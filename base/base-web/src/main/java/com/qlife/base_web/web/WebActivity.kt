package com.qlife.base_web.web

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebStorage
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARHelper
import com.jay.base_component.arouter.ARPath
import com.jay.base_component.base.BaseActivity
import com.jay.base_component.constant.Constants
import com.qlife.base_web.R
import com.qlife.base_web.preload.PreloadWebView
import com.tencent.smtt.sdk.CookieManager
import com.tencent.smtt.sdk.CookieSyncManager
import com.tencent.smtt.sdk.WebView
import kotlinx.android.synthetic.main.base_web_activity_web.*

@Route(path = ARPath.PathWeb.WEB_ACTIVITY_PATH)
class WebActivity : BaseActivity() {

    private var mBackIv: ImageView? = null

    private var mTitleTv: TextView? = null

    private var mWebView: WebView? = null

    private var mUrl: String? = null

    private var mTitle: String? = null

    override fun getLayoutResId(): Int {
        return R.layout.base_web_activity_web
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initIntent()
        initViews()
        setWebView()
    }

    private fun initIntent() {
        val mapParams = ARHelper.getParamsMapFromIntentByJson(intent)
        mUrl = ARHelper.getStrFromParamsMap(mapParams, Constants.MapKey.URL)
        mTitle = ARHelper.getStrFromParamsMap(mapParams, Constants.MapKey.TITLE)
    }

    private fun setWebView() {
        if (mWebView == null) return
        mWebView?.loadUrl(mUrl)
    }

    private fun initViews() {
        mTitleTv = findViewById<TextView>(R.id.tv_title)
        mBackIv = findViewById<ImageView>(R.id.iv_back)
        mBackIv?.setOnClickListener { onBackPressed() }
        mTitleTv?.text = mTitle ?: mUrl
        mWebView = PreloadWebView.getWebView(this@WebActivity)
        ll_content.addView(
            mWebView,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
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
        clearCookies(this)
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
        PreloadWebView.preload()
    }

    private fun clearCookies(context: Context) {
        CookieSyncManager.createInstance(context)
        val cookieManager: CookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true)
        cookieManager.removeSessionCookie()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.removeAllCookies(null)
            CookieManager.getInstance().flush()
        } else {
            cookieManager.removeAllCookie()
            CookieSyncManager.getInstance().sync()
        }
        WebStorage.getInstance().deleteAllData()
    }

}