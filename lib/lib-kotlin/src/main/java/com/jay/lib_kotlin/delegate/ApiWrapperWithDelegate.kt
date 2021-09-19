package com.jay.lib_kotlin.delegate

/**
 * 接口代理，默认还回去，可以重写需要改动的方法
 * 对象api代理ApiWrapperWithDelegate实现Api
 * api 是传进来的委托方
 * ApiWrapperWithDelegate 是受托方
 *
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/2
 */
class ApiWrapperWithDelegate(private val api: Api) : Api by api {
    override fun a() {
        println("ApiWrapperWithDelegate-a")
    }
}


fun main() {
    val apiImpl = ApiImpl() //委托方
    //接口委托

    val apiWrapperWithDelegate = ApiWrapperWithDelegate(apiImpl)
    apiWrapperWithDelegate.a()
    apiWrapperWithDelegate.b()
    apiWrapperWithDelegate.c()
//ApiWrapperWithDelegate-a
//ApiImpl-b
//ApiImpl-c


    val apiWrapper = ApiWrapper(apiImpl)
    apiWrapper.a()
}


