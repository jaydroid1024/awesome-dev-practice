package com.jay.biz_movie.entity

import com.google.gson.annotations.SerializedName


/**
 * @author wangxuejie
 * @version 1.0
 * @date 2020/8/15
 */
data class MovieListResponse(
    @SerializedName("count")
    var count: Int?,
    @SerializedName("start")
    var start: Int?,
    @SerializedName("subjects")
    var subjects: List<Subject>?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("total")
    var total: Int?
)

data class Subject(
    @SerializedName("alt")
    var alt: String?,
    @SerializedName("casts")
    var casts: List<Cast>?,
    @SerializedName("collect_count")
    var collectCount: Int?,
    @SerializedName("directors")
    var directors: List<Director>?,
    @SerializedName("durations")
    var durations: List<String>?,
    @SerializedName("genres")
    var genres: List<String>?,
    @SerializedName("has_video")
    var hasVideo: Boolean?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("images")
    var images: Images?,
    @SerializedName("mainland_pubdate")
    var mainlandPubdate: String?,
    @SerializedName("original_title")
    var originalTitle: String?,
    @SerializedName("pubdates")
    var pubdates: List<String>?,
    @SerializedName("rating")
    var rating: Rating?,
    @SerializedName("subtype")
    var subtype: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("year")
    var year: String?
)

data class Cast(
    @SerializedName("alt")
    var alt: String?,
    @SerializedName("avatars")
    var avatars: Avatars?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("name_en")
    var nameEn: String?
)

data class Director(
    @SerializedName("alt")
    var alt: String?,
    @SerializedName("avatars")
    var avatars: AvatarsX?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("name_en")
    var nameEn: String?
)

data class Images(
    @SerializedName("large")
    var large: String?,
    @SerializedName("medium")
    var medium: String?,
    @SerializedName("small")
    var small: String?
)

data class Rating(
    @SerializedName("average")
    var average: Double?,
    @SerializedName("details")
    var details: HashMap<String, String>?,
    @SerializedName("max")
    var max: Int?,
    @SerializedName("min")
    var min: Int?,
    @SerializedName("stars")
    var stars: String?
)

data class Avatars(
    @SerializedName("large")
    var large: String?,
    @SerializedName("medium")
    var medium: String?,
    @SerializedName("small")
    var small: String?
)

data class AvatarsX(
    @SerializedName("large")
    var large: String?,
    @SerializedName("medium")
    var medium: String?,
    @SerializedName("small")
    var small: String?
)

