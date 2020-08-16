package com.jay.biz_movie.movie_douban.mvp

import com.jay.base_component.base.mvp.BaseObserver
import com.jay.base_component.base.mvp.BasePresenter
import com.jay.biz_movie.entity.MovieDetailsResponse
import com.jay.biz_movie.entity.MovieListResponse
import com.jay.biz_movie.net.BizMovieNetFactory

class MoviePresenter : BasePresenter<MovieContract.View>(), MovieContract.Presenter {

    /**
     * 正在热映：
     * https://api.douban.com/v2/movie/in_theaters?apikey=0df993c66c0c636e29ecbb5344252a4a&start=0&count=10
     */
    override fun getMCUMovie() {
        val start = 0
        val count = 10
        val observable = BizMovieNetFactory
            .network()
            .getMCUMovie(start, count)
        addSubscribe(observable, object : BaseObserver<MovieListResponse?>(getView()) {
            override fun onSuccess(data: MovieListResponse?) {
                getView()?.getMCUMovieSuccess(data)
            }
        })

    }

    /**
     * ### 电影详情：
     * https://api.douban.com/v2/movie/subject/1292052?apikey=0df993c66c0c636e29ecbb5344252a4a
     */
    override fun getMovieDetails(movieId: String) {
        val observable = BizMovieNetFactory
            .network()
            .getMovieDetails(movieId)
        addSubscribe(observable, object : BaseObserver<MovieDetailsResponse?>(getView()) {
            override fun onSuccess(data: MovieDetailsResponse?) {
                getView()?.getMovieDetailsSuccess(data)
            }
        })

    }


}