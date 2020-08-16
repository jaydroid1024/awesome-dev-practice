package com.jay.biz_movie.movie_douban

import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jay.biz_movie.R
import com.jay.biz_movie.entity.MovieItem
import com.youth.banner.Banner
import com.youth.banner.loader.ImageLoader

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
class MovieAdapter(data: MutableList<MovieItem>) :
    BaseSectionQuickAdapter<MovieItem, BaseViewHolder>(
        R.layout.biz_movie_section_head,
        R.layout.biz_movie_item_view, data
    ) {

    override fun convert(helper: BaseViewHolder, item: MovieItem) {
        helper.setIsRecyclable(false)//todo
        helper.setText(R.id.text, item.title)
        val itemImg = helper.getView<ImageView>(R.id.icon)
        val banner = helper.getView<Banner>(R.id.banner)
        setBanner(banner, item)
    }

    override fun convertHeader(helper: BaseViewHolder, item: MovieItem) {
        helper.setGone(R.id.more, true)
        helper.setText(R.id.header, item.headerTitle)
    }

    private fun setBanner(banner: Banner, item: MovieItem) {
        val urlList = mutableListOf<String>()
        if (item.photos != null) {
            Log.d("okhttp", item.title+"------------------")
            for (banner in item.photos!!) {
                Log.d("okhttp", banner.image)
                urlList.add(banner.image!!)
            }
        }
        banner.setImageLoader(object : ImageLoader() {
            override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
                val roundedCorners = RoundedCorners(20)
                val bitmapTransform = RequestOptions.bitmapTransform(roundedCorners)
                Glide.with(context!!)
                    .load(path)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .apply(bitmapTransform).into(imageView!!)
            }
        })
        banner.setImages(urlList)
            .isAutoPlay(true)
            .start()
    }

}