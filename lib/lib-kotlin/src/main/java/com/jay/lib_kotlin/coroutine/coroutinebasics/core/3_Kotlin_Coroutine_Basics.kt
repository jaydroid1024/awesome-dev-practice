package com.jay.lib_kotlin.coroutine.coroutinebasics.core

import com.jay.lib_kotlin.coroutine.coroutinebasics.api.User
import com.jay.lib_kotlin.coroutine.coroutinebasics.api.githubApi
import com.jay.lib_kotlin.coroutine.coroutinebasics.common.DispatcherContext
import com.jay.lib_kotlin.coroutine.coroutinebasics.common.HandlerDispatcher
import com.jay.lib_kotlin.coroutine.coroutinebasics.utils.log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import kotlin.coroutines.*

//Platform declaration clash: The following declarations have the same JVM signature
// (foo(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;):
suspend fun foo() {}
//fun foo(continuation: Continuation<Unit>): Any {}

suspend fun bar(a: Int): String = "Hello"
//fun bar(a: Int, continuation: Continuation<String>): Any = "Hello"


suspend fun getUserSuspend(name: String) = suspendCoroutine<User> { continuation ->
    githubApi.getUserCallback(name).enqueue(object : Callback<User> {
        override fun onFailure(call: Call<User>, t: Throwable) =
            continuation.resumeWithException(t)
        override fun onResponse(call: Call<User>, response: Response<User>) =
            response.takeIf { it.isSuccessful }?.body()?.let(continuation::resume)
                ?: continuation.resumeWithException(HttpException(response))
    })
}


suspend fun main() {
    val user = getUserSuspend("jaydroid1024")
    showUser(user)

    suspend {

    }.createCoroutine(object : Continuation<Unit> {
        override val context = EmptyCoroutineContext

        override fun resumeWith(result: Result<Unit>) {
            log("Coroutine End with $result")
        }
    }).resume(Unit)

    suspend {

    }.startCoroutine(object : Continuation<Unit> {
        override val context = EmptyCoroutineContext

        override fun resumeWith(result: Result<Unit>) {
            log("Coroutine End with $result")
        }
    })

    suspend {

    }.startCoroutine(object : Continuation<Unit> {
        override val context = DispatcherContext(HandlerDispatcher)

        override fun resumeWith(result: Result<Unit>) {
            log("Coroutine End with $result")
        }
    })

    suspend {

    }.startCoroutine(object : Continuation<Unit> {
        override val context = DispatcherContext(HandlerDispatcher)

        override fun resumeWith(result: Result<Unit>) {
            log("Coroutine End with $result")
        }
    })
}

suspend fun suspendFunc() = suspendCoroutine<Int> {
    it.resumeWith(Result.success(1))
}