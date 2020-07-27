package com.jay.base_web.fileview

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARHelper
import com.jay.base_component.arouter.ARPath
import com.jay.base_component.base.BaseActivity
import com.jay.base_component.constant.Constants
import com.jay.base_web.R
import kotlinx.android.synthetic.main.base_pdf_activity_test.*
import java.util.*

@Route(path = ARPath.PathWeb.FILE_VIEW_TEST_ACTIVITY_PATH)
class TestActivity : BaseActivity() {

    private var pdfUrl = "https://css4.pub/2015/textbook/somatosensory.pdf"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        et_web_first.setText(pdfUrl)
        et_web_sec.setText("https://www.baidu.com")

        btn_web_first.setOnClickListener {
            if (et_web_first.text.isNotEmpty()) {
                pdfUrl = et_web_first.text.toString()
                goToPdfPage(et_web_first.hint.toString(), pdfUrl)
            }
        }

        btn_web_sec.setOnClickListener {
            if (et_web_sec.text.isNotEmpty()) {
                pdfUrl = et_web_sec.text.toString()
                goToWeb(et_web_sec.hint.toString(), pdfUrl)
            }
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.base_pdf_activity_test
    }

    private fun goToPdfPage(title: String, url: String) {
        val mapParams = HashMap<String, Any>()
        mapParams[Constants.MapKey.URL] = url
        mapParams[Constants.MapKey.TITLE] = url
        ARHelper.routerToWithJson(
            mapParams,
            ARPath.PathWeb.FILE_VIEW_ACTIVITY_PATH
        )
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
