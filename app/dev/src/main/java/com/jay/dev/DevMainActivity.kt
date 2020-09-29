package com.jay.dev

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARPath
import com.jay.base_component.widget.demolist.DemoListEntity
import com.jay.dev.databinding.ActivityDevBinding

/**
 * Android 开发最佳实践系列知识体系
1. 基本技能-计算机系统&网络知识体系
1. 内功心法-算法与思想知识体系
1. 必修之技-Java知识体系
1. 未来可期-Kotlin知识体系
1. 乘风破浪-Android知识体系
1. 勇攀高峰-封神大招知识体系
 */
@Route(path = ARPath.PathDev.DEV_ACTIVITY_PATH)
class DevMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDevBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDevBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.dvDemo.setDemoListData(homeItemData)
    }

    private val homeItemData: ArrayList<DemoListEntity>
        get() = arrayListOf(
            DemoListEntity(headerTitle = "基本技能-计算机系统&网络知识体系"),
            DemoListEntity("电影", ARPath.PathMovie.DEMO_ACTIVITY_PATH, R.mipmap.ic_bald, "dfdfd"),
            DemoListEntity("电影详情", ARPath.PathJava.JAVA_ACTIVITY_PATH, R.mipmap.ic_bald, "1"),
            DemoListEntity("电影2", ARPath.PathMovie.MOVIE_ACTIVITY_PATH, R.mipmap.ic_bald, "2"),
            DemoListEntity("电影3", ARPath.PathMovie.MOVIE_ACTIVITY_PATH, R.mipmap.ic_bald, "3"),
            DemoListEntity("语音识别", ARPath.PathSpeech.MAIN_SPEECH_ACTIVITY_PATH, R.mipmap.ic_bald),
            DemoListEntity("计算机", ARPath.PathDetail.DETAIL_ACTIVITY_PATH, R.mipmap.ic_bald),
            DemoListEntity("操作系统", ARPath.PathDev.DEV_ACTIVITY_PATH, R.mipmap.ic_bald),
            DemoListEntity("网络", ARPath.PathDev.DEV_ACTIVITY_PATH, R.mipmap.ic_bald),

            DemoListEntity(headerTitle = "内功心法-算法与思想知识体系"),
            DemoListEntity("算法", ARPath.PathDev.DEV_ACTIVITY_PATH, R.mipmap.ic_bald),
            DemoListEntity("设计模式", ARPath.PathDev.DEV_ACTIVITY_PATH, R.mipmap.ic_bald),
            DemoListEntity("架构思想", ARPath.PathDev.DEV_ACTIVITY_PATH, R.mipmap.ic_bald),

            DemoListEntity(headerTitle = "必修之技-Java知识体系"),
            DemoListEntity("核心语法", ARPath.PathDev.DEV_ACTIVITY_PATH, R.mipmap.ic_bald),
            DemoListEntity("JVM虚拟机", ARPath.PathDev.DEV_ACTIVITY_PATH, R.mipmap.ic_bald),

            DemoListEntity(headerTitle = "未来可期-Kotlin知识体系"),
            DemoListEntity("核心语法", ARPath.PathDev.DEV_ACTIVITY_PATH, R.mipmap.ic_bald),
            DemoListEntity("协程", ARPath.PathDev.DEV_ACTIVITY_PATH, R.mipmap.ic_bald),

            DemoListEntity(headerTitle = "乘风破浪-Android知识体系"),
            DemoListEntity("核心语法", ARPath.PathDev.DEV_ACTIVITY_PATH, R.mipmap.ic_bald),
            DemoListEntity("DVM虚拟机", ARPath.PathDev.DEV_ACTIVITY_PATH, R.mipmap.ic_bald),

            DemoListEntity(headerTitle = "勇攀高峰-封神大招知识体系"),
            DemoListEntity("跨平台", ARPath.PathDev.DEV_ACTIVITY_PATH, R.mipmap.ic_bald),
            DemoListEntity("小程序", ARPath.PathDev.DEV_ACTIVITY_PATH, R.mipmap.ic_bald)
        )
}

