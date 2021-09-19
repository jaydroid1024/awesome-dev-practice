package com.jay.lib_kotlin.delegate

class FooMap {


}

class User {
    //委托 val
    val map: Map<String, Any?> = mapOf("name" to null, "age" to null)

    val name: String by map
    //可变 map 可以委托 val和var
    val map2: MutableMap<String, Any?> = mutableMapOf("age" to "Jay", "name" to "18")
    var age: Int? by map2

    //更新 age 的值，MutableMap 也会同步更新
    fun setValues(age: Int) {
        this.age = age
    }
}

fun main() {
    val user = User()
    user.setValues(44)
    println(user.age)
    println(user.name)
    println(user.map)
    println(user.map2)
}



