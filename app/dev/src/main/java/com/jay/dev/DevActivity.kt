package com.jay.dev

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.jay.base_component.arouter.ARHelper
import com.jay.base_component.arouter.ARPath
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
class DevActivity : AppCompatActivity(), OnItemClickListener {
    private lateinit var binding: ActivityDevBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDevBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.adapter = homeAdapter
    }

    /**
     * RV适配器
     */
    private val homeAdapter by lazy {
        HomeAdapter(homeItemData).apply {
            animationEnable = true

            val top = layoutInflater.inflate(R.layout.top_view, binding.recyclerView, false)
            addHeaderView(top)
            setOnItemClickListener(this@DevActivity)
        }
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        val item = adapter.data[position] as HomeEntity
        if (!item.isHeader) {
            ARHelper.routerTo(item.activityPath)
        }
    }

    private val homeItemData: ArrayList<HomeEntity>
        get() = arrayListOf(
            HomeEntity(headerTitle = "基本技能-计算机系统&网络知识体系"),
            HomeEntity("WEB", ARPath.PathWeb.WEB_TEST_ACTIVITY_PATH, R.mipmap.ic_bald),
            HomeEntity("FILE", ARPath.PathWeb.FILE_VIEW_TEST_ACTIVITY_PATH, R.mipmap.ic_bald),
            HomeEntity("计算机", ARPath.PathDetail.DETAIL_ACTIVITY_PATH, R.mipmap.ic_bald),
            HomeEntity("操作系统", ARPath.PathDev.DEV_ACTIVITY_PATH, R.mipmap.ic_bald),
            HomeEntity("网络", ARPath.PathDev.DEV_ACTIVITY_PATH, R.mipmap.ic_bald),

            HomeEntity(headerTitle = "内功心法-算法与思想知识体系"),
            HomeEntity("算法", ARPath.PathDev.DEV_ACTIVITY_PATH, R.mipmap.ic_bald),
            HomeEntity("设计模式", ARPath.PathDev.DEV_ACTIVITY_PATH, R.mipmap.ic_bald),
            HomeEntity("架构思想", ARPath.PathDev.DEV_ACTIVITY_PATH, R.mipmap.ic_bald),

            HomeEntity(headerTitle = "必修之技-Java知识体系"),
            HomeEntity("核心语法", ARPath.PathDev.DEV_ACTIVITY_PATH, R.mipmap.ic_bald),
            HomeEntity("JVM虚拟机", ARPath.PathDev.DEV_ACTIVITY_PATH, R.mipmap.ic_bald),

            HomeEntity(headerTitle = "未来可期-Kotlin知识体系"),
            HomeEntity("核心语法", ARPath.PathDev.DEV_ACTIVITY_PATH, R.mipmap.ic_bald),
            HomeEntity("协程", ARPath.PathDev.DEV_ACTIVITY_PATH, R.mipmap.ic_bald),

            HomeEntity(headerTitle = "乘风破浪-Android知识体系"),
            HomeEntity("核心语法", ARPath.PathDev.DEV_ACTIVITY_PATH, R.mipmap.ic_bald),
            HomeEntity("DVM虚拟机", ARPath.PathDev.DEV_ACTIVITY_PATH, R.mipmap.ic_bald),

            HomeEntity(headerTitle = "勇攀高峰-封神大招知识体系"),
            HomeEntity("跨平台", ARPath.PathDev.DEV_ACTIVITY_PATH, R.mipmap.ic_bald),
            HomeEntity("小程序", ARPath.PathDev.DEV_ACTIVITY_PATH, R.mipmap.ic_bald)


        )
}

