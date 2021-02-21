package com.jay.biz_mvvm.demo.m.repository.source.remote

import com.jay.base_component.network.bean.wan.BaseResponse
import com.jay.base_component.network.bean.wan.user.User
import com.jay.biz_mvvm.demo.m.repository.source.remote.retrofit.UserNetFactory
import com.jay.biz_mvvm.demo.m.repository.source.remote.retrofit.UserNetWork
import io.reactivex.Observable
import java.util.*

/**
 * 网络数据实现层 okHttp+Retrofit+RxJava/Coroutine
 * 网络的请求方式可通过组合的方式更换
 */
class UserNetWorkDataSourceImpl : UserNetWorkDataSource {

    private var userNetWork: UserNetWork = UserNetFactory.getUserNet()

    override fun login(requestMap: HashMap<String, Any>): Observable<BaseResponse<User>> {
        return userNetWork.login(requestMap)
    }

    override fun login(userName: String, password: String): Observable<BaseResponse<User>> {
        return userNetWork.login(userName, password)
    }

}