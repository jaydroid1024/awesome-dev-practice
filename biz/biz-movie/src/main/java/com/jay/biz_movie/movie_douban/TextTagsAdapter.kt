package com.jay.biz_movie.movie_douban

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.jay.biz_movie.R
import com.moxun.tagcloudlib.view.TagsAdapter
import de.hdodenhof.circleimageview.CircleImageView

/**
 * Created by moxun on 16/1/19.
 */
class TextTagsAdapter(dataSet: ArrayList<String>) : TagsAdapter() {
    private val dataSet: List<String> = dataSet

    override fun getCount(): Int {
        Log.d("jay", "dataSet.size :  ${dataSet.size}")
        return dataSet.size
    }

    override fun getView(context: Context, position: Int, parent: ViewGroup): View {
        val tv = ImageView(context)
        tv.setImageResource(R.mipmap.biz_movie_ic_hat)
        Log.d("jay", "position :  $position")
        Log.d("jay", "dataSet[position] :  ${dataSet[position]}")

        val view = LayoutInflater.from(context).inflate(R.layout.biz_movie_tag_item_view, parent, false)
        val image = (view.findViewById<CircleImageView>(R.id.profile_image) as? CircleImageView)
//        image.setImageResource(R.mipmap.base_component_ic_launcher)
        Glide.with(context)
            .load(dataSet[position])
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(image!!)
        return view
    }

    override fun getItem(position: Int): Any {
        return dataSet[position]
    }

    override fun getPopularity(position: Int): Int {
        return position % 7
    }

    override fun onThemeColorChanged(view: View, themeColor: Int) {
//        view.setBackgroundColor(themeColor)
    }


}