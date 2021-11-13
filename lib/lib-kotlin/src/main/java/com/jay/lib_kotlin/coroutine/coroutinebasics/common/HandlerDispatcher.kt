package com.jay.lib_kotlin.coroutine.coroutinebasics.common

import android.os.Handler

object HandlerDispatcher: Dispatcher {
    private val handler = Handler()

    override fun dispatch(block: () -> Unit) {
        handler.post(block)
    }
}