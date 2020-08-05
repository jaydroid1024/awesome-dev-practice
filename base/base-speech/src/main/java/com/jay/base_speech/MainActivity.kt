package com.jay.base_speech

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.iflytek.voicedemo.XunFeiSpeechMainActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goToXunFeiSpeech(view: View) {
        startActivity(Intent(this, XunFeiSpeechMainActivity::class.java))

    }

    fun goToBaiDuSpeech(view: View) {
        startActivity(Intent(this, XunFeiSpeechMainActivity::class.java))

    }
}