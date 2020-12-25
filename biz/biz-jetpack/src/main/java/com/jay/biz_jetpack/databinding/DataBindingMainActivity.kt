package com.jay.biz_jetpack.databinding

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARHelper
import com.jay.base_component.arouter.ARPath
import com.jay.base_component.widget.demolist.DemoListEntity
import com.jay.biz_jetpack.R
import com.jay.biz_jetpack.databinding.data.User
import kotlinx.android.synthetic.main.biz_jetpack_activity_jetpack_main.*

@Route(path = ARPath.PathJetPack.DATA_BINDING_ACTIVITY_PATH)
class DataBindingMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.biz_jetpack_activity_jetpack_main)
        setDemoList()
    }

    private fun setDemoList() {
        dv_demo.setSpanCount(1)
        dv_demo.setDemoListData(demoItemData)
        dv_demo.setTopText(" DataBinding 组件")
    }

    private val demoItemData: ArrayList<DemoListEntity>
        get() = arrayListOf(
            DemoListEntity(headerTitle = "DataBinding"),
            DemoListEntity("简单使用", ARPath.PathJetPack.DATA_BINDING_USE_ACTIVITY_PATH),
            DemoListEntity("使用可观察的数据对象", ARPath.PathJetPack.DATA_BINDING_OBSERVABLE_ACTIVITY_PATH),
        )
}