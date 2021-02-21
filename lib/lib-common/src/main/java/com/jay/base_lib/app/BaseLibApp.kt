package com.jay.base_lib.app

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.util.Log
import com.qlife.lib_app.appdelegate.AppPriority
import com.qlife.lib_app.appdelegate.IAppLife

/**
 * BaseLibApp,反射调用
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2019-10-15 10:57
 */
class BaseLibApp : IAppLife {

    override fun attachBaseContext(base: Context) {
        Log.d(TAG, "attachBaseContext")

    }

    override fun onCreate(application: Application) {
        Log.d(TAG, "onCreate")
        app = application
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

    override fun onPriority(): Int {
        return AppPriority.HIGH_DEFAULT
    }

    companion object {
        private const val TAG = "BaseLibApp"
        private lateinit var app: Application
        fun getApp(): Application {
            return app
        }
    }
}
