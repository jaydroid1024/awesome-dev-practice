package com.jay.base_web.web

import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARHelper
import com.jay.base_component.arouter.ARPath
import com.jay.base_component.base.BaseActivity
import com.jay.base_component.constant.Constants
import com.jay.base_web.R
import kotlinx.android.synthetic.main.base_web_activity_test.*
import java.util.*


@Route(path = ARPath.PathWeb.WEB_TEST_ACTIVITY_PATH )
class TestActivity : BaseActivity() {

    private var webUrl = "https://www.github.com"

    private var pdfUrl = "https://css4.pub/2015/textbook/somatosensory.pdf"


    override fun initView() {
        et_web_first.setText(webUrl)
        et_web_sec.setText(pdfUrl)

        btn_web_first.setOnClickListener {
            if (et_web_first.text.isNotEmpty()) {
                webUrl = et_web_first.text.toString()
                goToWeb(et_web_first.hint.toString())
            }
        }

        btn_web_sec.setOnClickListener {
            if (et_web_sec.text.isNotEmpty()) {
                webUrl = et_web_sec.text.toString()
                goToWeb(et_web_sec.hint.toString())
            }
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.base_web_activity_test
    }

    private fun goToWeb(title: String) {
        val mapParams =
            getParamsMap(
                webUrl,
                title
            )
        ARHelper.routerToWithJson(
            mapParams,
            ARPath.PathWeb.WEB_ACTIVITY_PATH
        )
    }

    private fun getParamsMap(url: String, title: String): HashMap<String, Any> {
        val mapParams = HashMap<String, Any>()
        mapParams[Constants.MapKey.URL] = url
        mapParams[Constants.MapKey.TITLE] = title
        return mapParams
    }
}
