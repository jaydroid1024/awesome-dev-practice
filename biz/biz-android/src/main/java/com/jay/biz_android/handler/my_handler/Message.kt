package com.jay.biz_android.handler.my_handler

import java.util.concurrent.Delayed
import java.util.concurrent.TimeUnit

/**
 * @author jaydroid
 * @version 1.0
 * @date 2021/10/7
 */
class Message : Delayed {

    var time: Long = 0

    override fun compareTo(other: Delayed?): Int {
        return if (other is Message) (time - other.time).toInt()
        else (getDelay(TimeUnit.MILLISECONDS) - other?.getDelay(TimeUnit.MILLISECONDS)!!).toInt()
    }

    override fun getDelay(unit: TimeUnit?): Long {
        return unit?.convert(time - System.currentTimeMillis(), TimeUnit.MILLISECONDS) ?: 0L
    }
}