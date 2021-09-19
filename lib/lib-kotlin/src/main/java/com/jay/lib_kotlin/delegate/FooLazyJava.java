package com.jay.lib_kotlin.delegate;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.functions.Function0;

/**
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/13
 */
class FooLazyJava {
  public static void main(String[] args) {
    Lazy<String> lazy =
        LazyKt.lazy(
            LazyThreadSafetyMode.SYNCHRONIZED,
            (Function0)
                (new Function0() {
                  @Override
                  public final String invoke() {
                    return "a";
                  }
                }));

    MyLazyImpl<String> lazy2 =
        new MyLazyImpl(
            (Function0)
                (new Function0() {
                  @Override
                  public final String invoke() {
                    return "a";
                  }
                }));

    lazy.getValue();
    lazy2.getValue();
    MyLazy<String> l = (MyLazy<String>) lazy2;
    l.getValue();
    LazyKt.lazyOf(null);
    //    MyLazyKt.getValue()

    MyDelegate a = new MyDelegate();
    a.getValue();
    //     FooByKt.getValue()
  }
}
