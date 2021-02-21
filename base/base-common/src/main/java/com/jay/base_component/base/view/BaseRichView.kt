package com.jay.base_component.base.view

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout

/**
 * 自定义View的基类
 * @author wangxuejie
 * @date 2019-11-07 17:13
 * @version 1.0
 */
abstract class BaseRichView : FrameLayout {

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        init(context, attrs)
    }

    /**
     * 初始化自定义View,获取自定义属性可以重写该方法
     */
    protected fun init(context: Context, attrs: AttributeSet?) {
        val view = LayoutInflater.from(context).inflate(getLayout(), this, true)
        initView(view)
        initData()
    }

    /**
     * 获取自定义布局
     */
    abstract fun getLayout(): Int

    /**
     * 获取布局控件，初始化页面数据
     */
    abstract fun initView(view: View)

    /**
     * 初始化数据
     */
    fun initData() {}


}