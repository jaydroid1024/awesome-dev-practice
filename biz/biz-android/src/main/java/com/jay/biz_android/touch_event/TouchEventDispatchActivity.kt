package com.jay.biz_android.touch_event

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARPath
import com.jay.biz_android.R

@Route(path = ARPath.PathAndroid.TOUCH_EVENT_DISPATCH_ACTIVITY_PATH)
class TouchEventDispatchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.biz_android_activity_touch_event_dispatch)
    }
}