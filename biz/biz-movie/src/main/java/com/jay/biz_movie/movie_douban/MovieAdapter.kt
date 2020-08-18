package com.jay.biz_movie.movie_douban

import android.content.Context
import android.util.Log
import android.widget.ImageView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jay.biz_movie.R
import com.jay.biz_movie.entity.MovieItem
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader

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
        if (item.index.toInt() == 0) {
            helper.setGone(R.id.view_line, true)
        } else {
            helper.setGone(R.id.view_line, false)
        }


        val itemImg = helper.getView<ImageView>(R.id.icon)
        val banner = helper.getView<Banner>(R.id.banner)
        setBanner(banner, item, helper.adapterPosition)
    }

    override fun convertHeader(helper: BaseViewHolder, item: MovieItem) {
        helper.setText(R.id.header, item.headerTitle)
    }

    private fun setBanner(
        banner: Banner,
        item: MovieItem,
        adapterPosition: Int
    ) {
        val urlList = mutableListOf<String>()
        if (item.photos != null) {
            Log.d("okhttp", item.title + "------------------")
            Log.d("okhttp", "--------" + item.photos!![0].alt)
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
        banner.isEnabled = false
        banner.setBannerStyle(BannerConfig.NOT_INDICATOR)
//        banner.setBannerAnimation(getTransformer(adapterPosition))
        banner.setBannerAnimation(ZoomOutTranformer::class.java)
        banner.setDelayTime(800)
        banner.setImages(urlList)
            .isAutoPlay(true)
            .start()
    }

    private fun getTransformer(adapterPosition: Int): Class<out ViewPager.PageTransformer>? {
        transformerList.add(Transformer.Default)
        transformerList.add(Transformer.Accordion)
        transformerList.add(Transformer.BackgroundToForeground)
        transformerList.add(Transformer.ForegroundToBackground)
        transformerList.add(Transformer.CubeIn)
        transformerList.add(Transformer.CubeOut)
        transformerList.add(Transformer.DepthPage)
        transformerList.add(Transformer.FlipHorizontal)
        transformerList.add(Transformer.FlipVertical)
        transformerList.add(Transformer.RotateDown)
        transformerList.add(Transformer.RotateUp)
        transformerList.add(Transformer.ScaleInOut)
        transformerList.add(Transformer.Stack)
        transformerList.add(Transformer.Tablet)
        transformerList.add(Transformer.ZoomIn)
        transformerList.add(Transformer.ZoomOut)
        transformerList.add(Transformer.ZoomOutSlide)
//        val randomArray = NumberUtils.getRandomNumArray(0, transformerList.size - 1, 1)
//        return transformerList[randomArray[0]]
        return transformerList[adapterPosition % transformerList.size]
    }


}