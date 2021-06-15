package com.jay.lib_kotlin

/**
 * @author jaydroid
 * @version 1.0
 * @date 3/1/21
 */
fun main() {

//    iterable()
//
//    sequence()

    sequence2()
}

fun iterable() {
    val words = "The quick brown fox jumps over the lazy dog".split(" ")
    println(words)

    val lengthsList = words.filter {
        println("filter: $it")
        it.length > 3
    }.map {
        println("length: ${it.length}")
        it.length
    }.take(2)

    println("Lengths of first 4 words longer than 3 chars:")
    println(lengthsList)
}

fun sequence() {
    val words = "The quick brown fox jumps over the lazy dog".split(" ")
    println(words)
    //将列表转换为序列
    val wordsSequence = words.asSequence()
    println(wordsSequence)
    val lengthsSequence = wordsSequence.filter {
        println("filter: $it")
        it.length > 3
    }.map {
        println("length: ${it.length}")
        it.length
    }.take(4)

    println("Lengths of first 4 words longer than 3 chars")
    // 终端操作：以列表形式获取结果
    println(lengthsSequence.toList())
}

fun sequence2() {
    val seq = sequence {
        (1..10).forEach {
            println("1>>")
            yield(it)
            println("2>>")
        }
    }

    println("3>>")

    seq.forEach { _ ->
        println("4>>")
    }


    /*
    执行结果：
    3>>
    1>>
    4>>
    2>>

    1>>
     */

}


class SequenceDemo {
}