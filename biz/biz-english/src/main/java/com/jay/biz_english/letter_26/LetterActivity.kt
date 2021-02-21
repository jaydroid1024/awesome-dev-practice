package com.jay.biz_english.letter_26

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARPath
import com.jay.biz_english.R

@Route(path = ARPath.PathEnglish.LETTER_ACTIVITY_PATH)
class LetterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        super.onCreate(savedInstanceState)
        setContentView(R.layout.biz_english_activity_letter)
    }
}