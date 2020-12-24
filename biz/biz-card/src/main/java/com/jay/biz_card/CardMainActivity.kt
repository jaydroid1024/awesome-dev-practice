package com.jay.biz_card

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARPath
import com.jay.base_component.widget.demolist.DemoListEntity
import kotlinx.android.synthetic.main.biz_card_activity_card_main.*

@Route(path = ARPath.PathCard.CARD_ACTIVITY_PATH)
class CardMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.biz_card_activity_card_main)
        dv_demo.setSpanCount(1)
        dv_demo.setDemoListData(demoItemData)
        dv_demo.setTopText("测试卡片")
    }

    private val demoItemData: ArrayList<DemoListEntity>
        get() = arrayListOf(
            DemoListEntity(headerTitle = "身份证"),
            DemoListEntity("身份证模拟", ARPath.PathCard.CARD_ID_ACTIVITY_PATH),
            DemoListEntity("身份证识别", ARPath.PathCard.CARD_RECOGNITION_ACTIVITY_PATH)
        )
}
