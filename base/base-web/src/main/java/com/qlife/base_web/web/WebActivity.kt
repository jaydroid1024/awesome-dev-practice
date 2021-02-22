package com.qlife.base_web.web

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARHelper
import com.jay.base_component.arouter.ARPath
import com.jay.base_component.base.BaseActivity
import com.jay.base_component.constant.Constants
import com.qlife.base_web.R
import com.qlife.base_web.preload.WebPreloadHelper
import com.qlife.base_web.preload.WebX5Helper
import com.qlife.base_web.widget.x5.BaseX5WebView
import kotlinx.android.synthetic.main.base_web_activity_web.*

@Route(path = ARPath.PathWeb.WEB_ACTIVITY_PATH)
class WebActivity : BaseActivity() {

    private var mBackIv: ImageView? = null

    private var mTitleTv: TextView? = null

    private var mWebView: BaseX5WebView? = null

    private var mUrl: String? = null

    private var mTitle: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initIntent()
        initView()
        setWebView()
    }

    private fun initIntent() {
        val mapParams = ARHelper.getParamsMapFromIntentByJson(intent)
        mUrl = ARHelper.getStrFromParamsMap(mapParams, Constants.MapKey.URL)
        mTitle = ARHelper.getStrFromParamsMap(mapParams, Constants.MapKey.TITLE)
        Log.d(TAG, "mUrl: $mUrl")
        Log.d(TAG, "mTitle: $mTitle")

    }


    private fun setWebView() {
        if (mWebView == null) return
        mWebView?.loadUrl(mUrl!!)
    }


    override fun initView() {
        mTitleTv = findViewById<TextView>(R.id.tv_title)
        mBackIv = findViewById<ImageView>(R.id.iv_back)
        mBackIv?.setOnClickListener { onBackPressed() }
        mTitleTv?.text = mUrl
        mWebView = WebX5Helper.createX5WebView()
        fl_web_container.addView(
            mWebView,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
    }

    override fun getLayoutResId(): Int {
        return R.layout.base_web_activity_web

    }

    override fun onBackPressed() {
        if (mWebView != null) {
            if (mWebView!!.canGoBack()) {
                mWebView!!.goBack()
            } else {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        if (mWebView != null) {
            val parent = mWebView!!.parent
            if (parent != null) {
                (parent as ViewGroup).removeView(mWebView)
            }
            mWebView!!.removeAllViews()
            mWebView!!.destroy()
            mWebView = null
        }
        //缓存WebView,下次进入使用
        WebPreloadHelper.preloadX5Web()
    }

    companion object {

        const val TAG = "Web"
    }


}