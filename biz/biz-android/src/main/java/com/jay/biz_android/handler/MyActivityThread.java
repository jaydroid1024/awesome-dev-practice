package com.jay.biz_android.handler;

import android.app.ActivityThread;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;

import androidx.annotation.RequiresApi;

import java.io.FileDescriptor;
import java.util.LinkedList;

/**
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/24
 */
class MyActivityThread {

  @RequiresApi(api = Build.VERSION_CODES.M)
  public static void main(String[] args) {
    ActivityThread.ActivityClientRecord activityClientRecord =
        new ActivityThread.ActivityClientRecord();

    Looper.loop();
    // 原生应用层：Java\Kotlin
    // Native 层：C&C++
    // 跨端应用层：Dart
    Looper.prepareMainLooper();
    Looper.myQueue();
    LinkedList<String> strings = new LinkedList<>();
    strings.add("a");

    Handler handler = new Handler(Looper.myLooper());

    handler.post(() -> {});
    handler.postDelayed(() -> {}, 1024);
    handler.postDelayed(() -> {}, 0, 1024);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
      handler.postDelayed(() -> {}, new Object(), 1024);
    }
    handler.postAtFrontOfQueue(() -> {});
    handler.postAtTime(() -> {}, 1024);
    handler.postAtTime(() -> {}, new Object(), 1024);

    handler.sendEmptyMessage(1);
    handler.sendEmptyMessageDelayed(2, 1024);
    handler.sendEmptyMessageAtTime(3, 1024);
    handler.sendMessage(Message.obtain());
    handler.sendMessageDelayed(Message.obtain(), 1024);
    handler.sendMessageAtTime(Message.obtain(), 1024);
    handler.sendMessageAtFrontOfQueue(Message.obtain());
    handler.executeOrSendMessage(Message.obtain());

    /**
     * //Handler public class Handler {} final Looper mLooper; final MessageQueue mQueue;
     *
     * <p>//Looper public final class Looper {} private Looper(boolean quitAllowed) { mQueue = new
     * MessageQueue(quitAllowed); }
     *
     * <p>//MessageQueue public final class MessageQueue {} private native static long nativeInit();
     * private native static void nativeDestroy(long ptr); private native void nativePollOnce(long
     * ptr, int timeoutMillis); /*non-static for callbacks private native static void
     * nativeWake(long ptr); private native static boolean nativeIsPolling(long ptr); private native
     * static void nativeSetFileDescriptorEvents(long ptr, int fd, int events);
     *
     * <p>MessageQueue(boolean quitAllowed) { mQuitAllowed = quitAllowed; mPtr = nativeInit(); } }
     */
    Message message = new Message();

    // todo 'MessageQueue(boolean)' is not public in 'android.os.MessageQueue'. Cannot be accessed
    // from outside package

    // MessageQueue messageQueue = new MessageQueue(true);
    MessageQueue.OnFileDescriptorEventListener onFileDescriptorEventListener =
        new MessageQueue.OnFileDescriptorEventListener() {
          @Override
          public int onFileDescriptorEvents(FileDescriptor fd, int events) {
            return 0;
          }
        };

    MessageQueue.IdleHandler idleHandler =
        new MessageQueue.IdleHandler() {
          @Override
          public boolean queueIdle() {
            return false;
          }
        };

//    Looper looper = new Looper();
    ThreadLocal<String> threadLocal = new ThreadLocal<String>();
  }
}
