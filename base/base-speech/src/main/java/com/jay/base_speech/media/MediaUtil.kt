package com.jay.base_speech.media

import android.media.MediaPlayer
import android.util.Log
import com.danikula.videocache.CacheListener
import com.jay.base_speech.app.BaseSpeechApp
import com.jay.base_speech.app.BaseSpeechApp.Companion.getInstance
import com.jay.base_speech.media.NetWorkUtil.isOnline

/**
 * Created by Administrator on 2017/7/11.
 */
object MediaUtil {
    private const val TAG = "MediaUtil"
    var mediaPlayer = MediaPlayer()
    var listerner: OnCompletionListener? = null

    fun display(url: String?, listerner: OnCompletionListener): Boolean {
        this.listerner = listerner
        return display(url)
    }

    fun display(url: String?): Boolean {
        val proxy = getInstance().getProxy()
        val proxyUrl = proxy!!.getProxyUrl(url)
        proxy.registerCacheListener(CacheListener { cacheFile, url, percentsAvailable ->
            Log.d(TAG, "onCacheAvailable ,cacheFile:${cacheFile}")
            Log.d(TAG, "onCacheAvailable ,url:${url}")
            Log.d(TAG, "onCacheAvailable ,percentsAvailable:${percentsAvailable}")
        }, proxyUrl)
        Log.d(TAG, "onCacheAvailable ,isCached:${proxy.isCached(proxyUrl)}")

        if (!proxy.isCached(proxyUrl)) {
            if (!isOnline(BaseSpeechApp.getApp())) {
                return false
            }
        }
        mediaPlayer.reset()
        try {
            mediaPlayer.setDataSource(proxyUrl)
            mediaPlayer.prepareAsync()
            mediaPlayer.setOnPreparedListener { player ->
                Log.d(TAG, "onPrepared,proxyUrl:${proxyUrl}")
                player.start()
            }
            mediaPlayer.setOnCompletionListener {
                Log.d(TAG, "onCompletion")
                listerner?.onCompletion(it)

            }
            mediaPlayer.setOnBufferingUpdateListener { mp, percent ->
                Log.d(TAG, "onBufferingUpdate, percent: $percent")
            }
        } catch (e: Exception) {
            Log.d("error ", e.toString())
        }
        return true
    }

    fun setOnCompletionListener(onCompletionListener: OnCompletionListener) {
        this.listerner = onCompletionListener
    }

    interface OnCompletionListener {
        fun onCompletion(mp: MediaPlayer?)
    }


}