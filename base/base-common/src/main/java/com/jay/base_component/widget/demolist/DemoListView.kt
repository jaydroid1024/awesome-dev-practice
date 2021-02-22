package  com.jay.base_component.widget.demolist

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.jay.base_component.R
import com.jay.base_component.arouter.ARHelper
import com.jay.base_component.base.view.BaseRichView
import com.jay.base_lib.utils.ColorUtils
import kotlinx.android.synthetic.main.base_common_demo_list_view.view.*
import java.util.*

/**
 * 通知条提示框,修改身份信息等
 * @author wangxuejie
 * @date 2019-11-07 17:28
 * @version 1.0
 */
class DemoListView : BaseRichView, OnItemClickListener {

    private var demoListData: ArrayList<DemoListEntity> = arrayListOf()

    private var topTv: TextView? = null
    private var topContentTv: TextView? = null
    private var topCl: ConstraintLayout? = null

    var demoAdapter: DemoListAdapter? = null

    var rv: RecyclerView? = null

    var listerner: OnItemClickListener? = null


    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(
        context: Context, attrs: AttributeSet,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr)

    constructor(
        context: Context, attrs: AttributeSet,
        defStyleAttr: Int, defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun initView(view: View) {
        rv = view.findViewById<RecyclerView>(R.id.rv_list)
    }

    /**
     * RV适配器
     */
    private fun getDemoListAdapter(): DemoListAdapter {
        return DemoListAdapter(demoListData).apply {
            setOnItemClickListener(this@DemoListView)
            animationEnable = true
            val top = LayoutInflater.from(context)
                .inflate(R.layout.base_common_demo_list_top_view, rv_list, false)
            topTv = top.findViewById<TextView>(R.id.tv_top)
            topCl = top.findViewById<ConstraintLayout>(R.id.cl_top)
            topContentTv = top.findViewById<TextView>(R.id.tv_top_content)
            top.setOnClickListener {

            }
            addHeaderView(top)
        }
    }


    fun setDemoListData(demoListData: ArrayList<DemoListEntity>) {
        this.demoListData.clear()
        this.demoListData.addAll(demoListData)
        demoAdapter = getDemoListAdapter()
        rv?.adapter = demoAdapter
    }

    fun setTopText(topTitle: String) {
        topContentTv?.visibility = if (topTitle.isNullOrEmpty()) View.GONE else View.VISIBLE
        topTv?.text = topTitle
        topCl?.setBackgroundColor(Color.parseColor(ColorUtils.getRandColor()))
    }

    fun setTopText(topTitle: String, topContent: String) {
        topContentTv?.visibility = if (topContent.isNullOrEmpty()) View.GONE else View.VISIBLE
        topContentTv?.text = topContent
        setTopText(topTitle)
    }

    fun setSpanCount(spanCount: Int) {
        val a = rv?.layoutManager as GridLayoutManager
        a.spanCount = spanCount
    }

    override fun getLayout(): Int {
        return R.layout.base_common_demo_list_view
    }


    fun setOnItemClickListener(onCompletionListener: OnItemClickListener) {
        this.listerner = onCompletionListener
    }

    interface OnItemClickListener {
        fun onItemClick(item: DemoListEntity)
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        val item = adapter.data[position] as DemoListEntity
        if (listerner != null) {
            listerner?.onItemClick(item)
            return
        }
        if (!item.isHeader) {

        }
        if (item.activityPath.isNotEmpty()) {
            ARHelper.routerToWithJson(item.mapParams, item.activityPath)
        }
    }
}
