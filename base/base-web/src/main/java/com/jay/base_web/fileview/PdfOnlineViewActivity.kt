package com.jay.base_web.fileview

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.BuildConfig
import com.jay.base_component.arouter.ARHelper
import com.jay.base_component.arouter.ARPath
import com.jay.base_component.base.BaseActivity
import com.jay.base_component.constant.Constants
import com.jay.base_lib.utils.L
import com.jay.base_lib.utils.ToastUtils
import com.jay.base_pdf.DownloadService
import com.jay.base_web.R
import com.jay.base_web.databinding.BasePdfActivityPdfOnlineViewBinding
import com.tbruyelle.rxpermissions2.RxPermissions
import com.tencent.smtt.sdk.TbsReaderView
import io.reactivex.internal.schedulers.RxThreadFactory
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.*
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

@Route(path = ARPath.PathWeb.FILE_VIEW_ACTIVITY_PATH)
class PdfOnlineViewActivity : BaseActivity(), View.OnClickListener {

    private var _binding: BasePdfActivityPdfOnlineViewBinding? = null

    //避免了空检查
    private val binding get() = _binding!!


    private var mUrl: String? = null

    private var mTitle: String? = null


    private var newApkFile: File? = null

    private var fileName: String? = null

    private var mReaderView: TbsReaderView? = null

    private var rxPermissions: RxPermissions? = null


    private val mExternalPath = Environment.getExternalStorageDirectory()
        .absolutePath + "/"

    private val mCurrentPath = mExternalPath

    private var okhttpClient: OkHttpClient? = null

    private val singleThreadScheduledPool = ThreadPoolExecutor(
        1, 1, 0L, TimeUnit
            .MILLISECONDS, LinkedBlockingQueue(), RxThreadFactory("thread-")
    )

    /**
     * 初始化OkHttpClient
     *
     * @return OkHttpClient
     */
    private val okHttpClient: OkHttpClient
        get() {

            if (okhttpClient == null) {
                okhttpClient = OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(5, TimeUnit.SECONDS)
                    .build()
            }

            return okhttpClient!!
        }

    /**
     * 初始化Retrofit
     *
     * @return Retrofit
     */
    private val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    /**
     * 获取UpdateAppService
     *
     * @return updateAppService
     */
    private val apiService: DownloadService
        get() = retrofit.create(DownloadService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = BasePdfActivityPdfOnlineViewBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initIntent()
        initTitle()
        initDatas()
    }

    private fun initDatas() {
        binding.includeTitle.ivBack.setOnClickListener(this)
        rxPermissions = RxPermissions(this)
        fileName = "boss_file_${System.currentTimeMillis()}.pdf"
        if (mUrl.isNullOrEmpty()) {
            this.finish()
        }
        L.d("okhttp", "mUrl= $mUrl")
        binding.includeTitle.tvTitle.post {
            checkPermission()
        }

    }

    override fun getLayoutResId(): Int {
        return 0
    }

    @SuppressLint("CheckResult")
    private fun checkPermission() {

        val writePermission = Manifest.permission.WRITE_EXTERNAL_STORAGE
        if (checkPermission(writePermission)) {
            initDownloadRequest()
            return
        }
        rxPermissions?.requestEach(writePermission)
            ?.subscribe { permission ->
                if (permission.granted) {
                    initDownloadRequest()
                } else {
                    this.finish()
                    ToastUtils.showShort("读写权限请求失败")
                }
            }
    }


    private fun initDownloadRequest() {
        Log.d(TAG, "initDownloadRequest")
        var call = apiService.downloadFileService(mUrl!!)
        call = call.clone()
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                Log.d(TAG, "initDownloadRequest onResponse success:" + response.isSuccessful)
                Log.d(TAG, "initDownloadRequest onResponse:$response")
                if (response.isSuccessful) {
                    singleThreadScheduledPool.execute {

                        if (writeResponseBodyToDisk(response.body())) {
                            this@PdfOnlineViewActivity.runOnUiThread { handleDownloadSuccess() }
                        }
                    }

                } else {

                    ToastUtils.showShort("下载合同文件失败")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                Log.d(TAG, "initDownloadRequest onFailure t:" + t.localizedMessage)
                ToastUtils.showShort("下载失败")

            }
        })
    }


    private fun handleDownloadSuccess() {

        Log.d(TAG, "handleDownloadSuccess")
        mReaderView = TbsReaderView(this) { integer, o, o1 ->

        }
        val bundle = Bundle()
        val filePath = newApkFile!!.toString()

        bundle.putString("filePath", filePath)
        bundle.putString(
            "tempPath", Environment.getExternalStorageDirectory().toString() + File.separator
                    + "BossTbsReaderView"
        )
        //加载文件前的初始化工作,加载支持不同格式的插件
        val b = mReaderView!!.preOpen(getFileType(filePath), false)
        if (b) {
            mReaderView!!.openFile(bundle)
        } else {
            ToastUtils.showShort("文件打开失败")
        }
        binding.flPdfContainer.addView(mReaderView)

        Log.d(TAG, "handleDownloadSuccess b:$b")
    }

    /***
     * 获取文件类型
     *
     * @param path 文件路径
     * @return 文件的格式
     */
    private fun getFileType(path: String): String {

        var str = ""

        if (TextUtils.isEmpty(path)) {
            return str
        }
        val i = path.lastIndexOf('.')
        if (i <= -1) {
            return str
        }
        str = path.substring(i + 1)
        return str
    }

    /**
     * writ file to disk
     *
     * @param body file
     *
     * @return whether succeed  true:succeed  false:failed
     */

    private fun writeResponseBodyToDisk(body: ResponseBody?): Boolean {

        Log.d(TAG, "writeResponseBodyToDisk")
        try {
            newApkFile = createFile()

            if (newApkFile == null) {
                return false
            }

            var inputStream: InputStream? = null
            var outStream: OutputStream? = null

            try {
                val fileReader = ByteArray(4096)

                inputStream = body!!.byteStream()
                outStream = FileOutputStream(newApkFile)

                while (true) {
                    val read = inputStream!!.read(fileReader)

                    if (read == -1) {
                        break
                    }

                    outStream.write(fileReader, 0, read)
                }

                outStream.flush()

                return true
            } catch (e: IOException) {
                Log.d(TAG, "writeResponseBodyToDisk 1e:" + e.localizedMessage)
                return false
            } finally {
                inputStream?.close()

                outStream?.close()
            }
        } catch (e: IOException) {
            Log.d(TAG, "writeResponseBodyToDisk 2e:" + e.localizedMessage)
            return false
        }

    }

    /**
     * 检查保存路径
     */
    private fun createFile(): File? {

        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            val file = File(mCurrentPath)
            if (!file.exists()) {
                if (!file.mkdir()) {
                    return null
                }
            }
            return File(mCurrentPath + fileName!!)
        } else {
            val dir = getDir("msf", Context.MODE_PRIVATE)

            val apkFile = File(dir.path + "/" + fileName)
            if (!apkFile.exists()) {
                try {
                    if (!apkFile.createNewFile()) {
                        return null
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                    return null
                }

            }
            val command = arrayOf("chmod", "777", apkFile.path)
            val builder = ProcessBuilder(*command)
            try {
                builder.start()
                return apkFile
            } catch (e: IOException) {
                e.printStackTrace()
                return null
            }

        }

    }


    private fun checkPermission(permission: String): Boolean {
        return rxPermissions?.isGranted(permission) ?: false
    }

    private fun initIntent() {
        val mapParams = ARHelper.getParamsMapFromIntentByJson(intent)
        mUrl = ARHelper.getStrFromParamsMap(mapParams, Constants.MapKey.URL)
        mTitle = ARHelper.getStrFromParamsMap(mapParams, Constants.MapKey.TITLE)
    }

    /**
     * 初始化标题
     */
    private fun initTitle() {
        if (mTitle.isNullOrEmpty()) {
            mTitle = mUrl
        }
        binding.includeTitle.tvTitle.text = mTitle
    }


    override fun onClick(view: View) {
        when (view.id) {
            //返回
            R.id.iv_back -> this.finish()
            else -> {
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mReaderView != null) {
            mReaderView!!.onStop()
        }
    }

    companion object {
        const val TAG = "PdfOnlineViewActivity"

    }


}