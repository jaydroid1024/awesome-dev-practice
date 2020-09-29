package  com.jay.base_component.widget.demolist

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.jay.base_component.R
import com.jay.base_component.arouter.ARHelper
import com.jay.base_component.base.view.BaseRichView
import kotlinx.android.synthetic.main.base_common_demo_list_view.view.*

/**
 * 通知条提示框,修改身份信息等
 * @author wangxuejie
 * @date 2019-11-07 17:28
 * @version 1.0
 */
class DemoListView : BaseRichView, OnItemClickListener {

    private var demoListData: ArrayList<DemoListEntity> = arrayListOf()

    private var topTv: TextView? = null

    var demoAdapter: DemoListAdapter? = null

    var rv: RecyclerView? = null


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
            val top = LayoutInflater.from(context).inflate(R.layout.base_common_demo_list_top_view, rv_list, false)
            topTv = top.findViewById<TextView>(R.id.tv_top)
            addHeaderView(top)
        }
    }


    /**
     * 获取十六进制的颜色代码.例如  "#5A6677"
     * 分别取R、G、B的随机值，然后加起来即可
     *
     * @return String
     */
    fun getRandColor(): String? {
        var R: String = Integer.toHexString((0..256).random()).toUpperCase()
        var G: String = Integer.toHexString((0..256).random()).toUpperCase()
        var B: String = Integer.toHexString((0..256).random()).toUpperCase()
        R = if (R.length == 1) "0$R" else R
        G = if (G.length == 1) "0$G" else G
        B = if (B.length == 1) "0$B" else B
        return "#$R$G$B"
    }


    fun setDemoListData(demoListData: ArrayList<DemoListEntity>) {
        this.demoListData.clear()
        this.demoListData.addAll(demoListData)
        demoAdapter = getDemoListAdapter()
        rv?.adapter = demoAdapter
    }

    fun setTopText(topImageText: String) {
        topTv?.text = topImageText
        topTv?.setBackgroundColor(Color.parseColor(getRandColor()))
    }


    override fun getLayout(): Int {
        return R.layout.base_common_demo_list_view
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        val item = adapter.data[position] as DemoListEntity
        if (!item.isHeader) {
            ARHelper.routerTo(item.activityPath)
        }
    }
}
