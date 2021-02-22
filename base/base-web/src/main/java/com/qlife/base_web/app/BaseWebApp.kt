package com.qlife.base_web.app

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.util.Log
import com.qlife.base_web.preload.WebPreloadHelper
import com.qlife.lib_app.appdelegate.AppPriority
import com.qlife.lib_app.appdelegate.IAppLife
import com.tencent.smtt.sdk.QbSdk
import com.tencent.smtt.sdk.TbsListener
import com.tencent.smtt.sdk.WebView

/**
 * BaseWebApp,反射调用
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2019-10-15 10:57
 */
class BaseWebApp : IAppLife {

    override fun attachBaseContext(context: Context) {
        Log.d(TAG, "attachBaseContext")

    }

    override fun onCreate(application: Application) {
        Log.d(TAG, "onCreate")
        app = application
        initWebView()

    }


    private fun initWebView() {
        //确定内核是否成功加载并且可用
        val callback = object : QbSdk.PreInitCallback {
            override fun onCoreInitFinished() {
                Log.d(TAG, "onCoreInitFinished X5内核加载完成")
            }

            override fun onViewInitFinished(b: Boolean) {
                //这里被回调，并且b=true说明内核初始化并可以使用
                //如果b=false,内核会尝试安装，你可以通过下面监听接口获知
                Log.d(TAG, "onViewInitFinished 初化结束 b：$b")
            }
        }

        val tbsListener = object : TbsListener {
            override fun onDownloadFinish(i: Int) {
                //tbs内核下载完成回调
                Log.d(TAG, "onDownloadFinish: tbs内核下载完成回调")
            }

            override fun onInstallFinish(i: Int) {
                //内核安装完成回调，
                Log.d(TAG, "onInstallFinish: 内核安装完成回调")
            }

            override fun onDownloadProgress(i: Int) {
                //下载进度监听
                Log.d(TAG, "onDownloadProgress: 下载进度监听")

            }
        }

        QbSdk.setDownloadWithoutWifi(true)
        QbSdk.initX5Environment(app, callback)
        //内核会尝试安装，你可以通过下面监听接口获知
        QbSdk.setTbsListener(tbsListener)
        //缓存WebView
        WebPreloadHelper.preloadX5Web()
        WebView.setWebContentsDebuggingEnabled(true)
        android.webkit.WebView.setWebContentsDebuggingEnabled(true)

    }


    override fun onTerminate(application: Application) {
        Log.d(TAG, "onTerminate")

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        Log.d(TAG, "onConfigurationChanged")
    }

    override fun onLowMemory() {
        Log.d(TAG, "onLowMemory")
    }

    override fun onTrimMemory(level: Int) {
        Log.d(TAG, "onTrimMemory")
    }

    /**
     * 设置该appLife的优先级，必须设置，否则不会回调
     */
    override fun onPriority(): Int {
        return AppPriority.MEDIUM_DEFAULT
    }

    companion object {
        private const val TAG = "BaseWebApp"
        private lateinit var app: Application

        fun getApp(): Application {
            return app
        }
    }
}
