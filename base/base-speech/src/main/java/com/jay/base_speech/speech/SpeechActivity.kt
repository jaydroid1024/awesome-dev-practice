package com.jay.base_speech.speech

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARPath
import com.jay.base_speech.R

@Route(path = ARPath.PathSearch.SEARCH_ACTIVITY_PATH)
class SpeechActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speech_tts)
    }
}