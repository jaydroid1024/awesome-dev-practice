package com.jay.detail.web.mvp

import com.jay.base_component.base.mvp.IPresenter
import com.jay.base_component.base.mvp.IView
import com.jay.base_component.network.bean.wan.detail.AddFavoriteResponse

interface WebContract {

    interface View : IView {
        fun onAddFavorited(addFavoriteResponse: AddFavoriteResponse?)
    }

    interface Presenter : IPresenter<View> {
        fun addFavorite(id: Int, title: String, author: String, link: String)
    }
}