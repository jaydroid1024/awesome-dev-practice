package com.jay.biz_android.handler

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.os.Process
import android.util.Log
import okhttp3.internal.notifyAll
import okhttp3.internal.wait

/**
 * A [Thread] that has a [Looper]. The [Looper] can then be used to create [ ]s.
 *
 *
 * Note that just like with a regular [Thread], [.start] must still be called.
 *
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/24
 */
class MyHandlerThread : Thread {

    var mPriority: Int

    /** Returns the identifier of this thread. See Process.myTid().  */
    var threadId = -1
    var mLooper: Looper? = null
    private var mHandler: Handler? = null

    constructor(name: String?) : super(name) {
        mPriority = Process.THREAD_PRIORITY_DEFAULT
    }

    /**
     * Constructs a HandlerThread.
     *
     * @param name
     * @param priority The priority to run the thread at. The value supplied must be from [     ] and not from java.lang.Thread.
     */
    constructor(name: String?, priority: Int) : super(name) {
        mPriority = priority
    }

    /**
     * Call back method that can be explicitly overridden if needed to execute some setup before
     * Looper loops.
     */
    protected fun onLooperPrepared() {}

    override fun run() {
        threadId = Process.myTid()
        Looper.prepare()
        synchronized(this) {
            mLooper = Looper.myLooper()
            notifyAll()
        }
        Process.setThreadPriority(mPriority)
        onLooperPrepared()
        Looper.loop()
        threadId = -1
    }// If the thread has been started, wait until the looper has been created.

    /**
     * This method returns the Looper associated with this thread. If this thread not been started or
     * for any reason isAlive() returns false, this method will return null. If this thread has been
     * started, this method will block until the looper has been initialized.
     *
     * @return The looper.
     */
    val looper: Looper?
        get() {
            if (!isAlive) {
                return null
            }

            // If the thread has been started, wait until the looper has been created.
            synchronized(this) {
                while (isAlive && mLooper == null) {
                    try {
                        wait()
                    } catch (e: InterruptedException) {
                    }
                }
            }
            return mLooper
        }

    /**
     * @return a shared [Handler] associated with this thread
     * @hide
     */
    val threadHandler: Handler
        get() {
            if (mHandler == null) {
                mHandler = Handler(looper)
            }
            return mHandler!!
        }

    /**
     * Quits the handler thread's looper.
     *
     *
     * Causes the handler thread's looper to terminate without processing any more messages in the
     * message queue.
     *
     *
     * Any attempt to post messages to the queue after the looper is asked to quit will fail. For
     * example, the [Handler.sendMessage] method will return false.
     *
     *
     * Using this method may be unsafe because some messages may not be delivered
     * before the looper terminates. Consider using [.quitSafely] instead to ensure that all
     * pending work is completed in an orderly manner.
     *
     * @return True if the looper looper has been asked to quit or false if the thread had not yet
     * started running.
     * @see .quitSafely
     */
    fun quit(): Boolean {
        val looper = looper
        if (looper != null) {
            looper.quit()
            return true
        }
        return false
    }

    /**
     * Quits the handler thread's looper safely.
     *
     *
     * Causes the handler thread's looper to terminate as soon as all remaining messages in the
     * message queue that are already due to be delivered have been handled. Pending delayed messages
     * with due times in the future will not be delivered.
     *
     *
     * Any attempt to post messages to the queue after the looper is asked to quit will fail. For
     * example, the [Handler.sendMessage] method will return false.
     *
     *
     * If the thread has not been started or has finished (that is if [.getLooper] returns
     * null), then false is returned. Otherwise the looper is asked to quit and true is returned.
     *
     * @return True if the looper looper has been asked to quit or false if the thread had not yet
     * started running.
     */
    fun quitSafely(): Boolean {
        val looper = looper
        if (looper != null) {
            looper.quitSafely()
            return true
        }
        return false
    }
}

class MyHTTest {

    val handler: Handler

    init {
        val myHandlerThread = MyHandlerThread("jay-thread")
        myHandlerThread.start()
        handler = object : Handler(myHandlerThread.looper, CallBack()) {
            override fun handleMessage(msg: Message?) {
                "myHandlerThread-Handler-message | Handler:${msg?.target.hashCode()}，message:$msg，"
            }
        }
    }

    class CallBack : Handler.Callback {
        override fun handleMessage(msg: Message?): Boolean {
            Log.d(
                HandlerMainActivity.TAG,
                "myHandlerThread-CallBack-message | Handler:${msg?.target.hashCode()}，message:$msg，"
            )
            return false ///dispatchMessage 时如果 返回 true 处理完这个回调就结束，返回false还需继续Handler 的 handleMessage
        }
    }


}



