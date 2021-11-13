package com.jay.lib_kotlin.coroutine.coroutinebasics.utils

import java.text.SimpleDateFormat
import java.util.*


val dateFormat = SimpleDateFormat("yyyy-MM-DD HH:mm:ss", Locale.CHINA)

val now = { dateFormat.format(Date(System.currentTimeMillis())) }

fun log(vararg msg: Any?) =
    println("time: ${now()}, currentThread: [${Thread.currentThread().name}], msg:  ${msg.joinToString(" ")}")

fun stackTrace() {
    Throwable().printStackTrace(System.out)
}
