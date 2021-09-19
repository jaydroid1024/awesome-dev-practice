package com.jay.lib_kotlin.delegate

import kotlin.reflect.KProperty

/**
 *
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/2
 */
interface MyLazy<out T> {
    val value: T
}

fun <T> myLazy(initializer: () -> T): MyLazy<T> = MyLazyImpl(initializer)

//委托标识方法
operator fun <T> MyLazy<T>.getValue(thisRef: Any?, property: KProperty<*>): T = this.value


internal class MyLazyImpl<out T>(initializer: () -> T) : MyLazy<T> {


    private var initializer: (() -> T)? = initializer

    private var _value: Any? = null

    override val value: T
        get() {
            //懒加载
            if (_value == null) {
                _value = initializer!!()
            }
            @Suppress("UNCHECKED_CAST")
            return _value as T
        }
}

