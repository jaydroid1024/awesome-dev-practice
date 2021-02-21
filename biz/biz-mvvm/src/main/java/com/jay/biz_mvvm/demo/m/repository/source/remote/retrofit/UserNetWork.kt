package com.jay.biz_mvvm.demo.m.repository.source.remote.retrofit

import com.jay.base_component.network.bean.wan.BaseResponse
import com.jay.base_component.network.bean.wan.user.User
import com.jay.biz_mvvm.demo.m.repository.source.remote.retrofit.service.UserApiService
import com.jay.lib_net.AbstractNetwork
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * @author wangxuejie
 * @version 1.0
 * @date 2019-12-24 17:18
 */

class UserNetWork() : AbstractNetwork<UserApiService>() {

    override val baseUrl: String get() = "https://www.wanandroid.com/"

    override val apiServiceClass: Class<UserApiService> get() = UserApiService::class.java

    /*重写相关方法实现个性化定制*/
    override fun okHttpClientHandler(builder: OkHttpClient.Builder): OkHttpClient? {
        return super.okHttpClientHandler(builder)
    }

    override fun retrofitBuilderHandler(builder: Retrofit.Builder): Retrofit.Builder {
        return super.retrofitBuilderHandler(builder)
    }

    override fun okHttpClientBuilderHandler(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        return super.okHttpClientBuilderHandler(builder)
    }

    fun login(): Observable<Any> {
        return Observable.just(Any()).delay(3, TimeUnit.SECONDS) //延迟3秒
    }

    fun login(requestMap: HashMap<String, Any>): Observable<BaseResponse<User>> {
        return getApiService().login(requestMap)
    }

    fun login(userName: String, password: String): Observable<BaseResponse<User>> {
        return getApiService().login(userName, password)
    }


}
