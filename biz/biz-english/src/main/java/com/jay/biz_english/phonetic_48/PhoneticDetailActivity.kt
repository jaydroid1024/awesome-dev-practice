package com.jay.biz_english.phonetic_48

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARPath
import com.jay.base_speech.media.MediaUtil
import com.jay.base_speech.speech.SpeechHelper
import com.jay.biz_english.R
import com.jay.biz_english.data.EnConstants
import com.jay.biz_english.data.EnDataHelper.getCPhoneticList
import com.jay.biz_english.data.EnDataHelper.getPhoneticList
import com.jay.biz_english.data.EnDataHelper.getVPhoneticList
import com.jay.biz_english.data.PhoneticItem
import com.moxun.tagcloudlib.view.TagCloudView
import kotlinx.android.synthetic.main.biz_english_activity_phonetic.*

@Route(path = ARPath.PathEnglish.PHONETIC_ACTIVITY_PATH)
class PhoneticDetailActivity : AppCompatActivity() {
    var textTagsAdapter: TextTagsAdapter? = null
    var checkedPhoneticItem: PhoneticItem? = null
    var isEnd: Boolean = false
    var randomNum: Int = -1
    var handler: Handler = Handler()
    val arrayList = mutableListOf<PhoneticItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.biz_english_activity_phonetic_detail)
        arrayList.addAll(getPhoneticList()!!)
        textTagsAdapter = TextTagsAdapter(arrayList!!)
        tag_cloud.setAdapter(textTagsAdapter)


        handler.postDelayed(run, 100)

        Handler().postDelayed({
            SpeechHelper.startSpeech("")
        }, 1000)

        Handler().postDelayed({
            updateIndex()
        }, 3000)
    }

    private val run: Runnable = Runnable {
    }



    private fun updateIndex() {
        isEnd = true
        tag_cloud.autoScrollMode = TagCloudView.MODE_DECELERATE
        val listTemp = getPhoneticList()
        randomNum = (0 until listTemp!!.size).random()
        arrayList.clear()
        checkedPhoneticItem = listTemp[randomNum]
        checkedPhoneticItem?.isChecked = true
        arrayList.addAll(listTemp)
        textTagsAdapter?.notifyDataSetChanged()


    }

}