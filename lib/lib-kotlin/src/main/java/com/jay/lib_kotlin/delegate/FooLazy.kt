package com.jay.lib_kotlin.delegate

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
    //用到时再去获取，并把结果缓存起来避免重复的运算，提高代码的性能
    val z: String by lazy {
        index++
        "zzz——$index"
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

