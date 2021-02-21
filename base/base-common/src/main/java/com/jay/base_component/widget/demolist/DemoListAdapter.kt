package com.jay.base_component.widget.demolist

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jay.base_component.R
import com.jay.base_lib.utils.ColorUtils

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
class DemoListAdapter(data: MutableList<DemoListEntity>?) :
    BaseSectionQuickAdapter<DemoListEntity, BaseViewHolder>(
        R.layout.base_common_demo_list_section,
        R.layout.base_common_demo_list_item_view,
        data
    ) {

    override fun convert(helper: BaseViewHolder, item: DemoListEntity) {
        helper.setText(R.id.text, item.name)
        if (item.imageResource != -1) {
            helper.getView<ImageView>(R.id.icon).visibility = View.VISIBLE
            helper.setImageResource(
                R.id.icon, if (item.imageResource == 0) R.mipmap.ic_bald else item.imageResource
            )
        } else {
            helper.getView<ImageView>(R.id.icon).visibility = View.GONE
        }
        if (item.isHeader) {
            val card = helper.getView<LinearLayout>(R.id.ll_section)
            card.setBackgroundColor(Color.parseColor(ColorUtils.getRandColor()))
        }
    }

    override fun convertHeader(helper: BaseViewHolder, item: DemoListEntity) {
        helper.setGone(R.id.more, true)
        helper.setText(R.id.header, item.headerTitle)
    }

}