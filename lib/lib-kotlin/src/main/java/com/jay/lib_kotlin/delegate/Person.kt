package com.jay.lib_kotlin.delegate

/**
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/2
 */
class Person(name: String) {

    val a: String = name.split(" ")[0]

    //public actual fun <T> lazy(initializer: () -> T): Lazy<T> = SynchronizedLazyImpl(initializer)
    val firstName: String by lazy {
        a
    }

}