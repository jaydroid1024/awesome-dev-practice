package com.jay.biz_mvvm

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARPath
import com.jay.base_component.widget.demolist.DemoListEntity
import kotlinx.android.synthetic.main.biz_mvvm_activity_map_main.*

@Route(path = ARPath.PathMVVM.MVVM_ACTIVITY_PATH)
class MvvmMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.biz_mvvm_activity_map_main)
        dv_demo.setSpanCount(1)
        dv_demo.setDemoListData(demoItemData)
        dv_demo.setTopText("Android MVVM 模式")
    }

    private val demoItemData: ArrayList<DemoListEntity>
        get() = arrayListOf(
            DemoListEntity(headerTitle = "MVVM 架构演示 Demo"),
            DemoListEntity("玩 Android 登录注册", ARPath.PathUser.LOGIN_ACTIVITY_PATH),
        )
}
