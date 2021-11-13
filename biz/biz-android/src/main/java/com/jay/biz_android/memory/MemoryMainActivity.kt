package com.jay.biz_android.memory

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARPath
import com.jay.biz_android.R
import com.jay.biz_android.memory.memory_shake.MemorySharkActivity
import com.jay.biz_android.memory.o4_reference.ReferenceType.testSoftReference
import com.jay.biz_android.memory.oom.OomMainActivity
import com.jay.biz_android.memory.yearclass.YearClassTestActivity

/*
//dvm最大可用内存：
adb shell getprop | grep dalvik.vm.heapsize
//单个程序限制最大可用内存：
adb shell getprop|grep heapgrowthlimit
我们可以输出我们App的内存使用情况概览：
adb shell dumpsys meminfo com.component.app_android
top 命令是 Linux 下常用的性能分析工具，能够 实时显示系统中各个进程的资源占用状况，类似于 Windows 的任务管理器。top 命令提供了 实时的对系统处理器的状态监视。它将 显示系统中 CPU 最“敏感”的任务列表。该命令可以按 CPU使用、内存使用和执行时间 对任务进行排序。
adb shell top -n 1

进程的内存概要
adb shell dumpsys meminfo
查看单个 App 进程的内存信息
dumpsys meminfo <pid> // 输出指定pid的某一进程
dumpsys meminfo --package <packagename> // 输出指定包名的进程，可能包含多个进程


 */
@Route(path = ARPath.PathAndroid.MEMORY_MAIN_ACTIVITY_PATH)
class MemoryMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.biz_android_activity_memory_main)


        getMemory()


        findViewById<Button>(R.id.btn_add).setOnClickListener {
            testSoftReference()
        }

        findViewById<Button>(R.id.btn_shake).setOnClickListener {
            startActivity(Intent(this, MemorySharkActivity::class.java))
        }

        findViewById<Button>(R.id.btn_year).setOnClickListener {
            startActivity(Intent(this, YearClassTestActivity::class.java))
        }

        findViewById<Button>(R.id.btn_oom).setOnClickListener {
            startActivity(Intent(this, OomMainActivity::class.java))
        }

    }

    fun getMemory() {
        //Dalvik 2.0 （ART） 2.0 之后虽然还是 Dalvik 当时虚拟机已经换成ART了
        val vm = System.getProperty("java.vm.name") + " " + System.getProperty("java.vm.version")
        Log.d("Jay", "vm: $vm") //Dalvik 2.1.0
        val info = findViewById<TextView>(R.id.tv_info)
        info.text = vm
        val M_1 = 1024 * 1024
        // 在做一些需要很多内存的任务前， 检查设备是否处于低内存状态、
        val memoryInfo = getMemoryInfo()
        info.append("\n memoryInfo: $memoryInfo")
        //内核可访问的总内存。 这基本上是设备的 RAM 大小，不包括内核以下的固定分配，如 DMA 缓冲区、基带 CPU 的 RAM 等
        info.append("\n memoryInfo-totalMem: ${memoryInfo.totalMem / M_1}")
        //系统上的可用内存。 这个数字不应该被认为是绝对的：由于内核的性质，这个内存的很大一部分实际上正在使用中，并且需要整个系统运行良好
        info.append("\n memoryInfo-availMem: ${memoryInfo.availMem / M_1}")
        //如果系统认为自己当前处于低内存情况，则设置为 true。
        info.append("\n memoryInfo-lowMemory: ${memoryInfo.lowMemory}")
        //我们认为内存不足并开始杀死后台服务和其他非无关进程的availMem阈值。
        info.append("\n memoryInfo-threshold: ${memoryInfo.threshold / M_1}")
        info.append("\n memoryInfo-foregroundAppThreshold: ${memoryInfo.foregroundAppThreshold / M_1}")
        info.append("\n memoryInfo-hiddenAppThreshold: ${memoryInfo.hiddenAppThreshold / M_1}")
        info.append("\n memoryInfo-visibleAppThreshold: ${memoryInfo.visibleAppThreshold / M_1}")
        info.append("\n memoryInfo-secondaryServerThreshold: ${memoryInfo.secondaryServerThreshold / M_1}")

        Log.d("Jay", "" + info.text.toString())
        //2021-10-09 18:07:25.901 D: Dalvik 2.1.0
        //     memoryInfo: android.app.ActivityManager$MemoryInfo@a2fb165
        //     memoryInfo-totalMem: 7640
        //     memoryInfo-availMem: 2568
        //     memoryInfo-lowMemory: false
        //     memoryInfo-threshold: 1080
        //     memoryInfo-foregroundAppThreshold: 72
        //     memoryInfo-hiddenAppThreshold: 1417SparseBooleanArray
        //     memoryInfo-visibleAppThreshold: 90
        //     memoryInfo-secondaryServerThreshold: 1080

        if (!memoryInfo.lowMemory) {
            // 做需要很多内存的任务
        }
    }

    // 获取 MemoryInfo 对象
    private fun getMemoryInfo(): ActivityManager.MemoryInfo {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        return ActivityManager.MemoryInfo().also { memoryInfo ->
            activityManager.getMemoryInfo(memoryInfo)
        }
    }


    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
    }
}
