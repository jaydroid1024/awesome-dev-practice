package com.jay.biz_kotlin.ext

/**
 * 密封类：
 * 密封类用来表示受限的类继承结构：用于当一个值只有有限几种的类型
 * 在某种意义上,密封类就是扩展的枚举类
 * 不同：枚举常量只存在一个实例，而密封类的一个子类可以有可包含状态的多个实例。
 *
 * <out T> 泛型型变：用于安全的取数据
 */
sealed class BooleanExt<out T>

//Nothing 是所有类的子类
object Otherwise : BooleanExt<Nothing>()


class WithData<T>(val data: T) : BooleanExt<T>()


inline fun <T> Boolean.yes(block: () -> T) =
    when {
        this -> WithData(block())
        else -> Otherwise
    }

inline fun <T> Boolean.no(block: () -> T) =
    when {
        this -> Otherwise
        else -> WithData(block())
    }

inline fun <T> BooleanExt<T>.otherwise(block: () -> T): T =
    when (this) {
        is Otherwise -> block()
        is WithData -> this.data
    }


fun main() {
    val list = listOf<String>("a")
    list.isEmpty().no { println("no isEmpty") }.otherwise { println("yes isEmpty") }
    list.isEmpty().yes { println("yes isEmpty") }.otherwise {  }
}