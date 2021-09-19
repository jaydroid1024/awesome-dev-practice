package com.jay.lib_kotlin.delegate

import kotlin.properties.Delegates
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * 通过内置接口实现委托
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/2
 */
fun main() {
    val fooReadWrite = FooReadWrite()
    println(fooReadWrite.delegate1)
    println(fooReadWrite.delegate2)
    println(fooReadWrite.delegate3)
    //1024
    //1025
    //1026
}


var observableProp: String by Delegates.observable("默认值：xxx"){
        property, oldValue, newValue ->
    println("property: $property: $oldValue -> $newValue ")
}


var vetoableProp: Int by Delegates.vetoable(0) { _, oldValue, newValue ->
    // 如果新的值大于旧值，则生效
    newValue > oldValue
}


public open class FooReadWrite {

    val provider1 =
        object : PropertyDelegateProvider<FooReadWrite, ReadWriteProperty<FooReadWrite, Int>> {
            override fun provideDelegate(
                thisRef: FooReadWrite,
                property: KProperty<*>
            ): ReadWriteProperty<FooReadWrite, Int> {
                return object : ReadWriteProperty<FooReadWrite, Int> {
                    var result = 1024
                    override fun getValue(thisRef: FooReadWrite, property: KProperty<*>): Int {
                        return result
                    }

                    override fun setValue(
                        thisRef: FooReadWrite,
                        property: KProperty<*>,
                        value: Int
                    ) {
                        result = value
                    }
                }
            }
        }

    //lambda 简化版本
    val provider2: PropertyDelegateProvider<FooReadWrite, ReadOnlyProperty<FooReadWrite, Int>> =
        PropertyDelegateProvider<FooReadWrite, ReadOnlyProperty<FooReadWrite, Int>> { pThisRef: Any?, pProperty: KProperty<*> ->
            ReadOnlyProperty<Any?, Int> { thisRef, property -> 1025 }
        }

    //智能类型推导再简化版本
    private val provider3 =
        PropertyDelegateProvider { _: Any?, _ -> ReadOnlyProperty<Any?, Int> { _, _ -> 1026 } }

    val delegate1: Int by provider1
    val delegate2: Int by provider2
    val delegate3: Int by provider3

}




