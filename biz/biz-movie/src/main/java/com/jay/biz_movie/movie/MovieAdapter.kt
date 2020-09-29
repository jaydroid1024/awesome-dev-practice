package com.jay.biz_movie.movie

import android.util.Log
import android.widget.ImageView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jay.biz_movie.R
import com.jay.biz_movie.entity.MovieItem
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
class MovieAdapter(data: MutableList<MovieItem>) :
    BaseSectionQuickAdapter<MovieItem, BaseViewHolder>(
        R.layout.biz_movie_section_head,
        R.layout.biz_movie_item_view, data
    ) {
    var transformerList: ArrayList<Class<out ViewPager.PageTransformer>> = ArrayList()

    override fun convert(helper: BaseViewHolder, item: MovieItem) {
        helper.setIsRecyclable(false)//todo
        helper.setText(R.id.text, item.year + "\n 《" + item.title + "》 ")
        helper.setText(R.id.text_sub, item.originalTitle)
        helper.setText(R.id.index, "" + (item.index.toInt() + 1))

        val itemImg = helper.getView<ImageView>(R.id.icon)
        val banner = helper.getView(R.id.banner) as Banner<String, BannerImageAdapter<String>>
        setBanner(banner, item, helper.adapterPosition)
    }

    override fun convertHeader(helper: BaseViewHolder, item: MovieItem) {
        helper.setText(R.id.header, item.headerTitle)
    }

    private fun setBanner(
        banner: Banner<*,*>,
        item: MovieItem,
        adapterPosition: Int
    ) {
        val urlList = mutableListOf<String>()
        if (item.photos != null) {
            for (banner in item.photos!!) {
                Log.d("okhttp", banner.image)
                urlList.add(banner.image!!)
            }
        }

        //方法一：使用自定义图片适配器
//                banner.setAdapter(new ImageNetAdapter(DataBean.getTestData3()));

        //方法二：使用自带的图片适配器
        banner.adapter = object : BannerImageAdapter<String>(urlList) {
            override fun onBindView(
                holder: BannerImageHolder?,
                data: String?,
                position: Int,
                size: Int
            ) {
                //图片加载自己实现
                Glide.with(holder?.itemView!!)
                    .load(data)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
                    .into(holder.imageView)
            }
        }
    }



}