package com.jay.biz_android

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARPath
import com.jay.base_component.constant.Constants
import com.jay.base_component.widget.demolist.DemoListEntity
import kotlinx.android.synthetic.main.biz_android_activity_android_main.*
import java.util.*

@Route(path = ARPath.PathAlgorithm.ALGORITHM_MAIN_ACTIVITY_PATH)
class AlgorithmMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.biz_android_activity_android_main)
        setDemoList()
    }

    private fun setDemoList() {
        dv_demo.setSpanCount(1)
        dv_demo.setDemoListData(demoItemData)
        dv_demo.setTopText("数据与算法")

    }

    private val demoItemData: ArrayList<DemoListEntity>
        get() = arrayListOf(
            DemoListEntity(
                headerTitle = "链表",
                activityPath = ARPath.PathWeb.WEB_ACTIVITY_PATH,
                mapParams = getMapParams()
            ),
            DemoListEntity(
                name = "链表问题-GitHub",
                activityPath = ARPath.PathWeb.WEB_ACTIVITY_PATH,
                mapParams = getMapParams()
            ),
        )

    private fun getMapParams(): HashMap<String, Any> {
        val map = HashMap<String, Any>(2)
        val url =
            "https://github.com/jaydroid1024/awesome-dev-note/blob/main/notes/02-Thinking/01-%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E4%B8%8E%E7%AE%97%E6%B3%95/01-%E9%93%BE%E8%A1%A8.md"
        map[Constants.MapKey.TITLE] = url
        map[Constants.MapKey.URL] = url
        return map
    }
}
