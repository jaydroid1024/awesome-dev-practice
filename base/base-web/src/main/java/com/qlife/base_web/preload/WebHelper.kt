package com.qlife.base_web.preload

import android.annotation.SuppressLint
import android.content.Context
import android.content.MutableContextWrapper
import android.util.Log
import android.webkit.WebView
import com.qlife.base_web.app.BaseWebApp
import com.qlife.base_web.webClient.BaseWebChromeClient
import com.qlife.base_web.webClient.BaseWebViewClient
import com.qlife.base_web.webSetting.WebSettingHelper


/**
 * @author wangxuejie
 * @version 1.0
 * @date 1/22/21
 */
object WebHelper {

    /**
     * 初始化WebView并预加载
     */
    fun createWebView(): WebView {
        val webView = WebView(MutableContextWrapper(BaseWebApp.getApp()))
        configWebView(webView)
        return webView
    }

    /**
     * 配置webview
     */
    @SuppressLint("SetJavaScriptEnabled")
    private fun configWebView(webView: WebView): WebView {
        WebSettingHelper.configWebSettings(webView)
        configWebClients(webView)
        return webView
    }

    private fun configWebClients(webView: WebView) {
        webView.webViewClient = BaseWebViewClient()
        webView.webChromeClient = BaseWebChromeClient()
    }

    /**
     * 从缓存池中获取合适的WebView
     *
     * @param context activity context
     * @return WebView
     */
    fun getWebView(context: Context): WebView {
        Log.d(
            WebPreloadHelper.TAG,
            "getX5WebView ,size=：${WebPreloadHelper.mCachedWebViewStack.size}"
        )
        // 为空，直接返回新实例
        if (WebPreloadHelper.mCachedWebViewStack.isEmpty()) {
            val web = createWebView()
            val contextWrapper = web.context as MutableContextWrapper
            contextWrapper.baseContext = context
            return web
        }
        val webView = WebPreloadHelper.mCachedWebViewStack.pop()
        // webView不为空，则开始使用预创建的WebView,并且替换Context
        val contextWrapper = webView.context as MutableContextWrapper
        contextWrapper.baseContext = context
        return webView
    }

}