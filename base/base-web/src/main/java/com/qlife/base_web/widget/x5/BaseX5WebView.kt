package com.qlife.base_web.widget.x5

import android.content.Context
import android.util.AttributeSet
import com.qlife.base_web.widget.loading.WebProgressBar
import com.tencent.smtt.sdk.WebView

/**
 * 腾讯 x5 webview 基础类
 * @author wangxuejie
 * @version 1.0
 * @date 2020/8/3
 */
open class BaseX5WebView : WebView {

    private var webProgressBar: WebProgressBar? = null


    private var isProgressBarAdded: Boolean = false

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int)
            : super(context, attrs, defStyle) {
        init()
    }

    fun init() {
        //todo
    }


    /**
     * 设置 ProgressBar 是否可见
     */
    open fun setLoadingViewVisible(visible: Boolean) {

    }
}