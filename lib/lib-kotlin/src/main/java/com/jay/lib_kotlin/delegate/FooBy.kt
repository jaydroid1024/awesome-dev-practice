package com.jay.lib_kotlin.delegate

import kotlin.reflect.KProperty

/**
 * 实现自己的代理类
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/2
 */
public open class FooBy {

    //只要在by关键字后面带有一个代理对象，这个代理不一定要实现特定的接口，只要包含了那些签名特殊的get，或者get，set方法都有，那它就能作为一个代理属性来使用。
    val y: String by MyDelegate()

    var w: String by MyDelegate()

}

operator fun MyDelegate.getValue(thisRef: Any?, property: KProperty<*>): String = this.value

class MyDelegate {

    var value: String = "YYY"

    //todo 代理类里面必须提供 getValue 方法，或者扩展这个方法也可
    operator fun getValue(thisRef: Any, property: KProperty<*>): String {
        return value
    }

    operator fun setValue(thisRef: Any, property: KProperty<*>, s: String) {
        value = s
    }
}


//public final class FooBy {
//   // $FF: synthetic field
//   static final KProperty[] $$delegatedProperties = new KProperty[]{(KProperty)Reflection.property1(new PropertyReference1Impl(FooBy.class, "y", "getY()Ljava/lang/String;", 0)), (KProperty)Reflection.mutableProperty1(new MutablePropertyReference1Impl(FooBy.class, "w", "getW()Ljava/lang/String;", 0))};
//   @NotNull
//   private final MyDelegate y$delegate = new MyDelegate();
//   @NotNull
//   private final MyDelegate w$delegate = new MyDelegate();
//
//   @NotNull
//   public final String getY() {
//      return this.y$delegate.getValue(this, $$delegatedProperties[0]);
//   }
//
//   @NotNull
//   public final String getW() {
//      return this.w$delegate.getValue(this, $$delegatedProperties[1]);
//   }
//
//   public final void setW(@NotNull String var1) {
//      Intrinsics.checkNotNullParameter(var1, "<set-?>");
//      this.w$delegate.setValue(this, $$delegatedProperties[1], var1);
//   }
//}