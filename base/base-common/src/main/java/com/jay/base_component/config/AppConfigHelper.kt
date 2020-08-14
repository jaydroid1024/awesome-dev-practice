package com.jay.base_component.config

import com.jay.base_component.BuildConfig
import com.jay.base_component.arouter.ARHelper
import com.jay.base_component.arouter.ARPath
import com.jay.base_component.arouter.service.config.HideService
import com.jay.base_lib.utils.GsonUtils
import com.jay.base_lib.utils.L


/**
 * APP配置文件
 *
 * @author 王学杰
 * @version 1.0
 * @date 2019-11-05 18:03
 */
object AppConfigHelper {

    const val TAG = "AppConfigHelper"

    /**
     * hide服务管理类
     */
    private var hideService: HideService? = null

    init {
        //获取hideService服务管理类实例
        hideService = ARHelper.getService<HideService>(ARPath.PathHide.HIDE_SERVICE_PATH)
    }


    /**
     * 获取当前缓存的环境信息,全局使用这个方法获取配置信息用于环境切换
     */
    fun getAppConfig(): AppConfig? {
        val config = hideService?.getAppConfig()
        L.d(TAG, "getAppConfig:$config")
        return config
    }

    /**
     * 打开app缓存当前环境信息，如果之前没有切换过环境，就将gradle默认的缓存到本地
     */
    fun cacheDefaultAppConfig() {
        L.d(TAG, "cacheDefaultAppConfig:${getAppConfig()}")
        hideService?.setAppConfig(getCurrentAppConfig())
        L.d(TAG, "cacheDefaultAppConfig:${getAppConfig()}")
    }

    /**
     * 解析gradle指定那个环境信息
     */
    private fun getCurrentAppConfig(): AppConfig {
        val configJson =
            if (BuildConfig.DEBUG) BuildConfig.DEBUG_ENVIRONMENT_JSON else BuildConfig.RELEASE_ENVIRONMENT_JSON
        L.d(TAG, "getCurrentAppConfig:$configJson")
        return GsonUtils.fromJson(configJson, AppConfig::class.java)
    }


}