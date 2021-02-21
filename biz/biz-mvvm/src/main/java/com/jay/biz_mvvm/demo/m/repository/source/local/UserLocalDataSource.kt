package com.jay.biz_mvvm.demo.m.repository.source.local

import com.jay.base_component.network.bean.wan.user.User

/**
 * 本地数据抽象层，约束本地数据获取的行为（入参、返回值、方法等）
 */
interface UserLocalDataSource {

    /**
     * 保存用户名
     */
    fun saveUserName(userName: String)


    /**
     * 保存用户密码
     */
    fun savePassword(password: String)

    fun getUserName(): String

    fun cacheUserInfo(data: User?)

}