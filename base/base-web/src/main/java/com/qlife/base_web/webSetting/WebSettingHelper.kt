package com.qlife.base_web.webSetting

import com.tencent.smtt.sdk.WebView

/**
 * @author wangxuejie
 * @version 1.0
 * @date 1/22/21
 */
object WebSettingHelper {


    fun configX5WebSettings(webView: WebView) {
        X5WebSetting.configX5WebSettings(webView)
    }

    fun configWebSettings(webView: android.webkit.WebView) {
        WebSetting.configWebSetting(webView)

    }

}