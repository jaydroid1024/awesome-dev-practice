package com.jay.biz_mvvm.demo.m.repository

import com.jay.base_component.network.bean.wan.BaseResponse
import com.jay.base_component.network.bean.wan.user.User
import io.reactivex.Observable

/**
 * MVVM 的 数据抽象层（Model），统一组件的数据仓库
 * 包含网络数据和本地数据（每个组件都应该定义自己的 XxxRepository ）
 */
interface UserRepository {

    /**
     * 保存用户名
     */
    fun saveUserName(userName: String)

    fun getUserName(): String

    fun login(userName: String, password: String): Observable<BaseResponse<User>>
}