package com.jay.biz_movie.net

import com.jay.base_component.app.BaseComponentApp

/**
 * 网络工厂类，提供所有网络请求的总入口
 * @author wangxuejie
 * @date 2019-12-25 17:51
 * @version 1.0
 */
object BizMovieNetFactory {

    /**
     * Network
     */
    private var network: BizMovieNetwork? = null

    /**
     * 获取公共项目需要的网络类
     */
    @Synchronized
    fun network(): BizMovieNetwork {

        if (network == null) {
            network = BizMovieNetwork(BaseComponentApp.getApp().applicationContext)
        }
        return network!!
    }
}