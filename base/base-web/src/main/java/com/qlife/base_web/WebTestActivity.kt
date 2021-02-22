package com.qlife.base_web

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARHelper
import com.jay.base_component.arouter.ARPath
import com.jay.base_component.base.BaseActivity
import com.jay.base_component.constant.Constants
import kotlinx.android.synthetic.main.base_web_activity_test.*
import java.util.*

@Route(path = ARPath.PathWeb.WEB_TEST_ACTIVITY_PATH)
class WebTestActivity : BaseActivity() {

    //    private var webUrl = "https://www.github.com"
    private var webUrl = "https://ct-dev.o3cloud.cn/#/?k=wx_nav"

    private var pdfUrl = "https://css4.pub/2015/textbook/somatosensory.pdf"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        et_web_first.setText(webUrl)
        et_web_sec.setText(pdfUrl)

        btn_web_first.setOnClickListener {
            if (et_web_first.text.isNotEmpty()) {
                webUrl = et_web_first.text.toString()
                goToWeb(webUrl, webUrl)
            }
        }


        btn_web_sec.setOnClickListener {
            if (et_web_sec.text.isNotEmpty()) {
                webUrl = et_web_sec.text.toString()

            }
        }

    }

    override fun getLayoutResId(): Int {
        return R.layout.base_web_activity_test
    }


    private fun goToWeb(title: String, url: String) {
        val mapParams = HashMap<String, Any>()
        mapParams[Constants.MapKey.URL] = url
        mapParams[Constants.MapKey.TITLE] = url
        ARHelper.routerToWithJson(
            mapParams,
            ARPath.PathWeb.WEB_ACTIVITY_PATH
        )
    }


}
