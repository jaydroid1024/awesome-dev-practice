package com.jay.biz_android

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARPath
import com.jay.base_component.widget.demolist.DemoListEntity
import kotlinx.android.synthetic.main.biz_android_activity_android_main.*

@Route(path = ARPath.PathAndroid.ANDROID_MAIN_ACTIVITY_PATH)
class AndroidMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.biz_android_activity_android_main)
        setDemoList()
    }

    private fun setDemoList() {
        dv_demo.setSpanCount(1)
        dv_demo.setDemoListData(demoItemData)
        dv_demo.setTopText("Android")
    }

    private val demoItemData: ArrayList<DemoListEntity>
        get() = arrayListOf(
            DemoListEntity(headerTitle = "Android 基础"),
            DemoListEntity("Android 事件问题", ARPath.PathAndroid.TOUCH_EVENT_MAIN_ACTIVITY_PATH),
        )
}
