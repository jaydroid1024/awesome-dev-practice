package com.jay.biz_pattern

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARPath
import com.jay.base_component.widget.demolist.DemoListEntity
import kotlinx.android.synthetic.main.biz_pattern_activity_pattern_main.*

@Route(path = ARPath.PathPattern.PATTERN_ACTIVITY_PATH)
class PatternMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.biz_pattern_activity_pattern_main)
        dv_demo.setSpanCount(1)
        dv_demo.setDemoListData(demoItemData)
        dv_demo.setTopText("设计模式", "设计模式是软件设计中常见问题的典型解决方案。 每个模式就像一张蓝图， 你可以通过对其进行定制来解决代码中的特定设计问题。")
    }

    private val demoItemData: ArrayList<DemoListEntity>
        get() = arrayListOf(
            DemoListEntity(headerTitle = "设计模式"),
            DemoListEntity("23种设计模式", ARPath.PathPattern.PATTERN_LIST_ACTIVITY_PATH),
            DemoListEntity("23种设计模式", ARPath.PathPattern.PATTERN_LIST_ACTIVITY_PATH)
        )
}