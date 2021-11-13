package com.jay.biz_android.handler

import android.content.Context
import android.os.*
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.os.HandlerCompat
import androidx.core.os.postAtTime
import androidx.core.os.postDelayed
import com.jay.biz_android.handler.HandlerMainActivity.Companion.TAG
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread


/**
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/22
 */
object HandlerFactory {


    @RequiresApi(Build.VERSION_CODES.P)
    fun createHandler(type: Int): Handler {
        return when (type) {
            0x01 -> Handler()//Deprecated from API level 30
            0x02 -> Handler(CallBack())//Deprecated from API level 30
            0x03 -> Handler(Looper.myLooper())
            0x04 -> Handler(Looper.myLooper(), CallBack())
            0x05 -> Handler(false)//UnsupportedAppUsage
            0x06 -> Handler(CallBack(), false) //todo 无法调用，会崩溃
            0x07 -> Handler(Looper.myLooper(), CallBack(), false)//UnsupportedAppUsage
            0x08 -> Handler.createAsync(Looper.myLooper())//Call requires API level 28
            0x09 -> HandlerCompat.createAsync(Looper.myLooper())
            0x0a -> Handler.createAsync(Looper.myLooper(), CallBack())//Call requires API level 28
            0x0b -> HandlerCompat.createAsync(Looper.myLooper(), CallBack())
            0x0c -> Handler.getMain()
            0x0d -> Handler.mainIfNull(Handler(Looper.myLooper())) //todo 无法调用，会崩溃
            else -> Handler.getMain()
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun postMessage(type: Int): Boolean {
        val handler = Handler(Looper.myLooper())
        return when (type) {
            0x01 -> handler.post {}
            0x02 -> handler.postDelayed({}, 1024)
            0x03 -> handler.postDelayed({}, 0, 1024)
            0x04 -> handler.postDelayed({}, Any(), 1024)
            0x05 -> HandlerCompat.postDelayed(handler, {}, Any(), 1024)
            0x06 -> handler.postAtTime({}, 1024)
            0x07 -> handler.postAtTime({}, Any(), 1024)
            0x08 -> handler.postAtFrontOfQueue {}
            0x09 -> handler.postDelayed(200L) {}.equals("")
            0x0a -> handler.postAtTime(200L) {}.equals("")
            else -> handler.post {}
        }
    }

    fun sendMessage(type: Int): Boolean {
        val handler = Handler(Looper.myLooper())
        return when (type) {
            0x01 -> handler.sendEmptyMessage(1)
            0x02 -> handler.sendEmptyMessageDelayed(2, 1024)
            0x03 -> handler.sendEmptyMessageAtTime(3, 1024)
            0x04 -> handler.sendMessage(Message.obtain())
            0x05 -> handler.sendMessageDelayed(Message.obtain(), 1024)
            0x06 -> handler.sendMessageAtTime(Message.obtain(), 1024)
            0x07 -> handler.sendMessageAtFrontOfQueue(Message.obtain())
            0x08 -> handler.executeOrSendMessage(Message.obtain())
            0x09 -> handler.hasMessagesOrCallbacks()
            else -> handler.sendEmptyMessage(1)
        }
    }

    fun removeMessage(type: Int): Unit {
        val handler = Handler(Looper.myLooper())
        handler.sendEmptyMessage(5)
        val runnable = {}
        val token = Any()
        HandlerCompat.postDelayed(handler, runnable, token, 1024)
        when (type) {
            0x01 -> handler.removeMessages(5)
            0x02 -> handler.removeMessages(5, token)
            0x03 -> handler.removeEqualMessages(5, token)
            0x04 -> handler.removeCallbacksAndMessages(token)
            0x05 -> handler.removeCallbacksAndEqualMessages(token)
            0x06 -> handler.removeCallbacks(runnable, token)
            0x08 -> handler.removeCallbacks(runnable)
            else -> handler.removeCallbacks(runnable)
        }
    }

    //删除消息队列中带有代码“what”的所有待处理消息
    // 删除消息队列中代码为“what”且对象为“object”的所有待处理消息。 如果object为 null，则将删除所有消息

    /**
     * 简单工厂模式创建 Handler
     *
     * @param type 创建类型
     * @return Handler
     */
    fun createHandler2(type: Int): Handler {
        return when (type) {
            //默认构造函数将此 Handler 默认通过 mLooper = Looper.myLooper() 方式与当前线程的 Looper 相关联。
            //如果此线程没有创建对应的 Looper，Handler在构造时会引发异常：RuntimeException: Can't create handler inside thread Thread[Thread-3,5,main] that has not called Looper.prepare()
            // Deprecated From API level 30,弃用原因:
            // 在 Handler 构建过程中如果没有显示的指定一个 Looper 在一些情况下可能会导致 bug，例如以下情况：
            // 如果此线程没有创建对应的 Looper，Handler在构造时会导致 RuntimeException
            // 多线程情况下并发时创建 Handler 时可能出现不能确定是哪个线程中创建的，mLooper = Looper.myLooper() 这行代码不是线程安全的
            //
            //替代隐式设置Looper 的替代方案(以创建一个主线程H为例)：
            // ContextCompat.getMainExecutor(context) //Use an Executor
            // view.getHandler()
            // Handler.getMain() / Handler(Looper.getMainLooper()) //显式指定 Looper。

            0x01 -> Handler()//Deprecated from API level 30
            //同Handler() CallBack:一个回调接口，handleMessage 方法的回调对象，只有 send 系方式才会执行到这个回调，它的返回值也会影响 Handler 的 handleMessage 成员方法。
            0x02 -> Handler(CallBack())//Deprecated from API level 30
            //使用指定的 Looper 构造 Handler
            0x03 -> Handler(Looper.getMainLooper())
            //同上
            0x04 -> Handler(Looper.myLooper(), CallBack())
            // 默认情况下，Handler 处理的 Message 都是是同步的，使用此构造函数可以创建一个能够处理异步 Message 的Handler。
            // 异步消息表示不需要像同步消息那样对中断或事件进行全局排序。 异步消息不受MessageQueue.enqueueSyncBarrier(long)方法插入的的同步障碍的影响。
            //
            //参数：async – 最终会把同步标志传递到 Message.setAsynchronous(async)
            0x05 -> Handler(false) //这个构造器也没有显示传递 Looper 为什么没有标记废弃呢
            //todo Caused by: java.lang.NoSuchMethodError: No direct method <init>(Landroid/os/Handler$Callback;Z)V in class Landroid/os/Handler; or its super classes (declaration of 'android.os.Handler' appears in /system/framework/framework.jar!classes2.dex)
//            0x06 -> Handler(CallBack(), false) //todo 小米10-Android11 上无法调用
            0x07 -> Handler(Looper.myLooper(), CallBack(), false)//UnsupportedAppUsage
            //同 Handler(looper, callback, true);
            0x08 -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                Handler.createAsync(Looper.myLooper())
            } else {
                TODO("VERSION.SDK_INT < P")
            }//Call requires API level 28
            //同 Handler(looper, callback, true);
            0x09 -> Handler.createAsync(Looper.myLooper(), CallBack())//Call requires API level 28
            //同 Handler(Looper.getMainLooper());
            0x0a -> Handler.getMain()
//            0x0b -> Handler.mainIfNull(Handler(Looper.myLooper()))//todo 小米10-Android11 上无法调用
            //创建一个新的处理程序，其发布的消息和可运行对象不受显示 vsync 等同步障碍的影响。
            //发送到异步处理程序的消息保证相对于彼此排序，但不一定相对于来自其他处理程序的消息
            0x0c -> HandlerCompat.createAsync(Looper.myLooper())
            0x0d -> HandlerCompat.createAsync(Looper.myLooper(), CallBack())
            else -> Handler.getMain()
        }
    }


    class CallBack : Handler.Callback {
        override fun handleMessage(msg: Message?): Boolean {
            Log.d(TAG, "CallBack-message | Handler:${msg?.target.hashCode()}，message:$msg，")
            return true ///dispatchMessage 时如果 返回 true 处理完这个回调就结束，返回false还需继续Handler 的 handleMessage
        }
    }

    class MyH : Handler(Looper.myLooper(), CallBack()) {
        override fun handleMessage(msg: Message?) {
            Log.d(TAG, "Handler-message | Handler:${msg?.target.hashCode()}，message:$msg，")
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun test() {
        for (i in 0x01..0x0c) {
            val handler = createHandler(i)
            Log.d(TAG, "handler-$i-hashCode:${handler.hashCode()}")
//            handler.sendEmptyMessage(1024)
            handler.post {
                Log.d(TAG, "Handler-post ")
            }
            val r = {}
            handler.post(r)
            handler.removeCallbacks(r)

//            val message = Message.obtain()
//            message.what = i
//            handler.sendMessage(message)//发送
//            val handleMessageRef = handler::handleMessage
//            handleMessageRef.invoke(message)//处理
//            Log.d(
//                TAG,
//                "Handler-message-invoke | Handler:${message?.target.hashCode()}，message:${message.toString()}，"
//            )
        }


//        getConstructors()

    }


    private fun getConstructors() {
        val h = Handler.getMain()
        h.javaClass.constructors.forEach {
            Log.d(TAG, "Handler-constructors:$it")
        }
        //2021-09-23 12:28:49.165 D: Handler-constructors:public android.os.Handler()
        //2021-09-23 12:28:49.166 D: Handler-constructors:public android.os.Handler(android.os.Handler$Callback)
        //2021-09-23 12:28:49.166 D: Handler-constructors:public android.os.Handler(android.os.Looper)
        //2021-09-23 12:28:49.166 D: Handler-constructors:public android.os.Handler(android.os.Looper,android.os.Handler$Callback)
        //2021-09-23 12:28:49.166 D: Handler-constructors:public android.os.Handler(android.os.Looper,android.os.Handler$Callback,boolean)
        //2021-09-23 12:28:49.166 D: Handler-constructors:public android.os.Handler(boolean)
        //在11的设备上少了两个构造方法，。。。。。
    }


    fun senMsg() {

        val handler = Handler(Looper.myLooper())


        //将 Runnable 包装成 Message 并添加到 handler-mLooper-mQueue 中。 runnable 将在会在 mLooper 对应的线程上执行。
        //
        //返回： 如果 Runnable 成功放入 mQueue，则返回 true。 失败时返回 false，通常是因为处理 mQueue 的 mLooper 正在退出。
        handler.post {}
        //将 Runnable 包装成 Message 并添加到 handler-mLooper-mQueue 中，在 delayMillis 毫秒后运行。 runnable 将在会在 mLooper 对应的线程上执行。
        // 延迟的基准时间点是 SystemClock.uptimeMillis。 在深度睡眠中(不知道谁睡眠，应该是关机)花费的时间会增加执行的额外延迟。
        //
        //返回： 如果 Runnable 成功放入 mQueue，则返回 true。 失败时返回 false，通常是因为处理 mQueue 的 mLooper 正在退出。
        // 请注意，结果为 true 并不意味着 Runnable 将被处理，有一种情况是如果 mLooper 在延迟时间之前退出了，则消息将被丢弃。
        handler.postDelayed({}, 1024)
//        val action: () -> Unit = fun() {}
//        handler.postDelayed(1024, Any(), action)

        handler.postDelayed(200L) {}
        handler.postAtTime(200L) {}

        //同postDelayed(Runnable r, long delayMillis)，不同之处是将 Runnable 包装成 Message 时指定了 what 消息标号
        handler.postDelayed({}, 0, 1024)
        //同postDelayed(Runnable r, long delayMillis)，不同之处是将 Runnable 包装成 Message 时将一个对象设置给了 Message 的 obj
        // 这个对象可以用来在队列中找到这条消息，从而可以通过这个令牌调用 handler.removeCallbacksAndMessages(messageToken) 取消 Runnable 的执行。
        val messageToken = Any()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            handler.postDelayed({}, messageToken, 1024)
            //通过 token 对象在 mQueue 中找到并回收 Message
            handler.removeCallbacksAndMessages(messageToken)
        }
        //上面的兼容版本
        HandlerCompat.postDelayed(handler, {}, messageToken, 1024)

        //将 Runnable 包装成 Message 并排在 mQueue 的前面，当 mLooper 下一次迭代时进行处理。 runnable 将在会在 mLooper 对应的线程上执行。
        // 此方法仅用于非常特殊的情况：它很容易使消息队列饿死、导致排序问题或产生其他意想不到的副作用。
        //
        //返回： 如果 Runnable 成功放入 mQueue，则返回 true。 失败时返回 false，通常是因为处理 mQueue 的 mLooper 正在退出。
        handler.postAtFrontOfQueue {}
        //SystemClock.uptimeMillis(): 自系统启动开始从0开始的到调用该方法时相差的毫秒数，不计算深度睡眠所花费的时间。
        //System.currentTimeMillis(): 代表的是从 1970-01-01 00:00:00 到当前时间的毫秒数，这个值是一个强关联系统时间的值，我们可以通过修改系统时间达到修改该值的目的，所以该值是不可靠的值
        //将 Runnable 包装成 Message 并添加到 handler-mLooper-mQueue 中，在 uptimeMillis 指定的特定时间点运行。
        //在深度睡眠中(不知道谁睡眠，应该是关机)花费的时间会增加执行的额外延迟。 runnable 将在会在 mLooper 对应的线程上执行。
        //返回： 如果 Runnable 成功放入 mQueue，则返回 true。 失败时返回 false，通常是因为处理 mQueue 的 mLooper 正在退出。
        handler.postAtTime({}, 1024)
        //同postAtTime(Runnable r, long uptimeMillis)，不同之处是将 Runnable 包装成 Message 时将一个对象设置给了 Message 的 obj
        // 这个对象可以用来在队列中找到这条消息，从而可以通过这个令牌调用 handler.removeCallbacksAndMessages(messageToken) 取消 Runnable 的执行。
        handler.postAtTime({}, messageToken, 1024)
        //通过 token 对象在 mQueue 中找到并回收 Message
        handler.removeCallbacksAndMessages(messageToken)


        //发送一个仅包含 what 值的 Message 到 mQueue 中。handleMessage 方法将在会在 mLooper 对应的线程上执行
        handler.sendEmptyMessage(1)
        //发送一个仅包含 what 值的 Message，在 delayMillis 毫秒后处理。handleMessage 方法将在会在 mLooper 对应的线程上执行
        handler.sendEmptyMessageDelayed(2, 1024)
        //发送只包含 what 值的消息，在 uptimeMillis 时间点后处理。handleMessage 方法将在会在 mLooper 对应的线程上执行
        handler.sendEmptyMessageAtTime(3, 1024)
        //在当前时间之前的所有待处理消息之后，将消息推送到消息队列的末尾。 handleMessage 方法将在会在 mLooper 对应的线程上执行
        handler.sendMessage(Message.obtain())
        //同sendEmptyMessageDelayed
        handler.sendMessageDelayed(Message.obtain(), 1024)
        //同sendEmptyMessageAtTime
        handler.sendMessageAtTime(handler.obtainMessage(), 1024)
        //将 Message 并排在 mQueue 的前面，当 mLooper 下一次迭代时进行处理。 handleMessage 将在会在 mLooper 对应的线程上执行。
        // 此方法仅用于非常特殊的情况：它很容易使消息队列饿死、导致排序问题或产生其他意想不到的副作用。
        handler.sendMessageAtFrontOfQueue(Message.obtain())
        //构造 handler 时获取 mLooper 的与当前线程中缓存的 Looper 相比
        //如果同一个线程 直接执行 handleMessage 否则调用 sendMessage
        handler.executeOrSendMessage(Message.obtain())

    }


}

fun testExecutor(context: Context) {

    // Create an executor that executes tasks in the main thread.
    val mainExecutor: Executor = ContextCompat.getMainExecutor(context)
    // Execute a task in the main thread
    mainExecutor.execute(Runnable {
        // You code logic goes here.
    })


    // Create an executor that executes tasks in a background thread.
    val backgroundExecutor: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()
// Execute a task in the background thread.
    backgroundExecutor.execute {
        // Your code logic goes here.
        // Update UI on the main thread
        mainExecutor.execute {
            // You code logic goes here.
        }
    }

// Execute a task in the background thread after 3 seconds.
    backgroundExecutor.schedule({
        // Your code logic goes here
        // Update UI on the main thread
        mainExecutor.execute {
            // You code logic goes here.
        }
    }, 3, TimeUnit.SECONDS)
}

fun testNewHandlerInThread() {
    val h = Handler()
    h.post {
        println("thread1:${Thread.currentThread().name}")
    }

    thread {
        println("thread2:${Thread.currentThread().name}")
        //只调用 prepare 没有调用 loop 不会处理消息
        Looper.prepare()
        //在子线程没有实例化 Looper 在构造 Handler 时会报错：RuntimeException: Can't create handler inside thread Thread[Thread-3,5,main] that has not called Looper.prepare()
        val h = Handler()
        h.post {
            println("thread3:${Thread.currentThread().name}")
        }

        //TODO post 之后开启循环才能收到消息： Message msg = queue.next();  if (msg == null) {return;}
        Looper.loop()


    }

}

fun testSameWhatToManyHandler() {

    val h1 = Handler(Looper.myLooper()) {
        when (it.what) {
            1 -> Log.d("Jay", "target:" + it.target.hashCode())
        }
        true
    }
    val h2 = Handler(Looper.myLooper()) {
        when (it.what) {
            1 -> Log.d("Jay", "target:" + it.target.hashCode())
        }
        true
    }
    //h1
    Log.d("Jay", "h1:" + h1.hashCode())
    Log.d("Jay", "h1-looper:" + h1.looper.hashCode())

    val queue1Field = h1.looper.javaClass.getDeclaredField("mQueue")
    queue1Field.isAccessible = true
    val queue1 = queue1Field.get(h1.looper) as MessageQueue
    Log.d("Jay", "h1-looper-queue1:" + queue1.hashCode())

    //h2
    Log.d("Jay", "h2:" + h2.hashCode())
    Log.d("Jay", "h2-looper:" + h2.looper.hashCode())

    val queue2Field = h2.looper.javaClass.getDeclaredField("mQueue")
    queue2Field.isAccessible = true
    val queue2 = queue2Field.get(h2.looper) as MessageQueue
    Log.d("Jay", "h1-looper-queue2:" + queue2.hashCode())



    h1.sendEmptyMessage(1)
    h2.sendEmptyMessage(2)

    //mMessages
    val message1Field = queue1.javaClass.getDeclaredField("mMessages")
    message1Field.isAccessible = true
    val message1 = message1Field.get(queue1)
//    getMessages(message1)

    //mMessages
    val message2Field = queue2.javaClass.getDeclaredField("mMessages")
    message2Field.isAccessible = true
    val message2 = message2Field.get(queue2)


    handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {

                1024 -> {
                    handler?.sendEmptyMessageDelayed(1024, 10)
                }

                101 -> {
                    handler?.sendEmptyMessageDelayed(101, 1000)
                    Log.d("Jay", "--------sendEmptyMessageAtTime-1024")
                    getMessages(message2)
                }
            }
        }
    }


    handler?.sendEmptyMessageDelayed(101, 1000)
    handler?.sendEmptyMessageDelayed(1024, 10)

}

var handler: Handler? = null


fun getMessages(m: Any?): Boolean {
    if (m == null) {
        return false
    }
    var p: Message? = m as Message
    var index = 0
    while (p != null) {
        index++
        val nextField = p.javaClass.getDeclaredField("next")
        nextField.isAccessible = true
        val next = nextField.get(p)
        if (next != null) {
            next as Message
            Log.d("Jay", "next-what:${next.what}, target: ${next.target.hashCode()} ")
            p = next
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                Log.d("Jay", "Message: ${p.print()}")
            }
        }

        if (index > 3) {
            return false
        }
    }
    Log.d("Jay", "index:$index")

    return false
}


fun getTopMessages(m: Any?): Boolean {
    if (m == null) {
        return false
    }
    val p: Message? = m as Message
    if (p != null) {
        val nextField = p.javaClass.getDeclaredField("next")
        nextField.isAccessible = true
        val next = nextField.get(p)
        if (next != null) {
            next as Message
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                Log.d("Jay", "Message: ${next.print()}")
            }
        }
    }
    return false
}


@RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
fun Message.print() {
    Log.d("Jay", "uptimeMillis: ${SystemClock.uptimeMillis()}")
    Log.d("Jay", "when-uptimeMillis: ${(this.`when` - SystemClock.uptimeMillis())}")
    Log.d(
        "Jay", "target: ${if (this.target != null) this.target.hashCode() else "null"}, " +
                "what: ${this.what}," +
                "isAsynchronous: ${this.isAsynchronous}," +
                "arg1: ${this.arg1}," +
                "arg2: ${this.arg2}," +
                "callback: ${if (this.callback != null) this.callback.javaClass.name else this.callback}," +
                "data: ${this.data}," +
                "obj: ${this.obj}," +
                " when: ${this.`when`}"
    )
}

//2021-10-01 18:58:18.841 D: h1:205208129
//2021-10-01 18:58:18.841 D: h1-looper:4049894
//2021-10-01 18:58:18.841 D: h1-looper-queue1:139728167
//2021-10-01 18:58:18.841 D: h2:224990164
//2021-10-01 18:58:18.841 D: h2-looper:4049894
//2021-10-01 18:58:18.841 D: h1-looper-queue2:139728167
//2021-10-01 18:58:18.842 D: next-what:1, target: 205208129
//2021-10-01 18:58:18.842 D: next-what:1, target: 224990164
//2021-10-01 18:58:18.842 D: uptimeMillis: 24473870
//2021-10-01 18:58:18.842 D: when-uptimeMillis: 0
//2021-10-01 18:58:18.842 D: target: 224990164, what: 1,isAsynchronous: false,arg1: 0,arg2: 0,callback: null,data: Bundle[{}],obj: null, when: 24473870
//2021-10-01 18:58:18.842 D: Message: kotlin.Unit
//2021-10-01 18:58:18.842 D: next-what:0, target: 62929277
//2021-10-01 18:58:18.842 D: uptimeMillis: 24473871
//2021-10-01 18:58:18.842 D: when-uptimeMillis: 0
//todo RenderNodeAnimator 按钮事件
//2021-10-01 18:58:18.842 D: target: 62929277, what: 0,isAsynchronous: true,arg1: 0,arg2: 0,callback: android.graphics.animation.-$$Lambda$awqPSgriNRe12PWP0zkpAtPsfV4@e0ac072,data: Bundle[{}],obj: null, when: 24473871
//2021-10-01 18:58:18.843 D: Message: kotlin.Unit
//2021-10-01 18:58:18.843 D: uptimeMillis: 24473871
//2021-10-01 18:58:18.843 D: when-uptimeMillis: 0
//2021-10-01 18:58:18.843 D: target: 62929277, what: 0,isAsynchronous: true,arg1: 0,arg2: 0,callback: android.graphics.animation.-$$Lambda$awqPSgriNRe12PWP0zkpAtPsfV4@e0ac072,data: Bundle[{}],obj: null, when: 24473871
//2021-10-01 18:58:18.843 D: Message: kotlin.Unit


fun blockTest() {

    Looper.getMainLooper().setMessageLogging { x -> Log.d("Droid", "打印:$x") };

}


/*
Thread.java
    //与此线程有关的 ThreadLocal 值。该 ThreadLocalMap 由 ThreadLocal 类维护。
    ThreadLocal.ThreadLocalMap threadLocals = null;
ActivityThread.java
  Looper.prepareMainLooper();
  sThreadLocal.set(new Looper(quitAllowed));
  sMainLooper

 */



//主线程 —> threadlocal —> Looper —> MessageQueue —> Message —> Handler —> Activity
