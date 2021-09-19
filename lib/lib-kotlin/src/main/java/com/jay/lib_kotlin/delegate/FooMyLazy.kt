package com.jay.lib_kotlin.delegate


/**
 *
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/2
 */
class FooMyLazy {

    private var index = 0
    val x: String by myLazy {
        println("myLazy is call")
        index++
        "xxx_$index"
    }


}

val f = fun(): String {
    return "a"
}


fun main() {
    val fooMyLazy = FooMyLazy()
    println(fooMyLazy.x)
    println(fooMyLazy.x)


    val a: MyLazy<String> = myLazy(f)
    val l: Lazy<String> = lazy(f)
    val p = fooMyLazy::x
    a.getValue(fooMyLazy, p)
    l.getValue(fooMyLazy, p)


}


