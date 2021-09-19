package com.jay.lib_kotlin.property

import kotlin.reflect.KProperty0

/**
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/9
 */
fun main() {
    testMyInter()
}


//public actual interface KProperty0<out V> : KProperty<V>, () -> V {

interface myInterface : () -> String { // 等价于 Function0<String> 类型

    override fun invoke(): String {
        return "Jay"
    }

}

//public interface myInterface extends Function0 {
//   @NotNull
//   String invoke();
//
//   @Metadata(
//      mv = {1, 5, 1},
//      k = 3
//   )
//   public static final class DefaultImpls {
//      @NotNull
//      public static String invoke(@NotNull myInterface $this) {
//         return "Jay";
//      }
//   }
//}

val myInter: () -> String = object : myInterface {}

fun testMyInter() {
    val kProperty0: KProperty0<() -> String> = ::myInter
    println(kProperty0.returnType) //() -> kotlin.String
}
