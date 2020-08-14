package com.jay.base_component.config

import com.google.gson.annotations.SerializedName

/**
 * APP配置文件实体类
 * 配置数据来自 Gradle 中缓存的 json 数据或者环境切换后缓存的 json 数据
 *
 * @author 王学杰
 * @version 1.0
 * @date 2019-11-05 18:03
 */
data class AppConfig(

    /**
     * 环境类型名称
     */
    @SerializedName("property_name")
    var PROPERTY_NAME: String? = null,
    /**
     * 环境类型
     */
    @SerializedName("property_type")
    var PROPERTY_TYPE: String? = null,

    /**
     * 玩 Android
     */
    @SerializedName("biz_wan_base_url")
    var BIZ_WAN_BASE_URL: String? = null,

    /**
     * 豆瓣
     */
    @SerializedName("biz_movie_douban_base_url")
    var BIZ_MOVIE_DOUBAN_BASE_URL: String? = null,
    @SerializedName("biz_movie_douban_apikey_1")
    var BIZ_MOVIE_DOUBAN_APIKEY_1: String? = null,
    @SerializedName("biz_movie_douban_apikey_2")
    var BIZ_MOVIE_DOUBAN_APIKEY_2: String? = null


) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AppConfig

        if (PROPERTY_NAME != other.PROPERTY_NAME) return false

        return true
    }

    override fun hashCode(): Int {
        return PROPERTY_NAME?.hashCode() ?: 0
    }
}