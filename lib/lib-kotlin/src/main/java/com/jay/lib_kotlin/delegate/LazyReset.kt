package com.jay.lib_kotlin.delegate

import java.util.*
import kotlin.reflect.KProperty

/**
 * lazyMgr.reset() 控制是否每次都初始化 lazy 块
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/13
 */
class LazyReset {
    var name: Int = 0
    val lazyMgr = resettableManager()
    val prop1: Int by resettableLazy(lazyMgr) {
        name++
    }
    val prop2: Int by resettableLazy(lazyMgr) {
        name++
    }
    val prop3: Int by resettableLazy(lazyMgr) {
        name++
    }
}

fun main() {
    val LazyReset = LazyReset()
    println(LazyReset.prop1)
    LazyReset.lazyMgr.reset()
    println(LazyReset.prop1)
    println(LazyReset.prop2)
    LazyReset.lazyMgr.reset()
    println(LazyReset.prop2)
    println(LazyReset.prop3)
    LazyReset.lazyMgr.reset()
    println(LazyReset.prop3)
}


class ResettableLazyManager {
    // we synchronize to make sure the timing of a reset() call and new inits do not collide
    val managedDelegates = LinkedList<Resettable>()

    fun register(managed: Resettable) {
        synchronized(managedDelegates) {
            managedDelegates.add(managed)
        }
    }

    fun reset() {
        synchronized(managedDelegates) {
            managedDelegates.forEach { it.reset() }
            managedDelegates.clear()
        }
    }
}

interface Resettable {
    fun reset()
}

class ResettableLazy<P>(val manager: ResettableLazyManager, val init: () -> P) : Resettable {
    @Volatile
    var lazyHolder = makeInitBlock()

    operator fun getValue(thisRef: Any?, property: KProperty<*>): P {
        return lazyHolder.value
    }

    override fun reset() {
        lazyHolder = makeInitBlock()
    }

    fun makeInitBlock(): Lazy<P> {
        return lazy {
            manager.register(this)
            init()
        }
    }
}

fun <P> resettableLazy(manager: ResettableLazyManager, init: () -> P): ResettableLazy<P> {
    return ResettableLazy(manager, init)
}

fun resettableManager(): ResettableLazyManager = ResettableLazyManager()