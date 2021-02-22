package com.qlife.base_web.preload

import android.os.Looper
import android.util.Log
import com.qlife.base_web.app.BaseWebApp
import com.qlife.base_web.webClient.BaseX5WebViewClient
import com.qlife.base_web.widget.x5.BaseX5WebView
import java.util.*

object WebPreloadHelper {

    private const val CACHED_WEB_VIEW_MAX_NUM = 2

    const val TAG = "WebPreloadHelper"

    val mCachedX5WebViewStack: Stack<BaseX5WebView> = Stack()

    val mCachedWebViewStack: Stack<android.webkit.WebView> = Stack()

    /**
     * 创建并缓存 WebView 实例 用了applicationContext，在打开app时调用
     * IdleHandler 原理浅析:
     * 当消息队列空闲时会执行IdleHandler的queueIdle()方法，该方法返回一个boolean值，
     * 如果为false则执行完毕之后移除这条消息（一次性完事），
     * 如果为true则保留，等到下次空闲时会再次执行（重复执行）。
     * 如果返回 false，那么这个 IdleHandler 只会执行一次，执行完后会从队列中删除，如果返回 true，那么执行完后不会被删除，只要执行 MessageQueue.next 时消息队列中没有可执行的消息，即为空闲时间，那么 IdleHandler 队列中的 IdleHandler 还会继续被执行。
     */
    fun preloadX5Web() {
        Log.d(TAG, "preloadX5Web ,size=：${mCachedX5WebViewStack.size}")
        if (mCachedX5WebViewStack.size < CACHED_WEB_VIEW_MAX_NUM) {
            mCachedX5WebViewStack.push(WebX5Helper.createX5WebView())//创建一个WebView并缓存起来
            Log.d(TAG, "preloadX5Web finish ,size=：${mCachedX5WebViewStack.size}")

        }
    }

    fun preloadWeb() {
        Log.d(TAG, "preloadX5Web ,size=：${mCachedWebViewStack.size}")
        Looper.myQueue().addIdleHandler {
            if (mCachedWebViewStack.size < CACHED_WEB_VIEW_MAX_NUM) {
                mCachedWebViewStack.push(WebHelper.createWebView())//创建一个WebView并缓存起来
            }
            false//如果为false则执行完毕之后移除这条消息（一次性完事）
        }
    }

    /**
     * 预加载某个H5的资源
     *
     * @param preUrl h5 地址
     */
    fun loadPreUrlByX5(preUrl: String) {
        Log.d(TAG, "loadPreUrlByX5 ,preUrl=：${preUrl}")
        val webView = WebX5Helper.getX5WebView(BaseWebApp.getApp())
        webView.loadUrl(preUrl)
    }

    /**
     * 预加载某个H5的资源
     *
     * @param preUrl h5 地址
     */
    fun loadPreUrlByX5(preUrl: String, baseX5WebViewClient: BaseX5WebViewClient) {
        Log.d(TAG, "loadPreUrlByX5 ,preUrl=：${preUrl}")
        val webView = WebX5Helper.getX5WebView(BaseWebApp.getApp())
        webView.webViewClient = baseX5WebViewClient
        webView.loadUrl(preUrl)
    }

    /**
     * 预加载某个H5的资源
     *
     * @param preUrl h5 地址
     */
    fun loadPreUrl(preUrl: String) {
        val webView = WebHelper.getWebView(BaseWebApp.getApp())
        webView.loadUrl(preUrl)
    }


}