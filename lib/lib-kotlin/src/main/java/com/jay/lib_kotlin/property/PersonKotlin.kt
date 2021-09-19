package com.jay.lib_kotlin.property

import com.jay.lib_kotlin.delegate.MyDelegate
import kotlin.jvm.internal.CallableReference
import kotlin.reflect.KMutableProperty0
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KProperty0
import kotlin.reflect.jvm.isAccessible

/**
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/2
 */
var sex: Int = 1

open class PersonKotlin {

    constructor(age: Int, name: String) {
        this.age = age
    }

    //property=field(java)+get+set
    var age: Int? = null

    open val name: String by MyDelegate()

    open val sex: String by lazy {
        "sex is male"
    }

}

fun main() {

    //属性引用 可以更清楚的了解 property 背后的 get 和 set
    val ageRef: KMutableProperty1<PersonKotlin, Int?> = PersonKotlin::age
    val personKotlin = PersonKotlin(18, "Jay")
    ageRef.set(personKotlin, 22)
    println(ageRef.get(personKotlin))
    //22

    //public actual fun set(receiver: T, value: V)
    //public actual fun get(receiver: T): V
    //receiver - 用于获取属性值的接收器。 例如，如果这是该类的成员属性，则它应该是类实例，如果这是顶级扩展属性，则它应该是扩展接收器
    //PersonKotlin::age 类名获取的属性引用不包含 receiver，操作时需要传递一个


    //测试自定义属性委托后获取属性的委托信息
    val nameRef: KProperty0<String?> = personKotlin::name
    println("personKotlin: " + personKotlin.hashCode())
    println("nameRef: " + nameRef.hashCode())
    println("personKotlin.name: " + personKotlin.name.hashCode())
    //取： is KMutableProperty -> javaField?.isAccessible ?: true && javaGetter?.isAccessible ?: true &&javaSetter?.isAccessible ?: true
    //存： is KMutableProperty -> { javaField?.isAccessible = value javaGetter?.isAccessible = value javaSetter?.isAccessible = value }
    //设置是否访问，只有设置为 true 才可以拿到 属性引用中的委托信息（如果被委托了）这个属性需要单独引入 kotlin-reflect 库
    nameRef.isAccessible = true
    //如果这是一个委托属性，则返回委托的值，如果此属性未委托，则返回null
    val nameDelegate = nameRef.getDelegate()
    println("nameDelegate： $nameDelegate") //返回委托信息
    println(nameRef.getter.invoke()) //相当于调用 get 方法
    //personKotlin: 1751075886
    //nameRef: -954731934
    //thisRef:1751075886
    //property:-954731934
    //personKotlin.name: 88377
    //可以看到属性引用类和它的 receiver 在委托类和这里的 hashCode 相同

    //com.jay.lib_kotlin.delegate.MyDelegate@5a63f509
    //YYY

    //测试lazy 属性代理方式
    val sexRef: KProperty0<String?> = personKotlin::sex
    personKotlin.sex
    sexRef.isAccessible = true
    println(sexRef.getDelegate())
    //获取的代理信息就是lazy代码块中的值：sex is male



    //测试 属性引用的类型
    val kMutableProperty0: KMutableProperty0<Int> = ::sex
    val s = kMutableProperty0 as CallableReference
    println(s.owner) //file class com.jay.lib_kotlin.property.PersonKotlinKt


}



