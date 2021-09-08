package com.jay.lib_kotlin.generics

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * https://www.kotlincn.net/docs/reference/inline-functions.html#%E5%85%B7%E4%BD%93%E5%8C%96%E7%9A%84%E7%B1%BB%E5%9E%8B%E5%8F%82%E6%95%B0
 * https://github.com/JetBrains/kotlin/blob/master/spec-docs/reified-type-parameters.md
 * 泛型-具体化的类型参数(Reified Type Parameters)
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/6
 */
open class TypeLiteral<T> {
    val type: Type
        get() = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
}

inline fun <reified T> typeLiteral(): TypeLiteral<T> =
    object : TypeLiteral<T>() {} // here T is replaced with the actual type

fun main() {
    val type1 = typeLiteral<String>().type // returns 'class java.lang.String'
    val type2 = typeLiteral<Int>().type // returns 'class java.lang.Integer'
    val type3 = typeLiteral<Array<String>>().type // returns '[Ljava.lang.String;'
    val type4 = typeLiteral<List<*>>().type // returns 'java.util.List<?>'

    println("type1:$type1")
    println("type2:$type2")
    println("type3:$type3")
    println("type4:$type4")
    //type1:class java.lang.String
    //type2:class java.lang.Integer
    //type3:class [Ljava.lang.String;
    //type4:java.util.List<?>

}
