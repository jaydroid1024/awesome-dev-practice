package com.qlife.base_web.webClient

import android.graphics.Bitmap
import android.os.Build
import android.util.Log
import com.qlife.base_web.preload.WebPreloadHelper
import com.qlife.base_web.preload.WebPreloadHelper.TAG
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import java.util.*

/**
 * @author wangxuejie
 * @version 1.0
 * @date 1/22/21
 */
open class BaseX5WebViewClient : WebViewClient() {

    val webStorageMap = HashMap<String, String>(5)

    override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
        Log.d(TAG, "BaseX5WebViewClient,shouldOverrideUrlLoading,url=$url, view=${view.hashCode()}")
        if (view == null) {
            return false
        }
        Log.d(TAG, "BaseX5WebViewClient,overrideLoadUrl,url=$url, view=${view.hashCode()}")
        view.loadUrl(url)
        return true
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        Log.d(
            TAG,
            "BaseX5WebViewClient,onPageStarted,url=$url,,favicon=${favicon.toString()}, view=${view.hashCode()}"
        )
        addLocalStorages(view)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        Log.d(TAG, "BaseX5WebViewClient,onPageFinished,url=$url, view=${view.hashCode()}")
        addLocalStorages(view)
    }


    private fun addLocalStorages(view: WebView?) {
        if (view == null) {
            return
        }
        webStorageMap.forEach {
            val key = it.key
            val value = it.value
            addLocalStorage(view, key, value)
        }
    }

    fun setWebStorageMap(map: HashMap<String, String>) {
        webStorageMap.clear()
        webStorageMap.putAll(map)
    }


    /**
     * 向LocalStorage 中添加键值对
     *
     * @param view
     * @param key
     * @param value
     */
    fun addLocalStorage(view: WebView, key: String, value: String) {
        val js = "window.localStorage.setItem('" + key + "','" + value + "');"
        val jsUrl =
            "javascript:(function({ var localStorage = window.localStorage; localStorage.setItem('" + key + "','" + value + "') })()"

        Log.d(WebPreloadHelper.TAG, "CTX5WebViewClient,addLocalStorage,js: $js")
        Log.d(WebPreloadHelper.TAG, "CTX5WebViewClient,addLocalStorage,jsUrl: $jsUrl")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            view.evaluateJavascript(js, null)
        } else {
            view.loadUrl(jsUrl)
            view.reload()
        }
    }


}