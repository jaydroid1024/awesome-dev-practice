package com.qlife.lib_app.appdelegate

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import android.text.TextUtils
import android.util.Log
import android.util.SparseArray
import androidx.core.util.containsKey
import androidx.core.util.isNotEmpty

/**
 * Application 生命周期分发代理类
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2019-10-15 10:57
 */
class ApplicationDelegate(var context: Context) : IAppLife {

    private var appLifeList: List<IAppLife>? = null
    private var appLifePriority: List<Int>? = null
    private var priorityGroupMap: SparseArray<ArrayList<IAppLife>>? = null
    private val processName = getProcessName(context, android.os.Process.myPid())
    private val packageName = getAppPackageName(context)

    init {
        Log.e(IAppLife.TAG, "============= $processName ============= ")
        //初始化Manifest文件解析器，用于解析组件在自己的 Manifest 文件配置的 App 代理类
        appLifeList = ManifestParser(context).parseAppFromManifest()
        //按照优先级分组
        if (appLifeList != null && appLifeList!!.isNotEmpty()) {
            priorityGroupMap = SparseArray()
            appLifePriority = getAppLifePriority()
            for (life in appLifeList!!) {
                Log.d(IAppLife.TAG, " 组件名: " + life.javaClass.simpleName + "优先级" + life.onPriority())
                //分组
                groupAppLifeByPriority(life)
            }
            Log.d(IAppLife.TAG, " 分组完成: " + priorityGroupMap.toString())
        }
    }

    /**
     * 添加优先级 数字越大优先级越高
     *
     * LOW      [1,2,3]
     * MEDIUM   [4,5,6]
     * HIGH     [7,8,9]
     * @return
     */
    private fun getAppLifePriority(): List<Int> {
        val appLifePriority = arrayListOf<Int>()
        appLifePriority.add(AppPriority.HIGH.HIGH_3)
        appLifePriority.add(AppPriority.HIGH.HIGH_2)
        appLifePriority.add(AppPriority.HIGH.HIGH_1)
        appLifePriority.add(AppPriority.HIGH_DEFAULT)
        appLifePriority.add(AppPriority.MEDIUM.MEDIUM_3)
        appLifePriority.add(AppPriority.MEDIUM.MEDIUM_2)
        appLifePriority.add(AppPriority.MEDIUM.MEDIUM_1)
        appLifePriority.add(AppPriority.MEDIUM_DEFAULT)
        appLifePriority.add(AppPriority.LOW.LOW_3)
        appLifePriority.add(AppPriority.LOW.LOW_2)
        appLifePriority.add(AppPriority.LOW.LOW_1)
        appLifePriority.add(AppPriority.LOW_DEFAULT)
        return appLifePriority
    }

    /**
     * 按照优先级分组
     * @param life
     */
    private fun groupAppLifeByPriority(life: IAppLife) {
        val lifePriority = life.onPriority()
        if (priorityGroupMap!!.containsKey(lifePriority)) {
            //map中存在此分类，将数据存放当前key的map中
            priorityGroupMap!![lifePriority]?.add(life)
        } else {
            //map中不存在，新建key，用来存放数据
            val priorityList = ArrayList<IAppLife>()
            priorityList.add(life)
            priorityGroupMap!!.put(lifePriority, priorityList)
        }
    }

    override fun attachBaseContext(context: Context) {
        Log.d(IAppLife.TAG, " attachBaseContext $processName")
        if (priorityGroupMap?.isNotEmpty() == true) {
            //遍历所有优先级逐个执行
            appLifePriority?.forEach {
                attachBaseContextForLife(priorityGroupMap!![it], context)
            }
        }
    }

    /**
     * 调用各个优先级下的 IAppLife 的 attachBaseContext 方法
     */
    private fun attachBaseContextForLife(appLifes: ArrayList<IAppLife>?, context: Context) {
        if (!appLifes.isNullOrEmpty()) {
            for (life in appLifes) {
                //只在主进程初始化 attachBaseContext(context: Context) 方法
                if (TextUtils.equals(processName, packageName)) {
                    life.attachBaseContext(context)
                }
                //在多进程都可以初始化 attachBaseContext(context: Context, processName: String)
                life.attachBaseContext(context, processName)
                Log.d(IAppLife.TAG, "进程：[$processName]: ${life.javaClass.simpleName} attachBaseContext() 初始化完成")
            }
        }
    }


    override fun onCreate(application: Application) {
        Log.d(IAppLife.TAG, " onCreate $processName")
        if (priorityGroupMap?.isNotEmpty() == true) {
            //遍历所有优先级逐个执行
            appLifePriority?.forEach {
                onCreateForLife(priorityGroupMap!![it], application)
            }
        }
    }


    /**
     * 调用各个优先级下的 onCreate
     */
    private fun onCreateForLife(appLifes: ArrayList<IAppLife>?, app: Application) {
        if (!appLifes.isNullOrEmpty()) {
            for (life in appLifes) {
                //只在主进程初始化onCreate
                if (TextUtils.equals(processName, packageName)) {
                    life.onCreate(app)
                }
                //在多进程都可以初始化
                life.onCreate(app, processName)
                Log.d(IAppLife.TAG, "[$processName]: ${life.javaClass.simpleName} onCreate() 初始化完成")
            }
        }
    }

    override fun onTerminate(application: Application) {
        if (priorityGroupMap?.isNotEmpty() == true) {
            //遍历所有优先级逐个执行
            appLifePriority?.forEach {
                onTerminateForLife(priorityGroupMap!![it], application)
            }
        }
    }


    /**
     * 调用各个优先级下的 onTerminate
     */
    private fun onTerminateForLife(appLifes: ArrayList<IAppLife>?, application: Application) {
        if (!appLifes.isNullOrEmpty()) {
            for (life in appLifes) {
                life.onTerminate(application)
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        if (priorityGroupMap?.isNotEmpty() == true) {
            //遍历所有优先级逐个执行
            appLifePriority?.forEach {
                onConfigurationChangedForLife(priorityGroupMap!![it], newConfig)
            }
        }
    }

    /**
     * 调用各个优先级下的 onConfigurationChanged
     */
    private fun onConfigurationChangedForLife(appLifes: ArrayList<IAppLife>?, newConfig: Configuration) {
        if (!appLifes.isNullOrEmpty()) {
            for (life in appLifes) {
                life.onConfigurationChanged(newConfig)
            }
        }
    }

    override fun onLowMemory() {
        if (priorityGroupMap?.isNotEmpty() == true) {
            //遍历所有优先级逐个执行
            appLifePriority?.forEach {
                onLowMemoryForLife(priorityGroupMap!![it])
            }
        }
    }

    /**
     * 调用各个优先级下的 onLowMemory
     */
    private fun onLowMemoryForLife(appLifes: ArrayList<IAppLife>?) {
        if (!appLifes.isNullOrEmpty()) {
            for (life in appLifes) {
                life.onLowMemory()
            }
        }
    }

    override fun onTrimMemory(level: Int) {
        if (priorityGroupMap?.isNotEmpty() == true) {
            //遍历所有优先级逐个执行
            appLifePriority?.forEach {
                onTrimMemoryForLife(priorityGroupMap!![it], level)
            }
        }
    }

    /**
     * 调用各个优先级下的 onTerminate
     */
    private fun onTrimMemoryForLife(priorityList: ArrayList<IAppLife>?, level: Int) {
        if (!priorityList.isNullOrEmpty()) {
            for (life in priorityList) {
                life.onTrimMemory(level)
            }
        }
    }

    override fun onPriority(): Int {
        return 0
    }


    /**
     * 获取当前进程名称
     *
     * @return 当前进程名称
     */
    private fun getProcessName(context: Context, pid: Int): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            return Application.getProcessName()
        }
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningApps = am.runningAppProcesses ?: return "null"
        for (procInfo in runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName
            }
        }
        return "null"
    }

    /**
     * 获取应用程序包名
     *
     * @param context 上下文
     * @return 包名
     */
    private fun getAppPackageName(context: Context): String {
        try {
            val packageManager = context.packageManager
            val packageInfo = packageManager.getPackageInfo(context.packageName, 0)
            return packageInfo.packageName
        } catch (e: PackageManager.NameNotFoundException) {

            e.printStackTrace()
        }
        return "null"
    }
}
