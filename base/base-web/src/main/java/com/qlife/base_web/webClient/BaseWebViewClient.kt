package com.qlife.base_web.webClient

import android.graphics.Bitmap
import android.os.Build
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import com.qlife.base_web.preload.WebPreloadHelper


/**
 * @author wangxuejie
 * @version 1.0
 * @date 1/22/21
 */
class BaseWebViewClient : WebViewClient() {


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        Log.e(WebPreloadHelper.TAG, "shouldOverrideUrlLoading-2,url=${request?.url}")
        return super.shouldOverrideUrlLoading(view, request)
    }

    override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
        Log.e(WebPreloadHelper.TAG, "shouldOverrideUrlLoading-1,url=$url")
        view?.loadUrl(url)
        return true
    }

    override fun onPageStarted(p0: WebView?, p1: String?, p2: Bitmap?) {
        super.onPageStarted(p0, p1, p2)
        Log.d(WebPreloadHelper.TAG, "onPageStarted,PRE_URL：$p1")
    }

    override fun onPageFinished(p0: WebView?, p1: String?) {
        super.onPageFinished(p0, p1)
        Log.d(WebPreloadHelper.TAG, "onPageFinished, p1=$p1")
    }

    override fun shouldInterceptRequest(p0: WebView, s: String): WebResourceResponse? {
        Log.d(WebPreloadHelper.TAG, "shouldInterceptRequest-1, p1=$s")
        return super.shouldInterceptRequest(p0, "")
    }
}