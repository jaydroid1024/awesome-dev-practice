package com.jay.biz_movie.movie_douban

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jay.biz_movie.R

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
class MovieAdapter(data: MutableList<MovieItemEntity>) : BaseSectionQuickAdapter<MovieItemEntity, BaseViewHolder>(
    R.layout.def_section_head,
    R.layout.home_item_view,
        data) {

    override fun convert(helper: BaseViewHolder, item: MovieItemEntity) {
        helper.setText(R.id.text, item.name)
        val itemImg=helper.getView<ImageView>(R.id.icon)
        Glide.with(context).load(item.imageResource).into(itemImg)
    }

    override fun convertHeader(helper: BaseViewHolder, item: MovieItemEntity) {
        helper.setGone(R.id.more, true)
        helper.setText(R.id.header, item.headerTitle)
    }

}