package com.jay.lib_kotlin.delegate

/**
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/2
 */
class ApiImpl : Api {
    override fun a() { println("ApiImpl-a") }
    override fun b() { println("ApiImpl-b") }
    override fun c() { println("ApiImpl-c") }
}