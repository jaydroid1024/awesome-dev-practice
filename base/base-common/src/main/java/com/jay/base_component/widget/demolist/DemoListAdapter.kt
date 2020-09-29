package com.jay.base_component.widget.demolist

import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jay.base_component.R

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
        helper.setImageResource(R.id.icon, item.imageResource)
    }

    override fun convertHeader(helper: BaseViewHolder, item: DemoListEntity) {
        helper.setGone(R.id.more, true)
        helper.setText(R.id.header, item.headerTitle)
    }

}