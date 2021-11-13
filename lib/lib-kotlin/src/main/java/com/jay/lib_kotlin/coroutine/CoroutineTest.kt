package com.jay.lib_kotlin.coroutine

import com.jay.lib_kotlin.coroutine.coroutinebasics.api.githubApi
import com.jay.lib_kotlin.coroutine.coroutinebasics.core.showError
import com.jay.lib_kotlin.coroutine.coroutinebasics.core.showUser
import kotlin.coroutines.Continuation

/**
 * @author jaydroid
 * @version 1.0
 * @date 2021/10/12
 */
suspend fun coroutine() {
    try {
        val user = githubApi.getUserSuspend("jaydroid1024")
        showUser(user)
    } catch (e: Exception) {
        showError(e)
    }

}

suspend fun main() {
    coroutine()
}