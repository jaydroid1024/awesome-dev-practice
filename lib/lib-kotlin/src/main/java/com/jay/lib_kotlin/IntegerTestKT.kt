package com.jay.lib_kotlin

/**
 * @author jaydroid
 * @version 1.0
 * @date 3/11/21
 */
fun main(args: Array<String>) {
    basicTypeCacheTest()

}

/**
 *
 * kotlin中==比较的是数值是否相等, 而===比较的是两个对象的地址是否相等
 */
private fun basicTypeCacheTest() {
    val a1: Int = 999
    val a2: Int? = 999

    val b: Int? = a1
    val c: Int? = a1

    val d: Int? = a2
    val e: Int? = a2

    val f: Int = a1
    val g: Int = a1
    

    println(
        """
    val a: Int = 999
    val b: Int? = a
    val c: Int? = a
    val d: Int? = a2
    val e: Int? = a2
    println(b == c) //${b == c}
    println(b === c) //${b === c} 
    println(d == e) //${d == e}
    println(d === e) //${d === e}
    println(f == g) //${f == g}
    println(f == g) //${f === g}
    """.trimIndent()
    )
}

