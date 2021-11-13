package com.jay.biz_android.handler.my_handler

import java.util.concurrent.DelayQueue

/**
 * @author jaydroid
 * @version 1.0
 * @date 2021/10/7
 */
class MessageQueue {

    val queue = DelayQueue<Message>()

    fun enqueueMessage(msg: Message) = queue.add(msg)

    fun next(): Message? {
        try {
            return queue.take()
        } catch (e: InterruptedException) {
        }
        return null
    }

    fun quit() = queue.clear()

}