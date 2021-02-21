package com.jay.base_speech.media

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Administrator on 2017/6/3.
 */
object NetWorkUtil {
    @JvmStatic
    fun isOnline(context: Context): Boolean {
        val cm =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo?.isConnectedOrConnecting ?: false
    }
}