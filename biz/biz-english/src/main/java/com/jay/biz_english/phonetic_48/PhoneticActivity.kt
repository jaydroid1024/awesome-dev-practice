package com.jay.biz_english.phonetic_48

import android.graphics.Color
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
import com.jay.biz_english.data.EnDataHelper.getPhoneticList
import com.jay.biz_english.data.PhoneticItem
import com.moxun.tagcloudlib.view.TagCloudView
import jp.wasabeef.blurry.Blurry
import kotlinx.android.synthetic.main.biz_english_activity_phonetic.*

@Route(path = ARPath.PathEnglish.PHONETIC_ACTIVITY_PATH)
class PhoneticActivity : AppCompatActivity() {
    var textTagsAdapter: TextTagsAdapter? = null
    var checkedPhoneticItem: PhoneticItem? = null
    var isEnd: Boolean = false
    var title: String = "一天掌握一个音标"
    var randomNum: Int = -1
    var handler: Handler = Handler()
    val arrayList = mutableListOf<PhoneticItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.biz_english_activity_phonetic)
        arrayList.addAll(getPhoneticList()!!)
        textTagsAdapter = TextTagsAdapter(arrayList!!)
        tag_cloud.setAdapter(textTagsAdapter)


        Blurry.with(this)
            .radius(10)
            .sampling(8)
            .color(Color.argb(66, 255, 255, 0))
            .async()
            .animate(500)
            .onto(ll_v)

        initRandomP()
        handler.postDelayed(run, 100)

        Handler().postDelayed({

            SpeechHelper.startSpeech(title)
        }, 1000)

        Handler().postDelayed({
            updateIndex()
        }, 3000)

        initTitle()
    }

    private fun initTitle() {
        htv_title_line.animateText(title)
        htv_title_line.setAnimationListener {
            it.animateText(title)
        }
        htv_title_rainbow.animateText(title)


    }

    private val run: Runnable = Runnable {
        initRandomP()
    }


    private fun initRandomP() {
        val list = getPhoneticList()
        val random = (0 until list!!.size).random()
        tv_c.text = list[random].name
        checkedPhoneticItem = list[random]
        if (checkedPhoneticItem?.type1 == EnConstants.PhoneticType.TYPE_CONSONANT_1) {
            tv_c.setBackgroundResource(R.drawable.base_common_bg_circle_dark_radius_yellow)
        } else {
            tv_c.setBackgroundResource(R.drawable.base_common_bg_circle_dark_radius_white)
        }
        if (!isEnd) {
            handler.postDelayed(run, 100)
        } else {
            MediaUtil.display(checkedPhoneticItem?.url, object : MediaUtil.OnCompletionListener {
                override fun onCompletion(mp: MediaPlayer?) {
                    Handler().postDelayed({
//                        this@PhoneticActivity.finish()
                    }, 500)
                }
            })


        }

    }

    private fun updateIndex() {
        isEnd = true
        tag_cloud.autoScrollMode = TagCloudView.MODE_UNIFORM
        val listTemp = getPhoneticList()
        randomNum = (0 until listTemp!!.size).random()
        arrayList.clear()
        checkedPhoneticItem = listTemp[randomNum]
        checkedPhoneticItem?.isChecked = true
        arrayList.addAll(listTemp)
        textTagsAdapter?.notifyDataSetChanged()


    }

}