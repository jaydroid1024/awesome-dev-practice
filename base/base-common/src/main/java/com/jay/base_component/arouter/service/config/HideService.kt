package com.jay.base_component.arouter.service.config

import com.jay.base_component.arouter.service.ARouterService
import com.jay.base_component.config.AppConfig

/**
 * 项目配置服务管理类
 * @author wangxuejie
 * @date 2019-12-17 15:58
 * @version 1.0
 */
interface HideService : ARouterService {

    fun getAppConfig(): AppConfig?

    fun setAppConfig(appConfig: AppConfig?)

    fun getAppConfigLocalList(): MutableList<AppConfig>?

    fun setAppConfigLocalList(appConfigList: MutableList<AppConfig>?)


}