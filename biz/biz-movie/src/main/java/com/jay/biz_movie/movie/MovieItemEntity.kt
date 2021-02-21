package com.jay.biz_movie.movie

import com.chad.library.adapter.base.entity.SectionEntity

/**
 * @author: limuyang
 * @date: 2019-12-06
 * @Description:
 */
data class MovieItemEntity(
    val name: String = "",
    val activityPath: String = "",
    val imageResource: String = "",
    val headerTitle: String = ""
) : SectionEntity {
    override val isHeader: Boolean get() = headerTitle.isNotBlank()
}