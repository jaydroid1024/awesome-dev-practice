package com.jay.biz_movie.entity

import com.chad.library.adapter.base.entity.SectionEntity

/**
 * @author wangxuejie
 * @version 1.0
 * @date 2020/8/15
 */
data class MovieItem(
    var id: String = "",
    var index: String = "",
    var originalTitle: String = "",
    var title: String = "",
    var year: String = "",
    var photos: List<Photo>? = null,
    var casts: List<Cast>? = null,


    val headerTitle: String = ""
) : SectionEntity {
    override val isHeader: Boolean get() = headerTitle.isNotBlank()
}