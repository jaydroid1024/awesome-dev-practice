package com.jay.biz_movie.movie_douban.mvp

import com.jay.base_component.base.mvp.IPresenter
import com.jay.base_component.base.mvp.IView
import com.jay.biz_movie.entity.MovieListResponse

interface MovieContract {

    interface View : IView {
        fun getMCUMovieSuccess(response: MovieListResponse?)
    }

    interface Presenter : IPresenter<View> {
        fun getMCUMovie()
    }
}