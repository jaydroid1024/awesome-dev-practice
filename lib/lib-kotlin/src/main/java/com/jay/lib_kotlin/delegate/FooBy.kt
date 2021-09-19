package com.jay.lib_kotlin.delegate

import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * 实现自己的代理类
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/2
 */
public open class FooBy {

    //只要在by关键字后面带有一个代理对象，这个代理不一定要实现特定的接口，只要包含了getValue/setValue方法、那它就能作为一个代理属性来使用。
    var y by MyDelegate()


    private val provider = PropertyDelegateProvider<FooBy, MyDelegate> { thisRef, property ->
        if (thisRef.y == "YYY") {
            MyDelegate()
        } else {
            MyDelegate2()
        }
    }

    var w: String by provider


}


operator fun MyDelegate.getValue(thisRef: Any, property: KProperty<*>): String = this.value

open class MyDelegate {

    var value: String = "YYYW"

    //todo 代理类里面必须提供 getValue 方法，或者扩展这个方法也可
    open operator fun getValue(thisRef: Any, property: KProperty<*>): String {
        println("thisRef:" + thisRef.hashCode())
        println("property:" + property.hashCode())
        return value
    }

    open operator fun setValue(thisRef: Any, property: KProperty<*>, s: String) {
        value = s
    }
}

class MyDelegate2 : MyDelegate(), ReadWriteProperty<Any, String> {

    var value2: String = "WWW"

    /**
     * Returns the value of the property for the given object.
     * @param thisRef the object for which the value is requested.
     * @param property the metadata for the property.
     * @return the property value.
     */
    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return value2
    }

    /**
     * Sets the value of the property for the given object.
     * @param thisRef the object for which the value is requested.
     * @param property the metadata for the property.
     * @param value the value to set.
     */
    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        this.value2 = value
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