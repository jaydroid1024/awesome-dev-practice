package com.qlife.lib_app.appdelegate

import android.app.Application
import android.content.Context
import android.content.res.Configuration

/**
 * @author wangxuejie
 * @version 1.0
 * @date 2020/3/31
 */
interface IAppLife {

    fun attachBaseContext(context: Context) {}

    /**
     * 多进程会调用多次
     */
    fun attachBaseContext(context: Context, processName: String) {}

    fun onCreate(application: Application)

    /**
     * 多进程会调用多次
     */
    fun onCreate(application: Application, processName: String) {}

    fun onTerminate(application: Application) {}

    fun onConfigurationChanged(newConfig: Configuration) {}

    fun onLowMemory() {}

    fun onTrimMemory(level: Int) {}

    /**
     * 设置该appLife的优先级，必须设置，否则不会回调
     *
     */
    fun onPriority(): Int

    companion object {

        const val TAG = "AppLife"
    }

}
