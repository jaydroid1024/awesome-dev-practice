package com.jay.biz_android.handler.jni

/**
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/27
 */


fun main() {

    //// Must be kept in sync with the constants in Looper.FileDescriptorCallback 必须与 Looper.FileDescriptorCallback 中的常量保持同步
    //static const int CALLBACK_EVENT_INPUT = 1 << 0;
    //static const int CALLBACK_EVENT_OUTPUT = 1 << 1;
    //static const int CALLBACK_EVENT_ERROR = 1 << 2;

    val CALLBACK_EVENT_INPUT = 1 shl 0
    val CALLBACK_EVENT_OUTPUT = 1 shl 1
    val CALLBACK_EVENT_ERROR = 1 shl 2

    println(CALLBACK_EVENT_INPUT)
    println(CALLBACK_EVENT_OUTPUT)
    println(CALLBACK_EVENT_ERROR)
}