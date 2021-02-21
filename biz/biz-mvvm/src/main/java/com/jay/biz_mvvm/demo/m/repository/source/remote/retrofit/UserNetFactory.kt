package com.jay.biz_mvvm.demo.m.repository.source.remote.retrofit

import android.annotation.SuppressLint

/**
 * 网络工厂类，提供所有网络请求的总入口
 * @author wangxuejie
 * @date 2019-12-25 17:51
 * @version 1.0
 */
@SuppressLint("StaticFieldLeak")
object UserNetFactory {

    var network: UserNetWork? = null

    @Synchronized
    fun getUserNet(): UserNetWork {
        if (network == null) {
            network = UserNetWork()
        }
        return network!!
    }


}