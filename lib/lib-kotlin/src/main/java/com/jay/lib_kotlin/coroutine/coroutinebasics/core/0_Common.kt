package com.jay.lib_kotlin.coroutine.coroutinebasics.core

import com.jay.lib_kotlin.coroutine.coroutinebasics.api.User

fun showUser(user: User) {
    println(user)
}

fun showError(t: Throwable) {
    println("====================showError=====================")
    t.printStackTrace(System.out)
}

fun main() {


}