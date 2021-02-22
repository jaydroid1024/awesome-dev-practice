package com.qlife.base_web.webClient

import android.util.Log
import com.qlife.base_web.preload.WebPreloadHelper
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebView

/**
 * @author wangxuejie
 * @version 1.0
 * @date 1/22/21
 */
open class BaseX5WebChromeClient : WebChromeClient() {

    override fun onProgressChanged(p0: WebView?, newProgress: Int) {
        super.onProgressChanged(p0, newProgress)
        Log.d(WebPreloadHelper.TAG, "onProgressChanged,newProgress=：$newProgress")

    }
}