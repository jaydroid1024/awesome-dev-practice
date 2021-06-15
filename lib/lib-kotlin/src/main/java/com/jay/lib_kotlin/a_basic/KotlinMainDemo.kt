package com.jay.lib_kotlin.a_basic

/**
 * @author jaydroid
 * @version 1.0
 * @date 3/14/21
 */

fun main() {
    println("Kotlin Basic ")
    logNText(100)
}

fun logNText(n: Int) {
    var x = 0
    var i = 1
    while (i <= n) {
        i *= 2
        x++
    }
    println("n:$n") //100
    println("i:$i") //128
    println("x:$x") //7
}


public class KotlinMainDemo {

}

open class KotlinMainDemo2 {

}