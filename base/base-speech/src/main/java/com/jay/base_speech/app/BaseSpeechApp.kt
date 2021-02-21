package com.jay.base_speech.app

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.util.Log
import com.danikula.videocache.HttpProxyCacheServer
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.iflytek.mscv5plusdemo.SpeechApp
import com.jay.base_lib.app.appdelegate.IAppLife
import com.jay.base_lib.app.appdelegate.PriorityLevel
import com.jay.base_speech.speech.SpeechHelper

/**
 * BaseApp,反射调用
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2019-10-15 10:57
 */
class BaseSpeechApp : IAppLife {

    private lateinit var cookieJar: PersistentCookieJar
    private var proxy: HttpProxyCacheServer? = null

    override fun attachBaseContext(base: Context) {
        Log.d(TAG, "attachBaseContext")

    }

    override fun onCreate(application: Application) {
        Log.d(TAG, "onCreate")
        app = application
        instance = this
        SpeechApp.onCreate(app)
        SpeechHelper.initSpeech()
    }

    fun getProxy(): HttpProxyCacheServer? {
        proxy = newProxy()
        return proxy
    }

    private fun newProxy(): HttpProxyCacheServer {
        return HttpProxyCacheServer.Builder(getApp())
            .maxCacheSize(1024 * 1024 * 1024)       // 1 Gb for cache
            .build()
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
    override fun onPriority(): String {
        return PriorityLevel.MEDIUM
    }


    companion object {
        private const val TAG = "BaseSpeechApp"
        private lateinit var instance: BaseSpeechApp
        private lateinit var app: Application
        fun getInstance(): BaseSpeechApp {
            return instance
        }

        fun getApp(): Application {
            return app
        }
    }
}
