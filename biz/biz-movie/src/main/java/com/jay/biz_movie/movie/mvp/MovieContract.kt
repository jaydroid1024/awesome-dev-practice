package com.jay.biz_movie.movie.mvp

import com.jay.base_component.base.mvp.IPresenter
import com.jay.base_component.base.mvp.IView
import com.jay.biz_movie.entity.MovieDetailsResponse
import com.jay.biz_movie.entity.MovieListResponse

interface MovieContract {

    interface View : IView {
        fun getMCUMovieSuccess(response: MovieListResponse?)
        fun getMovieDetailsSuccess(data: MovieDetailsResponse?)
    }

    interface Presenter : IPresenter<View> {
        fun getMCUMovie()
        fun getMovieDetails(movieId: String)
    }
}