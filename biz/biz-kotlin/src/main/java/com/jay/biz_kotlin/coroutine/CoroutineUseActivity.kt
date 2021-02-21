package com.jay.biz_kotlin.coroutine

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARPath
import com.jay.biz_kotlin.R

@Route(path = ARPath.PathKotlin.COROUTINE_USE_ACTIVITY_PATH)
class CoroutineUseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.biz_kotlin_activity_coroutine_use)

    }

}
