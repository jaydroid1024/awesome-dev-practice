package com.jay.lib_kotlin.delegate

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/2
 */
class FooObservable {


    //beforeChange: 在尝试更改属性值之前调用的回调。 调用此回调时，该属性的值尚未更改。 如果回调返回true ，则属性的值被设置为新值，如果回调返回false ，则丢弃新值，属性保持其旧值
    //afterChange: 进行属性更改后调用的回调。 调用此回调时，该属性的值已更改。

    //返回读/写属性的属性委托，该属性在更改时调用指定的回调函数。
    //参数：
    //initialValue - 属性的初始值。
    //onChange - 在更改属性后调用的回调。 调用此回调时，该属性的值已更改

    var items: List<String> by Delegates.observable(mutableListOf()) { property, oldValue, newValue ->
        println("${property.name} : $oldValue -> $newValue")
    }

    var nameAfter: String by Delegates.observable("no") { prop, old, new ->
        println("$old -> $new")
    }
    var nameBefore: String by Delegates.vetoable("no") { prop, old, new ->
        println("$old -> $new")
        true //返回true 表示 setValue 成功，否则不能覆盖原值
    }

    private fun <T> onChange(property: KProperty<*>, oldValue: T, newValue: T) {
        println("${property.name} : $oldValue -> $newValue")
    }

    var age: Int by Delegates.observable(18, ::onChange)

    var name2: String by Delegates.notNull()
    val age2: Int by Delegates.notNull() // notNull 会为每个属性创建委托类 NotNullVar

    //    lateinit var age3: Int //lateinit 不支持原始类型
    lateinit var name3: String //lateinit 只能用在 var

    //非空属性应用场景分析
    //通常，声明为非空类型的属性必须在构造函数中初始化。然而，这通常并不方便。
    // 例如，可以通过依赖注入或在单元测试的 setup 方法中初始化属性。
    // 在这种情况下，您不能在构造函数中提供非 null 初始值设定项，但您仍然希望在引用类体内的属性时避免空检查。

    //notNull VS lateinit
    //lateinit 不支持原始类型、只能用在可变属性var
    //notNull 会为每个属性创建委托类 NotNullVar

    //notNull 可以返回一个讲过非空校验的属性
    // 在分配初始值之前尝试读取属性会导致异常，这也是返回非空属性的原理所在


}

fun main() {
    val user = FooObservable()
    user.nameAfter = "first"
    user.nameAfter = "second"
    user.nameBefore = "11111"
    user.nameBefore = "2222"
    user.age = 33
    user.age = 55
    //add like this:
    user.items += "new val"
    user.items += "new 111"

    //println(user.name2) // Property name2 should be initialized before get.
    user.name2 = "name2"
    println(user.name2)
}


