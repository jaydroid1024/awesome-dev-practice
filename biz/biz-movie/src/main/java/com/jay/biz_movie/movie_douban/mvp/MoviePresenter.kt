package com.jay.biz_movie.movie_douban.mvp

import com.jay.base_component.base.mvp.BaseObserver
import com.jay.base_component.base.mvp.BasePresenter
import com.jay.base_component.config.AppConfigHelper
import com.jay.biz_movie.entity.MovieListResponse
import com.jay.biz_movie.net.BizMovieNetFactory

class MoviePresenter : BasePresenter<MovieContract.View>(), MovieContract.Presenter {

    /**
     * 正在热映：
     * https://api.douban.com/v2/movie/in_theaters?apikey=0df993c66c0c636e29ecbb5344252a4a&start=0&count=10
     */
    override fun getMCUMovie() {
        val apikey = AppConfigHelper.getAppConfig()?.BIZ_MOVIE_DOUBAN_APIKEY_1
        val start = 0
        val count = 10
        val observable = BizMovieNetFactory
            .network()
            .getMCUMovie(apikey, start, count)

        addSubscribe(observable, object : BaseObserver<MovieListResponse?>(getView()) {
            override fun onSuccess(data: MovieListResponse?) {
                getView()?.getMCUMovieSuccess(data)
            }

            override fun onError(e: Throwable) {


            }

        })

    }


}