package com.jay.biz_jetpack.databinding

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARPath
import com.jay.biz_jetpack.R

@Route(path = ARPath.PathJetPack.DATA_BINDING_OBSERVABLE_ACTIVITY_PATH)
class DBObservableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.biz_jetpack_activity_data_binding_observabe)
    }


}