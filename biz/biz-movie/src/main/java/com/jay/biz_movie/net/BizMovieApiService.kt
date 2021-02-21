package com.jay.biz_movie.net

import com.jay.biz_movie.entity.MovieDetailsResponse
import com.jay.biz_movie.entity.MovieListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * BossApiService
 * @author wangxuejie
 * @date 2019-12-25 17:51
 * @version 1.0
 */
interface BizMovieApiService {

    /**
     * 正在热映：
     * https://api.douban.com/v2/movie/in_theaters?
     * apikey=0df993c66c0c636e29ecbb5344252a4a
     * &start=0
     * &count=10
     */
    @GET("v2/movie/in_theaters")
    fun getMCUMovie(
        @Query("apikey") keyword: String?,
        @Query("start") start: Int,
        @Query("count") count: Int
    ): Observable<MovieListResponse?>


    /**
     * ### 电影详情：
     * https://api.douban.com/v2/movie/subject/1292052?apikey=0df993c66c0c636e29ecbb5344252a4a
     */
    @GET("v2/movie/subject/{movieId}")
    fun getMovieDetails(
        @Path("movieId") movieId: String,
        @Query("apikey") apikey: String?
    ): Observable<MovieDetailsResponse?>


}
