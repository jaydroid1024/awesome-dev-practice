package com.jay.lib_persistence.app

import android.app.Application
import android.util.Log
import com.qlife.lib_app.appdelegate.AppPriority
import com.qlife.lib_app.appdelegate.IAppLife

/**
 * LibPersistenceApp,反射调用
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2019-10-15 10:57
 */
class LibPersistenceApp : IAppLife {

    override fun onCreate(application: Application) {
        Log.d(TAG, "onCreate")
        init(application)
    }

    private fun init(application: Application) {
        app = application
        instance = this

    }

    /**
     * 设置该appLife的优先级，必须设置，否则不会回调
     */
    override fun onPriority(): Int {
        return AppPriority.HIGH_DEFAULT
    }

    companion object {

        private const val TAG = "LibPersistenceApp"

        private lateinit var instance: LibPersistenceApp

        private lateinit var app: Application

        fun getInstance(): LibPersistenceApp {
            return instance
        }

        fun getApp(): Application {
            return app
        }
    }
}
