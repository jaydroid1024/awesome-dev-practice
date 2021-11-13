package com.jay.biz_android.memory.memory_shake

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.jay.biz_android.R

class MemorySharkActivity : AppCompatActivity() {

    val h: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            for (i in 0..100) {
                val arg = arrayOfNulls<String>(100000)
            }
            Log.d("Jay","handleMessage")
            msg?.target?.sendEmptyMessageDelayed(1, 30)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.biz_android_activity_memory_shark)

        findViewById<Button>(R.id.btn_shake).setOnClickListener {
            h.sendEmptyMessageDelayed(1, 30)
        }
    }


}