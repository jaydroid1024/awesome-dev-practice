package com.jay.biz_mvvm.demo.m.repository.source.remote.retrofit.service

import com.jay.base_component.network.bean.wan.BaseResponse
import com.jay.base_component.network.bean.wan.user.User
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.*

/**
 * Retrofit 网络数据请求信息的封装接口
 */
interface UserApiService {


    /** =======================================================
    WanAndroid 开放API：https://www.wanandroid.com/blog/show/2
    baseUrl 请使用：
    https://www.wanandroid.com
    ========================================================== */

    @POST("user/login")
    @FormUrlEncoded
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Observable<BaseResponse<User>>


    @POST("user/login")
    fun login(@Body request: HashMap<String, Any>): Observable<BaseResponse<User>>


}