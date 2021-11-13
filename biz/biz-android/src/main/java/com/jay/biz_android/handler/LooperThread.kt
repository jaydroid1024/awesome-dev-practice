package com.jay.biz_android.handler

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import com.jay.biz_android.handler.HandlerMainActivity.Companion.TAG

/**
 * 子线程创建自己的Handler
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/24
 */
class LooperThread : Thread() {
    var mLooper: Looper? = null
    override fun run() {
        Looper.prepare()
        mLooper = Looper.myLooper()
        Looper.loop()
    }
}

class LoopTHTest {

    var h1: Handler? = null
    var h2: Handler? = null
    var looperThread: LooperThread? = null
    init {
        looperThread = LooperThread()
        looperThread?.start()
    }
}


class MyH(mLooper: Looper?) : Handler(mLooper, CallBack()) {
    override fun handleMessage(msg: Message?) {
        Log.d(TAG, "h2-Handler-message | Handler:${msg?.target.hashCode()}，message:$msg，")
    }
}

class CallBack : Handler.Callback {
    override fun handleMessage(msg: Message?): Boolean {
        Log.d(TAG, "h3-CallBack-message | Handler:${msg?.target.hashCode()}，message:$msg，")
        return true ///dispatchMessage 时如果 返回 true 处理完这个回调就结束，返回false还需继续Handler 的 handleMessage
    }
}

