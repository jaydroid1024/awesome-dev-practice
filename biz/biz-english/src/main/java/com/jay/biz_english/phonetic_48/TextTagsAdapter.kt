package com.jay.biz_english.phonetic_48

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.jay.biz_english.R
import com.jay.biz_english.data.EnConstants
import com.jay.biz_english.data.PhoneticItem
import com.moxun.tagcloudlib.view.TagsAdapter

/**
 * Created by moxun on 16/1/19.
 */
class TextTagsAdapter(dataSet: MutableList<PhoneticItem>) : TagsAdapter() {
    private val dataSet: List<PhoneticItem> = dataSet

    override fun getCount(): Int {
        return dataSet.size
    }

    override fun getView(context: Context, position: Int, parent: ViewGroup): View {
        val view =
            LayoutInflater.from(context).inflate(R.layout.biz_english_tag_item_view, parent, false)
        val item = dataSet[position]
        val tvText = view.findViewById<TextView>(R.id.tv_text)
        val llInfo = view.findViewById<LinearLayout>(R.id.ll_info)
        tvText.text = item.name

        if (item.type1 == EnConstants.PhoneticType.TYPE_VOWEL_1) {
            val white = ContextCompat.getColor(context, R.color.white)
            tvText.setTextColor(white)
            if (item.isChecked) {
                llInfo.setBackgroundResource(R.drawable.base_common_bg_circle_dark_radius_red)
            } else {
                llInfo.setBackgroundResource(R.drawable.base_common_bg_circle_dark_radius_white)
            }
        } else {
            val yellow = ContextCompat.getColor(context, R.color.text_movie)
            tvText.setTextColor(yellow)
            if (item.isChecked) {
                llInfo.setBackgroundResource(R.drawable.base_common_bg_circle_dark_radius_red)
            } else {
                llInfo.setBackgroundResource(R.drawable.base_common_bg_circle_dark_radius_yellow)
            }
        }
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