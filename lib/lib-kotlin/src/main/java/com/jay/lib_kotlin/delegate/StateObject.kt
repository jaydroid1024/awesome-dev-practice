package com.jay.lib_kotlin.delegate

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/2
 */
class StateObject {
    //返回具有非“空”值的读写属性的属性委托，该值不是在对象构造期间而是在稍后时间初始化。在分配初始值之前尝试读取属性会导致异常。
    var notNullDelegates: Int by Delegates.notNull()

    //返回读写属性的属性委托，该属性在更改时调用指定的回调函数。 @param initialValue 属性的初始值。 @param onChange 属性更改后调用的回调。调用此回调时，该属性的值已更改。
    var observableDelegates: Int by Delegates.observable(0, ::onChange)

    var vetoableDelegates: Int by Delegates.vetoable(0, ::onChange2)

    private fun onChange(property: KProperty<*>, oldValue: Int, newValue: Int) {
        println("property: ${property.name}")
        println("oldValue: $oldValue")
        println("newValue: $newValue")

    }

    private fun onChange2(property: KProperty<*>, oldValue: Int, newValue: Int): Boolean {
        println("property: ${property.name}")
        println("oldValue: $oldValue")
        println("newValue: $newValue")
        return true
    }
}


