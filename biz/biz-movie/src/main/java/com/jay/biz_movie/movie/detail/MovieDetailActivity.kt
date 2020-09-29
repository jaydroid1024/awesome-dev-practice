package com.jay.biz_movie.movie.detail

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARPath
import com.jay.base_component.base.BaseActivity
import com.jay.biz_movie.banner.ImageTitleNumAdapter
import com.jay.biz_movie.data.MovieDataHelper
import com.jay.biz_movie.databinding.BizMovieActivityMovieDetailBinding
import com.jay.biz_movie.entity.ActorsItem
import com.jay.biz_movie.movie.ZoomOutTranformer


@Route(path = ARPath.PathMovie.MOVIE_DETAIL_ACTIVITY_PATH)
class MovieDetailActivity : BaseActivity() {

    private lateinit var binding: BizMovieActivityMovieDetailBinding

    private var actorsList: MutableList<ActorsItem>? = null


    private var zhiPic: String =
        "https://tva1.sinaimg.cn/large/007S8ZIlly1gi59qjnd0ej30u00u0t9a.jpg"

    private var jingPic: String =
        "https://tva1.sinaimg.cn/large/007S8ZIlly1gi59rlqa4bj30u00u0t9e.jpg"

    private var yuanPic: String =
        "https://tva1.sinaimg.cn/large/007S8ZIlly1gi4m2wig3lj31410u0q5c.jpg"

    private val moviePic =
        "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p725871968.webp"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        binding = BizMovieActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    fun initViews() {
        actorsList = MovieDataHelper.getActorsList()
        val banner = binding.banner
        banner.adapter = ImageTitleNumAdapter(actorsList)
        banner.isAutoLoop(true)
        banner.setLoopTime(1000)
//        banner.removeTransformer()
        banner.addPageTransformer(ZoomOutTranformer())

    }

//    |AlphaPageTransformer
//    |DepthPageTransformer
//    |RotateDownPageTransformer|
//    |RotateUpPageTransformer|
//    |RotateYTransformer|
//    |ScaleInTransformer|
//    |ZoomOutPageTransformer|


    private fun getBannerList(): MutableList<String>? {

        val acts = mutableListOf<String>()
        acts.add(zhiPic)
        acts.add(jingPic)
        acts.add(yuanPic)
        acts.add(moviePic)
        return acts
    }


    override fun getLayoutResId(): Int {
        return 0
    }


}