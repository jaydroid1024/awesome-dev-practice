package com.jay.lib_kotlin.coroutine.coroutinebasics.core

import com.jay.lib_kotlin.coroutine.coroutinebasics.api.User
import com.jay.lib_kotlin.coroutine.coroutinebasics.api.githubApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun async() {

//    val call = githubApi.getUserCallback("jaydroid1024")
//    call.enqueue(object : Callback<User> {
//        override fun onFailure(call: Call<User>, t: Throwable) {
//            showError(t)
//        }
//
//        override fun onResponse(call: Call<User>, response: Response<User>) {
//            response.body()?.let(::showUser) ?: showError(NullPointerException())
//        }
//    })

    val call = githubApi.getUserCallback()

    call.enqueue(object :Callback<Any>{
        /**
         * Invoked for a received HTTP response.
         *
         *
         * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
         * Call [Response.isSuccessful] to determine if the response indicates success.
         */
        override fun onResponse(call: Call<Any>, response: Response<Any>) {
            println(response.body().toString())
        }

        /**
         * Invoked when a network exception occurred talking to the server or when an unexpected
         * exception occurred creating the request or processing the response.
         */
        override fun onFailure(call: Call<Any>, t: Throwable) {
        }

    })
}

fun asyncLoop() {
    val names = arrayOf("bennyhuo", "udalov", "jaydroid1024")
    names.forEach { name ->
        val call = githubApi.getUserCallback(name)
        call.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                showError(t)
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                response.body()?.let(::showUser) ?: showError(NullPointerException())
            }
        })
    }
}


suspend fun coroutine() {
    try {
        val user = githubApi.getUserSuspend("jaydroid1024")
        showUser(user)
    } catch (e: Exception) {
        showError(e)
    }
}

suspend fun coroutineLoop() {
    val names = arrayOf("abreslav", "udalov", "jaydroid1024")
    names.forEach { name ->
        try {
            val user = githubApi.getUserSuspend(name)
            showUser(user)
        } catch (e: Exception) {
            showError(e)
        }
    }

}

private fun getDemoIndex() = 1

suspend fun main() = when (getDemoIndex()) {
    1 -> async()
    2 -> asyncLoop()
    3 -> coroutine()
    4 -> coroutineLoop()
    else -> async()
}

