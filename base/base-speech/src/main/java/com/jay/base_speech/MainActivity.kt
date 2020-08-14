package com.jay.base_speech

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.iflytek.mscv5plusdemo.XunfeiMainActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goToXunFeiSpeech(view: View) {
        startActivity(Intent(this, XunfeiMainActivity::class.java))

    }

    fun goToBaiDuSpeech(view: View) {
        startActivity(Intent(this, XunfeiMainActivity::class.java))

    }
}