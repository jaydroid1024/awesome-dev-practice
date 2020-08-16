package com.jay.biz_movie.entity

import com.google.gson.annotations.SerializedName


/**
 * @author wangxuejie
 * @version 1.0
 * @date 2020/8/15
 */
data class MovieDetailsResponse(
    @SerializedName("aka")
    var aka: List<String>?,
    @SerializedName("alt")
    var alt: String?,
    @SerializedName("blooper_urls")
    var blooperUrls: List<Any>?,
    @SerializedName("bloopers")
    var bloopers: List<Any>?,
    @SerializedName("casts")
    var casts: List<Cast>?,
    @SerializedName("clip_urls")
    var clipUrls: List<Any>?,
    @SerializedName("clips")
    var clips: List<Any>?,
    @SerializedName("collect_count")
    var collectCount: Int?,
    @SerializedName("collection")
    var collection: Any?,
    @SerializedName("comments_count")
    var commentsCount: Int?,
    @SerializedName("countries")
    var countries: List<String>?,
    @SerializedName("current_season")
    var currentSeason: Any?,
    @SerializedName("directors")
    var directors: List<Director>?,
    @SerializedName("do_count")
    var doCount: Any?,
    @SerializedName("douban_site")
    var doubanSite: String?,
    @SerializedName("durations")
    var durations: List<String>?,
    @SerializedName("episodes_count")
    var episodesCount: Any?,
    @SerializedName("genres")
    var genres: List<String>?,
    @SerializedName("has_schedule")
    var hasSchedule: Boolean?,
    @SerializedName("has_ticket")
    var hasTicket: Boolean?,
    @SerializedName("has_video")
    var hasVideo: Boolean?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("images")
    var images: Images?,
    @SerializedName("languages")
    var languages: List<String>?,
    @SerializedName("mainland_pubdate")
    var mainlandPubdate: String?,
    @SerializedName("mobile_url")
    var mobileUrl: String?,
    @SerializedName("original_title")
    var originalTitle: String?,
    @SerializedName("photos")
    var photos: List<Photo>?,
    @SerializedName("photos_count")
    var photosCount: Int?,
    @SerializedName("popular_comments")
    var popularComments: List<PopularComment>?,
    @SerializedName("popular_reviews")
    var popularReviews: List<PopularReview>?,
    @SerializedName("pubdate")
    var pubdate: String?,
    @SerializedName("pubdates")
    var pubdates: List<String>?,
    @SerializedName("rating")
    var rating: RatingXX?,
    @SerializedName("ratings_count")
    var ratingsCount: Int?,
    @SerializedName("reviews_count")
    var reviewsCount: Int?,
    @SerializedName("schedule_url")
    var scheduleUrl: String?,
    @SerializedName("seasons_count")
    var seasonsCount: Any?,
    @SerializedName("share_url")
    var shareUrl: String?,
    @SerializedName("subtype")
    var subtype: String?,
    @SerializedName("summary")
    var summary: String?,
    @SerializedName("tags")
    var tags: List<String>?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("trailer_urls")
    var trailerUrls: List<String>?,
    @SerializedName("trailers")
    var trailers: List<Trailer>?,
    @SerializedName("videos")
    var videos: List<Video>?,
    @SerializedName("website")
    var website: String?,
    @SerializedName("wish_count")
    var wishCount: Int?,
    @SerializedName("writers")
    var writers: List<Writer>?,
    @SerializedName("year")
    var year: String?
)


data class Photo(
    @SerializedName("alt")
    var alt: String?,
    @SerializedName("cover")
    var cover: String?,
    @SerializedName("icon")
    var icon: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("image")
    var image: String?,
    @SerializedName("thumb")
    var thumb: String?
)

data class PopularComment(
    @SerializedName("author")
    var author: Author?,
    @SerializedName("content")
    var content: String?,
    @SerializedName("created_at")
    var createdAt: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("rating")
    var rating: Rating?,
    @SerializedName("subject_id")
    var subjectId: String?,
    @SerializedName("useful_count")
    var usefulCount: Int?
)

data class PopularReview(
    @SerializedName("alt")
    var alt: String?,
    @SerializedName("author")
    var author: AuthorX?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("rating")
    var rating: RatingX?,
    @SerializedName("subject_id")
    var subjectId: String?,
    @SerializedName("summary")
    var summary: String?,
    @SerializedName("title")
    var title: String?
)

data class RatingXX(
    @SerializedName("average")
    var average: Double?,
    @SerializedName("details")
    var details: Details?,
    @SerializedName("max")
    var max: Int?,
    @SerializedName("min")
    var min: Int?,
    @SerializedName("stars")
    var stars: String?
)

data class Trailer(
    @SerializedName("alt")
    var alt: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("medium")
    var medium: String?,
    @SerializedName("resource_url")
    var resourceUrl: String?,
    @SerializedName("small")
    var small: String?,
    @SerializedName("subject_id")
    var subjectId: String?,
    @SerializedName("title")
    var title: String?
)

data class Video(
    @SerializedName("need_pay")
    var needPay: Boolean?,
    @SerializedName("sample_link")
    var sampleLink: String?,
    @SerializedName("source")
    var source: Source?,
    @SerializedName("video_id")
    var videoId: String?
)

data class Writer(
    @SerializedName("alt")
    var alt: String?,
    @SerializedName("avatars")
    var avatars: AvatarsXX?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("name_en")
    var nameEn: String?
)



data class Author(
    @SerializedName("alt")
    var alt: String?,
    @SerializedName("avatar")
    var avatar: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("signature")
    var signature: String?,
    @SerializedName("uid")
    var uid: String?
)


data class AuthorX(
    @SerializedName("alt")
    var alt: String?,
    @SerializedName("avatar")
    var avatar: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("signature")
    var signature: String?,
    @SerializedName("uid")
    var uid: String?
)

data class RatingX(
    @SerializedName("max")
    var max: Int?,
    @SerializedName("min")
    var min: Int?,
    @SerializedName("value")
    var value: Int?
)

data class Details(
    @SerializedName("1")
    var x1: Int?,
    @SerializedName("2")
    var x2: Int?,
    @SerializedName("3")
    var x3: Int?,
    @SerializedName("4")
    var x4: Int?,
    @SerializedName("5")
    var x5: Int?
)

data class Source(
    @SerializedName("literal")
    var literal: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("pic")
    var pic: String?
)

data class AvatarsXX(
    @SerializedName("large")
    var large: String?,
    @SerializedName("medium")
    var medium: String?,
    @SerializedName("small")
    var small: String?
)