package com.jay.lib_kotlin.delegate

/**
 * 接口代理，默认还回去，可以重写需要改动的方法
 * 对象api代理ApiWrapperWithDelegate实现Api
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/2
 */
class ApiWrapperWithDelegate(private val api: Api) : Api by api {
    override fun a() {
        println("ApiWrapperWithDelegate-a")
    }
}