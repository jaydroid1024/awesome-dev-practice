package com.qlife.base_web.net

import android.content.Context
import com.jay.base_component.BuildConfig
import com.jay.lib_net.AbstractNetwork
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 * @author wangxuejie
 * @version 1.0
 * @date 2019-12-24 17:18
 */

class FileNetwork(context: Context) : AbstractNetwork<FileDownloadApiService>() {

    override val baseUrl: String
        get() = BuildConfig.BASE_URL

    override val apiServiceClass: Class<FileDownloadApiService>
        get() = FileDownloadApiService::class.java

    private var pdfUrl = "https://css4.pub/2015/textbook/somatosensory.pdf"


    /*重写相关方法实现个性化定制*/

    override fun okHttpClientHandler(builder: OkHttpClient.Builder): OkHttpClient? {

        return super.okHttpClientHandler(builder)
    }

    override fun retrofitBuilderHandler(builder: Retrofit.Builder): Retrofit.Builder {
        return super.retrofitBuilderHandler(builder)
    }

    override fun okHttpClientBuilderHandler(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        return super.okHttpClientBuilderHandler(builder)
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
    }

    fun downloadFileService(fileUrl: String): Call<ResponseBody> {
        return getApiService().downloadFileService(fileUrl)
    }

}
