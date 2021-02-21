package com.jay.lib_net.app

import android.app.Application
import android.util.Log
import com.facebook.stetho.Stetho
import com.qlife.lib_app.appdelegate.AppPriority
import com.qlife.lib_app.appdelegate.IAppLife

/**
 * LibNetApp,反射调用
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2019-10-15 10:57
 */
class LibNetApp : IAppLife {

    override fun onCreate(application: Application) {
        Log.d(TAG, "onCreate")
        init(application)
    }

    private fun init(application: Application) {
        app = application
        instance = this
        initStetho(application)
    }

    /**
     * 初始化Stetho
     */
    private fun initStetho(application: Application) {
        //网络库测试类,需要依赖lib_net
        Stetho.initializeWithDefaults(application)
    }

    /**
     * 设置该appLife的优先级，必须设置，否则不会回调
     */
    override fun onPriority(): Int {
        return AppPriority.HIGH_DEFAULT
    }

    companion object {
        private const val TAG = "LibNetApp"
        private lateinit var instance: LibNetApp
        private lateinit var app: Application

        fun getInstance(): LibNetApp {
            return instance
        }

        fun getApp(): Application {
            return app
        }
    }
}
