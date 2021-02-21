package com.jay.biz_kotlin.coroutine

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARPath
import com.jay.base_component.widget.demolist.DemoListEntity
import com.jay.biz_kotlin.R
import kotlinx.android.synthetic.main.biz_kotlin_activity_kotlin_main.*

@Route(path = ARPath.PathKotlin.COROUTINE_MAIN_ACTIVITY_PATH)
class CoroutineMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.biz_kotlin_activity_kotlin_main)
        setDemoList()
    }

    private fun setDemoList() {
        dv_demo.setSpanCount(1)
        dv_demo.setDemoListData(demoItemData)
        dv_demo.setTopText("Kotlin-协程")
    }

    private val demoItemData: ArrayList<DemoListEntity>
        get() = arrayListOf(
            DemoListEntity(headerTitle = "Kotlin-协程"),
            DemoListEntity("协程的基本使用", ARPath.PathKotlin.COROUTINE_USE_ACTIVITY_PATH),
        )
}
