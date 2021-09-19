package com.jay.lib_kotlin.delegate

import kotlin.concurrent.thread

/**
 * KT中属性代理的实践：lazy
 * lazy 返回一个实现了Lazy接口的代理类，代理的属性都会调用getValue方法，
 * 这个时候调用Lazy的扩展方法getValue，然后返回Lazy内部的value属性，
 * 访问value的时候调用get()方法，synchronized是为了防止多线程访问同一个变量出错的问题
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/2
 */
class FooLazy {
    private var index = 0

//    LazyThreadSafetyMode 有三种模式作用是指定 [Lazy] 实例如何在多个线程之间同步初始化。
//    SYNCHRONIZED: 锁用于确保只有一个线程可以初始化[Lazy]实例。
//    PUBLICATION: 并发访问未初始化的[Lazy]实例值时，可以多次调用Initializer函数，但是只有第一个返回的值将用作[Lazy]实例的值。
//    NONE: 不使用锁来同步对 [Lazy] 实例值的访问；如果从多个线程访问该实例，可能会发生线程安全问题。除非保证 [Lazy] 实例永远不会从多个线程初始化，否则不应使用此模式。


    val a by MyDelegate()
    val b by myLazy { "bbb——${index++}" }
    val x: String by lazy{ "xxx——${index++}" } //LazyThreadSafetyMode默认 SYNCHRONIZED

    val y: String by lazy(LazyThreadSafetyMode.PUBLICATION) {
        println("yyy——lazy")
        "yyy——${index++}"
    }

    val z: String by lazy(LazyThreadSafetyMode.NONE) {
        println("zzz——lazy")
        "zzz——${index++}"
    }

}

fun main() {
    val fooLazy = FooLazy()
    for (i in 1..100) {
        val thread = thread(true) {
            Thread.sleep(100)
            println(Thread.currentThread().name)
            println("=" + fooLazy.x)
            println("====" + fooLazy.y)
            println("=========" + fooLazy.z)
        }
    }

}


//public final class FooLazy {
//   @NotNull
//   private final Lazy z$delegate;
//
//   @NotNull
//   public final String getZ() {
//      Lazy var1 = this.z$delegate;
//      Object var3 = null;
//      boolean var4 = false;
//      return (String)var1.getValue();
//   }
//
//   public FooLazy() {
//      this.z$delegate = LazyKt.lazy((Function0)null.INSTANCE);
//   }
//}

