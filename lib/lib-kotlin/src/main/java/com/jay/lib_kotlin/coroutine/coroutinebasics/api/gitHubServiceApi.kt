package com.jay.lib_kotlin.coroutine.coroutinebasics.api

import com.jay.lib_kotlin.coroutine.coroutinebasics.utils.log
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

val githubApi by lazy {

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { it.proceed(it.request()).apply { log("request: ${code()}") } }
        .build()

    val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://soft.o3cloud.cn")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    retrofit.create(GitHubApi::class.java)
}

interface GitHubApi {

    @GET("users/{login}")
    fun getUserCallback(@Path("login") login: String): Call<User>

    @GET("/android/boss/owner/ver.json")
    fun getUserCallback(): Call<Any>

    @GET("users/{login}")
    suspend fun getUserSuspend(@Path("login") login: String): User
}

data class User(val id: String, val name: String, val url: String)