package com.jay.lib_kotlin.delegate

/**
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/2
 */
class ApiWrapper(private val api: Api) : Api {

    override fun a() {
        println("ApiWrapper-a")
        api.a()
    }

    override fun b() {
        println("ApiWrapper-b")
        api.b()
    }

    override fun c() {
        println("ApiWrapper-c")
        api.b()
    }

}