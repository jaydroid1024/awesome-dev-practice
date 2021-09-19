package com.jay.lib_kotlin.delegate

/**
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/2
 */
fun main() {
    val apiImpl = ApiImpl() //委托方
    //接口委托

    val apiWrapperWithDelegate = ApiWrapperWithDelegate(apiImpl)
    apiWrapperWithDelegate.a()
    apiWrapperWithDelegate.b()
    apiWrapperWithDelegate.c()
//ApiWrapperWithDelegate-a
//ApiImpl-b
//ApiImpl-c


    val apiWrapper = ApiWrapper(apiImpl)
    apiWrapper.a()


    val supperArrayWithDelegate = SupperArrayWithDelegate<String>()
    supperArrayWithDelegate["01"] = "a"
    supperArrayWithDelegate.add("b")
    println(supperArrayWithDelegate.toString())
//list:[b],map:{01=a}


    val stateObject = StateObject()
    stateObject.notNullDelegates = 1
    stateObject.observableDelegates = 2
    stateObject.observableDelegates = 3
    stateObject.vetoableDelegates = 4
    stateObject.vetoableDelegates = 5
//property: observableDelegates
//oldValue: 0
//newValue: 2
//property: observableDelegates
//oldValue: 2
//newValue: 3
//property: vetoableDelegates
//oldValue: 0
//newValue: 4
//property: vetoableDelegates
//oldValue: 4
//newValue: 5

    val foo = FooBy()
    val fooLazy = FooLazy()
    val fooMyLazy = FooMyLazy()
    println(foo.y)
    foo.y = "setY"
    println()
    println(foo.y)
    println(foo.w)
    println("foo:${foo.hashCode()}")
    println(fooLazy.z)
    println(fooLazy.z)
    println(fooLazy.z)
    println(fooMyLazy.x)
    println(fooMyLazy.x)
    println(fooMyLazy.x)

}
