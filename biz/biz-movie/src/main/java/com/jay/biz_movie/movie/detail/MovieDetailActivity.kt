package com.jay.biz_movie.movie.detail

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARPath
import com.jay.base_component.base.BaseActivity
import com.jay.base_component.constant.Constants
import com.jay.base_lib.utils.ToastUtils
import com.jay.biz_movie.banner.ImageTitleNumAdapter
import com.jay.biz_movie.data.MovieDataHelper
import com.jay.biz_movie.databinding.BizMovieActivityMovieDetailBinding
import com.jay.biz_movie.entity.ActorsItem
import com.jay.biz_movie.movie.ZoomOutTranformer
import java.util.*


@Route(path = ARPath.PathMovie.MOVIE_DETAIL_ACTIVITY_PATH)
class MovieDetailActivity : BaseActivity() {

    private lateinit var binding: BizMovieActivityMovieDetailBinding

    private var actorsList: MutableList<ActorsItem>? = null

    private var title: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        binding = BizMovieActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initIntent()
        initViews()
    }

    /**
     *  初始化传入参数
     */
    private fun initIntent() {
        val mMapParams =
            intent.getSerializableExtra(Constants.IntentKey.MAP_PARAMS) as? HashMap<String, Any>?
        title = mMapParams?.let { it[Constants.MapKey.TITLE] as? String } ?: ""
    }

    private fun initViews() {
        ToastUtils.showLong("title: $title")
        actorsList = MovieDataHelper.getActorsList(title)

        val banner = binding.banner
        banner.adapter = ImageTitleNumAdapter(actorsList)
        banner.isAutoLoop(false)
        banner.setLoopTime(800)
        banner.addPageTransformer(ZoomOutTranformer())
    }


    override fun getLayoutResId(): Int {
        return 0
    }


}