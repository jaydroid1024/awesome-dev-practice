package com.qlife.base_web.preload

import android.annotation.SuppressLint
import android.content.Context
import android.content.MutableContextWrapper
import android.util.Log
import com.qlife.base_web.app.BaseWebApp
import com.qlife.base_web.preload.WebPreloadHelper.TAG
import com.qlife.base_web.webClient.BaseX5WebChromeClient
import com.qlife.base_web.webClient.BaseX5WebViewClient
import com.qlife.base_web.webSetting.WebSettingHelper
import com.qlife.base_web.widget.x5.BaseX5WebView
import com.tencent.smtt.sdk.WebView

/**
 * @author wangxuejie
 * @version 1.0
 * @date 1/22/21
 */
object WebX5Helper {

    /**
     * 初始化WebView并预加载
     */
    fun createX5WebView(): BaseX5WebView {
        val webView = BaseX5WebView(MutableContextWrapper(BaseWebApp.getApp()))
        Log.d(
            TAG,
            "createX5WebView ,size=：${WebPreloadHelper.mCachedX5WebViewStack.size} ,webView,hashCode= ${webView.hashCode()}"
        )
        configX5WebView(webView)
        return webView
    }

    /**
     * 初始化WebView并预加载
     */
    fun createX5WebView(context: Context): BaseX5WebView {
        val webView = BaseX5WebView(context)
        Log.d(
            TAG,
            "createX5WebView ,size=：${WebPreloadHelper.mCachedX5WebViewStack.size} ,webView,hashCode= ${webView.hashCode()}"
        )

        configX5WebView(webView)
        return webView
    }

    /**
     * 配置webview
     */
    @SuppressLint("SetJavaScriptEnabled")
    fun configX5WebView(webView: WebView): WebView {
        WebSettingHelper.configX5WebSettings(webView)
        configX5WebClients(webView)
        return webView
    }

    fun configX5WebClients(webView: WebView) {
        webView.webViewClient = BaseX5WebViewClient()
        webView.webChromeClient = BaseX5WebChromeClient()
    }

    /**
     * 从缓存池中获取合适的WebView
     *
     * @param context activity context
     * @return WebView
     */
    fun getX5WebView(context: Context): BaseX5WebView {
        Log.d(TAG, "getX5WebView ,size=：${WebPreloadHelper.mCachedX5WebViewStack.size}")
        // 为空，直接返回新实例
        if (WebPreloadHelper.mCachedX5WebViewStack.isEmpty()) {
            val web = createX5WebView()
            val contextWrapper = web.context as MutableContextWrapper
            contextWrapper.baseContext = context
            return web
        }
        val webView = WebPreloadHelper.mCachedX5WebViewStack.pop()
        Log.d(TAG, "getX5WebView ,webView.hashCode= ${webView.hashCode()}")
        // webView不为空，则开始使用预创建的WebView,并且替换Context
        val contextWrapper = webView.context as MutableContextWrapper
        contextWrapper.baseContext = context
        return webView
    }

}