package com.jay.lib_kotlin.delegate

import kotlin.reflect.KProperty

/**
 *
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/2
 */
class FooMyLazy {
    private var index = 0
    val x: String by lazy_x {
        println("lazy_x")
        index++
        "xxx_$index"
    }


    public fun <T> lazy_x(initializer: () -> T): LazyX<T> = LazyXImpl(initializer)


}


public interface LazyX<out T> {

    public val valueX: T

    public fun isInitialized(): Boolean

}

operator fun <T> LazyX<T>.getValue(thisRef: Any?, property: KProperty<*>): T = this.valueX


internal object UNINITIALIZED_VALUE

// internal to be called from lazy in JS
internal class LazyXImpl<out T>(initializer: () -> T) : LazyX<T> {
    private var initializer: (() -> T)? = initializer
    private var _value: Any? = UNINITIALIZED_VALUE

    override val valueX: T
        get() {
//            if (_value === UNINITIALIZED_VALUE) {
//                println("not  initialized")
//                _value = initializer!!()
//                initializer = null
//            }
            _value = initializer!!()
            println("initialized")
            @Suppress("UNCHECKED_CAST")
            return _value as T
        }

    override fun isInitialized(): Boolean = _value !== UNINITIALIZED_VALUE

    override fun toString(): String =
        if (isInitialized()) valueX.toString() else "Lazy value not initialized yet."

}

