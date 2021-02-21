package com.jay.biz_english

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARPath
import com.jay.base_component.widget.demolist.DemoListEntity
import kotlinx.android.synthetic.main.biz_english_activity_english.*

@Route(path = ARPath.PathEnglish.ENGLISH_ACTIVITY_PATH)
class EnglishMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.biz_english_activity_english)
        dv_demo.setSpanCount(1)
        dv_demo.setDemoListData(homeItemData)
        dv_demo.setTopText("猿舌音语", "一个怀揣好奇，认真聆听世界的码仔，主要分享：语言学习、音乐、声音、优质音频安利等内容")
    }

    private val homeItemData: ArrayList<DemoListEntity>
        get() = arrayListOf(
            DemoListEntity(headerTitle = "听其音，临其境"),
            DemoListEntity("国际音标48各个突破", ARPath.PathEnglish.PHONETIC_ACTIVITY_PATH),
            DemoListEntity("国际音标列表", ARPath.PathEnglish.PHONETIC_LIST_ACTIVITY_PATH),
            DemoListEntity("英语字母26", ARPath.PathEnglish.LETTER_ACTIVITY_PATH)
        )

}