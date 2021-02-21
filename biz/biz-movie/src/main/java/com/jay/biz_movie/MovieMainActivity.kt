package com.jay.biz_movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARPath
import com.jay.base_component.widget.demolist.DemoListEntity
import com.jay.biz_movie.data.MovieConstants
import kotlinx.android.synthetic.main.biz_movie_demo.*

/**
 * Android 开发最佳实践系列知识体系
1. 基本技能-计算机系统&网络知识体系
1. 内功心法-算法与思想知识体系
1. 必修之技-Java知识体系
1. 未来可期-Kotlin知识体系
1. 乘风破浪-Android知识体系
1. 勇攀高峰-封神大招知识体系
 */
@Route(path = ARPath.PathMovie.DEMO_ACTIVITY_PATH)
class MovieMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.biz_movie_demo)
        dv_demo.setSpanCount(1)
        dv_demo.setDemoListData(homeItemData)
        dv_demo.setTopText("猿舌电影", "一个怀揣好奇，用心讲好故事的码仔，主要分享：影视剪辑、解说、观点、优质影视剧安利等内容")
    }

    private val homeItemData: ArrayList<DemoListEntity>
        get() = arrayListOf(
            DemoListEntity(headerTitle = "用心剪好每一个故事"),
            DemoListEntity(
                MovieConstants.RED.建国大业,
                ARPath.PathMovie.MOVIE_DETAIL_ACTIVITY_PATH,
                R.mipmap.ic_bald
            ),
            DemoListEntity("漫威电影宇宙", ARPath.PathMovie.MOVIE_ACTIVITY_PATH, R.mipmap.ic_bald),
            DemoListEntity(
                MovieConstants.MCU.钢铁侠2,
                ARPath.PathMovie.MOVIE_DETAIL_ACTIVITY_PATH,
                R.mipmap.ic_bald
            )

        )
}

