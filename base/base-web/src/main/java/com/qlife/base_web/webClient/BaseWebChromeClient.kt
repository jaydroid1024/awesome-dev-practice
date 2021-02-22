package com.qlife.base_web.webClient

import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import com.qlife.base_web.preload.WebPreloadHelper

/**
 * @author wangxuejie
 * @version 1.0
 * @date 1/22/21
 */
class BaseWebChromeClient : WebChromeClient() {

    override fun onProgressChanged(p0: WebView?, newProgress: Int) {
        super.onProgressChanged(p0, newProgress)
        Log.d(WebPreloadHelper.TAG, "onProgressChanged,newProgress=：$newProgress")
    }
}