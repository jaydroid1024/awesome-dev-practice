package com.jay.dev

import com.chad.library.adapter.base.entity.SectionEntity
import java.util.*

/**
 * @author: limuyang
 * @date: 2019-12-06
 * @Description:
 */
data class HomeEntity(
    val name: String = "",
    val activityPath: String = "",
    val imageResource: Int = 0,
    val movieType: String = "1",
    val mapParams: HashMap<String, Any> = HashMap<String, Any>(),
    val headerTitle: String = ""
) : SectionEntity {
    override val isHeader: Boolean get() = headerTitle.isNotBlank()
}