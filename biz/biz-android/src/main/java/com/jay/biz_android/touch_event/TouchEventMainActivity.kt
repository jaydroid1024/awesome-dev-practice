package com.jay.biz_android.touch_event

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARPath
import com.jay.base_component.widget.demolist.DemoListEntity
import com.jay.biz_android.R
import kotlinx.android.synthetic.main.biz_android_activity_android_main.*

@Route(path = ARPath.PathAndroid.TOUCH_EVENT_MAIN_ACTIVITY_PATH)
class TouchEventMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.biz_android_activity_touch_event)
        setDemoList()
    }

    private fun setDemoList() {
        dv_demo.setSpanCount(1)
        dv_demo.setDemoListData(demoItemData)
        dv_demo.setTopText("Android 事件问题")
    }

    private val demoItemData: ArrayList<DemoListEntity>
        get() = arrayListOf(
            DemoListEntity(headerTitle = "Android 事件问题"),
            DemoListEntity("事件分发机制", ARPath.PathAndroid.TOUCH_EVENT_DISPATCH_ACTIVITY_PATH),
        )
}