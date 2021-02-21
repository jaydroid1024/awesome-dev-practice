package com.jay.biz_mvvm.room

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARPath
import com.jay.base_component.widget.demolist.DemoListEntity
import com.jay.biz_mvvm.R
import kotlinx.android.synthetic.main.biz_jetpack_activity_jetpack_main.*

@Route(path = ARPath.PathJetPack.ROOM_ACTIVITY_PATH)
class RoomMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.biz_jetpack_activity_jetpack_main)
        setDemoList()
    }

    private fun setDemoList() {
        dv_demo.setSpanCount(1)
        dv_demo.setDemoListData(demoItemData)
        dv_demo.setTopText(" Room 组件")
    }

    private val demoItemData: ArrayList<DemoListEntity>
        get() = arrayListOf(
            DemoListEntity(headerTitle = "Room"),
            DemoListEntity("简单使用", ARPath.PathJetPack.ROOM_USE_ACTIVITY_PATH),
        )
}
