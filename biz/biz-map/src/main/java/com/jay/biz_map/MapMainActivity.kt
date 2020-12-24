package com.jay.biz_map

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARPath
import com.jay.base_component.widget.demolist.DemoListEntity
import kotlinx.android.synthetic.main.biz_map_activity_map_main.*

@Route(path = ARPath.PathMap.MAP_ACTIVITY_PATH)
class MapMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.biz_map_activity_map_main)
        dv_demo.setSpanCount(1)
        dv_demo.setDemoListData(demoItemData)
        dv_demo.setTopText("地图")
    }

    private val demoItemData: ArrayList<DemoListEntity>
        get() = arrayListOf(
            DemoListEntity(headerTitle = "地图"),
            DemoListEntity("百度", ARPath.PathMap.BAIDU_MAP_ACTIVITY_PATH),
            DemoListEntity("高德", ARPath.PathMap.GAODE_MAP_ACTIVITY_PATH)
        )
}
