package com.qlife.base_web.webSetting

import android.annotation.SuppressLint
import com.qlife.base_web.app.BaseWebApp
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView

/**
 * @author wangxuejie
 * @version 1.0
 * @date 1/22/21
 */
object X5WebSetting {


    fun configX5WebSettings(webView: WebView) {

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
        webSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        //设置编码格式
        webSettings.defaultTextEncodingName = "utf-8"
        // 设置可以访问文件
        webSettings.allowFileAccess = true
        // 支持自动加载图片
        webSettings.loadsImagesAutomatically = true
        // 控制html的布局
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        webSettings.supportMultipleWindows()
        webSettings.allowContentAccess = true

        webSettings.savePassword = true
        webSettings.saveFormData = true
    }


    @SuppressLint("SetJavaScriptEnabled")
    fun handleWebSettings(webSettings: WebSettings) {
        webSettings.javaScriptEnabled = true
        // 支持通过JS打开新窗口
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        // 设置可以访问文件
        webSettings.allowFileAccess = true
        webSettings.builtInZoomControls = true
        // 打开本地缓存提供JS调用
        webSettings.domStorageEnabled = true
        webSettings.supportMultipleWindows()
        webSettings.allowContentAccess = true
        // 控制html的布局
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN

        webSettings.useWideViewPort = true
        webSettings.loadWithOverviewMode = true
        webSettings.savePassword = true
        webSettings.saveFormData = true

        webSettings.loadsImagesAutomatically = true
        webSettings.databaseEnabled = true
        webSettings.domStorageEnabled = true
    }

}