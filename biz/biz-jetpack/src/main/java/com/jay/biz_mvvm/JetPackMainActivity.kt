package com.jay.biz_mvvm

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARHelper
import com.jay.base_component.arouter.ARPath
import com.jay.base_component.widget.demolist.DemoListEntity
import kotlinx.android.synthetic.main.biz_jetpack_activity_jetpack_main.*

@Route(path = ARPath.PathJetPack.JET_PACK_MAIN_ACTIVITY_PATH)
class JetPackMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.biz_jetpack_activity_jetpack_main)
        setDemoList()
        //todo
        ARHelper.routerTo(ARPath.PathJetPack.ROOM_USE_ACTIVITY_PATH)
    }

    private fun setDemoList() {
        dv_demo.setSpanCount(1)
        dv_demo.setDemoListData(demoItemData)
        dv_demo.setTopText("Android JetPack 组件")
    }

    private val demoItemData: ArrayList<DemoListEntity>
        get() = arrayListOf(
            DemoListEntity(headerTitle = "JetPack 组件"),
            DemoListEntity("Room 持久性库", ARPath.PathJetPack.ROOM_ACTIVITY_PATH),
            DemoListEntity("依赖注入-Hint", ARPath.PathJetPack.HINT_ACTIVITY_PATH),
            DemoListEntity("数据绑定库-DataBinding", ARPath.PathJetPack.DATA_BINDING_ACTIVITY_PATH),
        )
}
