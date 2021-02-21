package com.qlife.biz_setting.net

import com.qlife.base_component.app.BaseComponentApp

/**
 * 网络工厂类，提供所有网络请求的总入口
 * @author wangxuejie
 * @date 2019-12-25 17:51
 * @version 1.0
 */
object BizTemplateNetFactory {

    /**
     * Network
     */
    private var network: BizTemplateNetwork? = null

    /**
     * 获取公共项目需要的网络类
     */
    @Synchronized
    fun network(): BizSettingNetwork {

        if (network == null) {
            network = BizTemplateNetwork(BaseComponentApp.getApp().applicationContext)
        }
        return network!!
    }
}