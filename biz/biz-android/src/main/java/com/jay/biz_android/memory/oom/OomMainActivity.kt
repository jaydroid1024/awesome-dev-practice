package com.jay.biz_android.memory.oom

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.jay.biz_android.R

class OomMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.biz_android_activity_oom_main)

        findViewById<Button>(R.id.btn_oom).setOnClickListener {
            for (i in 0..100) {
                val arg = arrayOfNulls<String>(100000)
                val toList = arg.toList()
                array.add(toList)
            }

        }
    }

    companion object {
        val array = ArrayList<List<String?>>()
    }
}