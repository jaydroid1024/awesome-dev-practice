package com.qlife.base_web.preload

import android.annotation.SuppressLint
import android.content.Context
import android.content.MutableContextWrapper
import android.graphics.Bitmap
import android.os.Looper
import android.util.Log
import android.webkit.WebSettings
import com.qlife.base_web.app.BaseWebApp
import com.tencent.smtt.export.external.interfaces.WebResourceResponse
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import java.util.*

object PreloadWebView {
    private const val CACHED_WEB_VIEW_MAX_NUM = 2
    const val TAG = "PreloadWebView"
    var PRE_URL = "https://boss-quhuo.aoaosong.com:9062/h5/qplus/loading"

    private val mCachedWebViewStack: Stack<WebView> = Stack()

    /**
     * 创建WebView实例 用了applicationContext
     */
    fun preload() {
        Looper.myQueue()
            .addIdleHandler {
                if (mCachedWebViewStack.size < CACHED_WEB_VIEW_MAX_NUM) {
                    mCachedWebViewStack.push(createWebView())
//                    loadPreUrl(PRE_URL)
                }
                false
            }
    }

    /**
     * 初始化WebView并预加载
     */
    private fun createWebView(): WebView {
        val webView = WebView(MutableContextWrapper(BaseWebApp.getApp()))
        configWebView(webView)
        return webView
    }

    /**
     * 预加载
     */
    fun loadPreUrl(preUrl: String) {
        val webView = getWebView(BaseWebApp.getApp())
        webView.loadUrl(preUrl)
    }


    /**
     * 配置webview
     */
    @SuppressLint("SetJavaScriptEnabled")
    private fun configWebView(webView: WebView): WebView {
        configSettings(webView)
        configClients(webView)
        return webView
    }

    private fun configClients(webView: WebView) {
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
                Log.d(TAG, "shouldOverrideUrlLoading,url=$url")
                view?.loadUrl(url)
                return true
            }

            override fun onPageStarted(p0: WebView?, p1: String?, p2: Bitmap?) {
                super.onPageStarted(p0, p1, p2)
                //                ToastUtils.showLong("加载中。。。。$p1")
                Log.d(TAG, "onPageStarted,PRE_URL：$p1")
            }

            override fun onPageFinished(p0: WebView?, p1: String?) {
                super.onPageFinished(p0, p1)
                //                ToastUtils.showLong("加载完成")
                Log.d(TAG, "onPageFinished, p1=$p1")
            }

            override fun shouldInterceptRequest(p0: WebView, s: String): WebResourceResponse? {
                Log.d(TAG, "shouldInterceptRequest, p1=$s")
                return super.shouldInterceptRequest(p0, s)
            }
        }

        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(p0: WebView?, p1: Int) {
                super.onProgressChanged(p0, p1)
                Log.d(TAG, "onProgressChanged,p1=：$p1")
                //                ToastUtils.showLong("预加载中。。。。$p1")
            }
        }
    }

    private fun configSettings(webView: WebView) {
        //声明WebSettings子类
        val webSettings = webView.settings
        // 是否支持js,true:支持  false:不支持,只需设置支持JS就自动打开IndexedDB存储机制
        webSettings.javaScriptEnabled = true
        // 支持通过JS打开新窗口
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        // 设置是否支持缩放
        webSettings.setSupportZoom(true)
        // 设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.builtInZoomControls = true
        // 隐藏原生的缩放控件
        webSettings.displayZoomControls = false
        // 设置自适应屏幕，两者合用;将图片调整到适合webview的大小
        webSettings.useWideViewPort = true
        // 缩放至屏幕的大小
        webSettings.loadWithOverviewMode = true
        // 是否使用文档存储
        webSettings.domStorageEnabled = true
        //设置是否打开H5的缓存,默认关闭
        webSettings.setAppCacheEnabled(true)
        val appCachePath = BaseWebApp.getApp().cacheDir.absolutePath
        //,appCachePath=：/data/user/0/com.quhuo.boss/cache
        //设置  Application Caches 缓存目录
        webSettings.setAppCachePath(appCachePath)
        //设置缓存大小
        webSettings.setAppCacheMaxSize(Long.MAX_VALUE)
        //开启 database storage API 功能
        webSettings.databaseEnabled = true
        webSettings.databasePath = appCachePath
        //缓存模式(5种)
        //LOAD_CACHE_ONLY:  不使用网络，只读取本地缓存数据
        //LOAD_DEFAULT:  根据cache-control决定是否从网络上取数据。
        //LOAD_CACHE_NORMAL: API level 17中已经废弃, 从API level 11开始作用同LOAD_DEFAULT模式
        //LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
        //LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
        webSettings.cacheMode = WebSettings.LOAD_DEFAULT
        //设置编码格式
        webSettings.defaultTextEncodingName = "utf-8"
        // 设置可以访问文件
        webSettings.allowFileAccess = true
        // 支持自动加载图片
        webSettings.loadsImagesAutomatically = true
    }


    /**
     * 从缓存池中获取合适的WebView
     *
     * @param context activity context
     * @return WebView
     */
    fun getWebView(context: Context?): WebView {
        Log.d(TAG, "onProgressChanged,p1=：${mCachedWebViewStack.isEmpty()}")
        // 为空，直接返回新实例
        if (mCachedWebViewStack.isEmpty()) {
            val web =
                createWebView()
            val contextWrapper = web.context as MutableContextWrapper
            contextWrapper.baseContext = context
            return web
        }
        val webView = mCachedWebViewStack.pop()
        // webView不为空，则开始使用预创建的WebView,并且替换Context
        val contextWrapper = webView.context as MutableContextWrapper
        contextWrapper.baseContext = context
        return webView
    }

}