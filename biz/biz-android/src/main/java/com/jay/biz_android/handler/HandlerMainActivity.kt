package com.jay.biz_android.handler

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.*
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARPath
import com.jay.biz_android.R


/*
 使用Handler方式进行异步消息处理主要由Message，Handler，MessageQueue，Looper四部分组成：
（1）Message，线程之间传递的消息，用于不同线程之间的数据交互。Message中的what字段用来标记区分多个消息，arg1、arg2 字段用来传递int类型的数据，obj可以传递任意类型的字段。
（2）Handler，用于发送和处理消息。其中的sendMessage()用来发送消息，handleMessage()用于消息处理，进行相应的UI操作。
（3）MessageQueue，消息队列（先进先出），用于存放Handler发送的消息，一个线程只有一个消息队列。
（4）Looper，可以理解为消息队列的管理者，当发现MessageQueue中存在消息，Looper就会将消息传递到handleMessage()方法中，同样，一个线程只有一个Looper。
 */

@Route(path = ARPath.PathAndroid.HANDLER_MAIN_ACTIVITY_PATH)
class HandlerMainActivity : AppCompatActivity() {

    val UPDATE = 0x1
    var tv: TextView? = null
    var tv2: TextView? = null
    val looperThread: LooperThread? = null

    //消息处理者,创建一个Handler的子类对象,目的是重写Handler的处理消息的方法(handleMessage())
    private val handler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                UPDATE -> tv?.text = msg.arg1.toString()
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.biz_android_activity_handler)
        tv = findViewById(R.id.tv)
        tv2 = findViewById(R.id.tv2)

//        startLoopThread()
        testSameWhatToManyHandler()

        blockTest()

        //postInvalidate() 子线程间接更新UI 通过Handler 发送消息-处理消息-执行invalidate方法
        tv2?.postInvalidate()
        tv2?.invalidate()


    }

    var loopTHTest: LoopTHTest? = null
    private fun startLoopThread() {
        loopTHTest = LoopTHTest()

    }


    private fun post() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                Toast.makeText(this@HandlerMainActivity, "延迟结束", Toast.LENGTH_LONG).show()
            }
        }, 3000)
    }


    fun send() {
        Thread {
            for (i in 3 downTo 1) {
                val msg = Message()
                msg.what = UPDATE
                msg.arg1 = i
                handler.sendMessage(msg)
                try {
                    Thread.sleep(1000) //休眠1秒
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                //打印log
                Log.i(TAG, "$this-$i")
            }
            //计时结束后跳转到其他界面
            runOnUiThread {
                Toast.makeText(this, "计时结束后跳转到其他界面", Toast.LENGTH_LONG).show()
            }
            //添加finish方法在任务栈中销毁倒计时界面，使新开界面在回退时直接退出而不是再次返回该界面
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        //log打印用于测试activity销毁
        Log.i(TAG, "destory")
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun test11CreateH(view: android.view.View) {
        HandlerFactory.test()
    }

    fun testUIHandler(view: android.view.View) {
        send()//开启倒计时并跳转页面的方法
        post()
    }

    //Android Framework | Handler 消息机制详解-相关类各个突破
    //Android Framework | Handler 消息机制详解-整体流程把控
    //Android Framework | Handler 消息机制详解-应用与实践

    fun testThreadHandler(view: android.view.View) {

        val myHTTest = MyHTTest()
        myHTTest.handler.sendEmptyMessage(1111)


//        loopTHTest?.h1 = object : Handler(looperThread?.mLooper) {
//            override fun handleMessage(msg: Message) {
//                Log.d(
//                    TAG,
//                    "h1-Handler-message | Handler:${msg.target.hashCode()}，message:$msg，"
//                )
//            }
//        }
//        loopTHTest?.h2 = MyH(looperThread?.mLooper)
//        loopTHTest?.h1?.sendEmptyMessage(222222)
//        loopTHTest?.h2?.sendEmptyMessage(3333333)


    }

    companion object {
        const val TAG = "Jay-H"
    }

    fun testJNI(view: android.view.View) {
//        testNewHandlerInThread()
//        startActivity(Intent(this,JNITestActivity::class.java))
    }


}