package com.jay.lib_kotlin.coroutine

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


private fun getDemoIndex() = 6

fun main() = when (getDemoIndex()) {
    1 -> main01()
    2 -> main02()
    3 -> main03()
    4 -> main04()
    5 -> main05()
    6 -> main06()
    else -> main01()
}

//案例01：启动协程
fun main01() = runBlocking { // CoroutineScope
    launch { // 启动新的协同程序并继续
        delay(1000L) // 非阻塞延迟1秒
        println("World!") // print after delay
    }
    println("Hello") // 当上一个协程延迟的同时主协程继续执行
}

//案例02：提取 suspend 函数
fun main02() = runBlocking { // this: CoroutineScope
    launch { doWorld() }
    println("Hello")
}

// this is your first suspending function
suspend fun doWorld() {
    delay(1000L)
    println("World!")
}

//案例03：coroutineScope
fun main03() = runBlocking {
    doWorld03()
}

suspend fun doWorld03() = coroutineScope {  // this: CoroutineScope
    launch {
        delay(1000L)
        println("World!")
    }
    println("Hello")
}

//案例04：runBlocking 中嵌套 coroutineScope
// Sequentially executes doWorld followed by "Done"
fun main04() = runBlocking {
    doWorld04()
    println("Done")
}

// Concurrently executes both sections
suspend fun doWorld04() = coroutineScope { // this: CoroutineScope
    launch {
        delay(2000L)
        println("World 2")
    }
    launch {
        delay(1000L)
        println("World 1")
    }
    println("Hello")
}

//案例05：job.join()
fun main05() = runBlocking {
    val job = launch { // 启动一个新的协程并保留对其 Job 的引用
        delay(1000L)
        println("World!")
    }
    println("Hello")
    job.join() // 等待子协程完成
    println("Done")
}

//案例05：有多轻量
fun main06() = runBlocking {
    var i = 0
    repeat(100_000) { // launch a lot of coroutines
        launch {
            delay(1000L)
            i++
            print("$i, ")
        }
    }
}

