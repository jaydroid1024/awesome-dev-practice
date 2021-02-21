package com.jay.biz_mvvm.demo.m.repository.source.remote

import com.jay.base_component.network.bean.wan.BaseResponse
import com.jay.base_component.network.bean.wan.user.User
import io.reactivex.Observable
import java.util.HashMap

/**
 * 网络数据抽象层，约束网络数据获取的行为（入参、返回值、方法等）
 */
interface UserNetWorkDataSource {

    fun login(requestMap: HashMap<String, Any>): Observable<BaseResponse<User>>

    fun login(userName: String, password: String): Observable<BaseResponse<User>>


}