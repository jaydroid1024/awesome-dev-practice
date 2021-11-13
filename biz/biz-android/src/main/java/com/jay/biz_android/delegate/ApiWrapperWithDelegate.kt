package com.jay.biz_android.delegate

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Proxy

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
fun main() {

    val apiWrapperWithDelegate = object : Api by noOpDelegate() {
        override fun a() {
            println("aa")
        }
    }
    apiWrapperWithDelegate.a()
    apiWrapperWithDelegate.b()
    apiWrapperWithDelegate.c()


}

inline fun <reified T : Any> noOpDelegate(): T {
    val javaClass = T::class.java
    val noOpHandler = InvocationHandler { _, _, _ ->
        // no op
    }
    return Proxy.newProxyInstance(
        javaClass.classLoader, arrayOf(javaClass), noOpHandler
    ) as T
}


