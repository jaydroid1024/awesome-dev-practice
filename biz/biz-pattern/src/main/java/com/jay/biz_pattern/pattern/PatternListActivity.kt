package com.jay.biz_pattern.pattern

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARPath
import com.jay.base_component.widget.demolist.DemoListEntity
import com.jay.base_component.widget.demolist.DemoListView
import com.jay.base_speech.media.MediaUtil
import com.jay.biz_pattern.data.PatternDataHelper.demoItemData
import com.jay.biz_pattern.R
import kotlinx.android.synthetic.main.biz_pattern_activity_phonetic_list.*

@Route(path = ARPath.PathPattern.PATTERN_LIST_ACTIVITY_PATH)
class PatternListActivity : AppCompatActivity(), DemoListView.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.biz_pattern_activity_phonetic_list)
        dv_demo.setSpanCount(4)
        dv_demo.setDemoListData(demoItemData())
        dv_demo.setTopText("设计模式", "")
        dv_demo.setOnItemClickListener(this)
    }


    override fun onItemClick(item: DemoListEntity) {

    }


}