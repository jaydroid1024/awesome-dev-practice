package com.jay.lib_kotlin.delegate

import com.jay.lib_kotlin.delegate.StackVarsKt
import kotlin.jvm.JvmStatic

/**
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/15
 */
class StackVarsKt {

    // instance变量
    private var x = 0

    fun stackAccess(`val`: Int) { // 访问和操作stack变量j
        var j = 0
        for (i in 0 until `val`) {
            j += 1
        }
    }

    fun instanceAccess(`val`: Int) { // 访问和操作instance变量x
        for (i in 0 until `val`) {
            x += 1
        }
    }

    fun staticAccess(`val`: Int) { // 访问和操作static变量staticX
        for (i in 0 until `val`) {
            staticX += 1
        }
    }

    fun instanceAccessPro(`val`: Int) { // 访问和操作instance变量x
        var tempX = x
        for (i in 0 until `val`) {
            tempX += 1
        }
        x = tempX
    }

    fun staticAccessPro(`val`: Int) { // 访问和操作static变量staticX
        var tempStaticX = staticX
        for (i in 0 until `val`) {
            tempStaticX += 1
        }
        staticX = tempStaticX
    }

    companion object {

        // static 变量
        private var staticX = 0

        @JvmStatic
        fun main(args: Array<String>) {
            val time = 1000000
            val stackVars = StackVarsKt()
            val t1 = System.currentTimeMillis()
            stackVars.stackAccess(time)
            val t2 = System.currentTimeMillis()
            println("time1:" + (t2 - t1))
            val t3 = System.currentTimeMillis()
            stackVars.instanceAccess(time)
            val t4 = System.currentTimeMillis()
            println("time2:" + (t4 - t3))
            val t5 = System.currentTimeMillis()
            stackVars.staticAccess(time)
            val t6 = System.currentTimeMillis()
            println("time3:" + (t6 - t5))
            val t7 = System.currentTimeMillis()
            stackVars.instanceAccessPro(time)
            val t8 = System.currentTimeMillis()
            println("time4:" + (t8 - t7))
            val t9 = System.currentTimeMillis()
            stackVars.staticAccessPro(time)
            val t10 = System.currentTimeMillis()
            println("time5:" + (t10 - t9))


            //todo 局部变量将性能提高了25%以上

            // i += 1

            // val time = 1_0000_0000
            // 在 PC 设备上测试的结果
            // time1:3
            // time2:20
            // time3:27
            // time4:3
            // time5:14

            // 在 Android 设备上测试的结果
            // time1:551
            // time2:1309
            // time3:3528
            // time4:586
            // time5:550
            // 导致了APP ANR

            // int time = 100_0000;
            // 在 PC 设备上测试的结果
            // time1:1
            // time2:4
            // time3:3
            // time4:2
            // time5:1

            // 在 Android 设备上测试的结果
            // time1:6
            // time2:15
            // time3:42
            // time4:6
            // time5:6
        }
    }
}