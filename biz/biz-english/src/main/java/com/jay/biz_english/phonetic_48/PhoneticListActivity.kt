package com.jay.biz_english.phonetic_48

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARPath
import com.jay.base_component.widget.demolist.DemoListEntity
import com.jay.base_component.widget.demolist.DemoListView
import com.jay.base_speech.media.MediaUtil
import com.jay.biz_english.R
import com.jay.biz_english.data.EnDataHelper
import kotlinx.android.synthetic.main.biz_english_activity_english.*

@Route(path = ARPath.PathEnglish.PHONETIC_LIST_ACTIVITY_PATH)
class PhoneticListActivity : AppCompatActivity(), DemoListView.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.biz_english_activity_phonetic_list)
        dv_demo.setSpanCount(4)
        dv_demo.setDemoListData(homeItemData())
        dv_demo.setTopText("国际音标", "")
        dv_demo.setOnItemClickListener(this)
    }


    fun homeItemData(): ArrayList<DemoListEntity> {
        val list = ArrayList<DemoListEntity>()
        list.add(DemoListEntity(headerTitle = "元音"))
        val listV = EnDataHelper.getVPhoneticList()
        val listC = EnDataHelper.getCPhoneticList()
        listV?.forEach {
            list.add(DemoListEntity(it.name, it.url, imageResource = -1))
        }
        list.add(DemoListEntity(headerTitle = "辅音"))
        listC?.forEach {
            list.add(DemoListEntity(it.name, it.url, imageResource = -1))
        }
        return list

    }

    override fun onItemClick(item: DemoListEntity) {
        MediaUtil.display(item?.activityPath, object : MediaUtil.OnCompletionListener {
            override fun onCompletion(mp: MediaPlayer?) {

            }
        })
    }


}