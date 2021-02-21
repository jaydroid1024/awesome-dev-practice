package com.jay.base_component.app

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.jay.base_component.config.AppConfigHelper
import com.jay.base_component.network.default_net.DefaultNetFactory
import com.jay.base_lib.BuildConfig
import com.jay.base_lib.utils.Utils
import com.qlife.lib_app.appdelegate.AppPriority
import com.qlife.lib_app.appdelegate.IAppLife

/**
 * BaseApp,反射调用
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2019-10-15 10:57
 */
class BaseComponentApp : IAppLife {
    private lateinit var cookieJar: PersistentCookieJar
    override fun attachBaseContext(base: Context) {
        Log.d(TAG, "attachBaseContext")

    }

    override fun onCreate(application: Application) {
        Log.d(TAG, "onCreate")
        app = application
        instance = this
        //初始化工具类
        initRouter(application)
        Utils.init(application)
        initApp()
    }

    /**
     * 初始化Router
     */
    private fun initRouter(application: Application) {
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        if (BuildConfig.DEBUG) {
            // 打印日志
            ARouter.openLog()
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug()
            // 打印日志的时候打印线程堆栈
            ARouter.printStackTrace()
        }
        // 尽可能早，推荐在Application中初始化
        ARouter.init(application)
    }

    private fun initApp() {
        cookieJar = PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(getApp()))
        AppConfigHelper.cacheDefaultAppConfig()
        //初始化网络库
        DefaultNetFactory.initialize(getApp())

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

    fun getPersistentCookieJar(): PersistentCookieJar {
        return cookieJar
    }

    companion object {
        private const val TAG = "BaseComponentApp"
        private lateinit var instance: BaseComponentApp
        private lateinit var app: Application
        fun getInstance(): BaseComponentApp {
            return instance
        }

        fun getApp(): Application {
            return app
        }
    }
}
