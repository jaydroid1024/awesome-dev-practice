package com.jay.biz_movie.movie_douban

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.alibaba.android.arouter.facade.annotation.Route
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.jay.base_component.arouter.ARPath
import com.jay.base_component.base.mvp.BaseMVPActivity
import com.jay.base_lib.utils.ToastUtils
import com.jay.biz_movie.R
import com.jay.biz_movie.databinding.ActivityMovieBinding
import com.jay.biz_movie.entity.MovieListResponse
import com.jay.biz_movie.marqueerecyclerview.LooperLayoutManager
import com.jay.biz_movie.movie_douban.mvp.MovieContract
import com.jay.biz_movie.movie_douban.mvp.MoviePresenter


@Route(path = ARPath.PathMovie.MOVIE_ACTIVITY_PATH)
class MovieActivity : BaseMVPActivity<MovieContract.View, MovieContract.Presenter>(),
    MovieContract.View, OnItemClickListener {


    private lateinit var binding: ActivityMovieBinding

    private val moviePic =
        "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p725871968.webp"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN;

        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.setAutoRun(true)
        binding.recyclerView.adapter = homeAdapter
        val layoutManager = LooperLayoutManager()
        layoutManager.setLooperEnable(true)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.start()

        presenter.getMCUMovie()

    }

    override fun getLayoutResId(): Int {
        return 0
    }

    /**
     * RV适配器
     */
    private val homeAdapter by lazy {
        MovieAdapter(homeItemData)
            .apply {
                animationEnable = true
                val top = layoutInflater.inflate(R.layout.top_view, binding.recyclerView, false)

                val topImg = top.findViewById<ImageView>(R.id.iv_top)

                val moviePic =
                    "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p725871968.webp"
//                Glide.with(this@MovieActivity).load(moviePic).into(topImg)
                addHeaderView(top)
                setOnItemClickListener(this@MovieActivity)
            }
    }


    private val homeItemData: ArrayList<MovieItemEntity>
        get() = arrayListOf(
            MovieItemEntity(headerTitle = "基本技能-计算机系统&网络知识体系"),
            MovieItemEntity(
                "WEB",
                ARPath.PathWeb.WEB_TEST_ACTIVITY_PATH,
                moviePic
            ),

            MovieItemEntity(headerTitle = "内功心法-算法与思想知识体系"),
            MovieItemEntity(
                "算法",
                ARPath.PathDev.DEV_ACTIVITY_PATH,
                moviePic
            ),

            MovieItemEntity(headerTitle = "必修之技-Java知识体系"),
            MovieItemEntity(
                "核心语法",
                ARPath.PathDev.DEV_ACTIVITY_PATH,
                moviePic
            ),

            MovieItemEntity(headerTitle = "未来可期-Kotlin知识体系"),
            MovieItemEntity(
                "核心语法",
                ARPath.PathDev.DEV_ACTIVITY_PATH,
                moviePic
            ),

            MovieItemEntity(headerTitle = "乘风破浪-Android知识体系"),
            MovieItemEntity(
                "核心语法",
                ARPath.PathDev.DEV_ACTIVITY_PATH,
                moviePic
            ),

            MovieItemEntity(headerTitle = "勇攀高峰-封神大招知识体系"),
            MovieItemEntity(
                "跨平台",
                ARPath.PathDev.DEV_ACTIVITY_PATH,
                moviePic
            )
        )


    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {


    }

    override fun createPresenter(): MovieContract.Presenter {
        return MoviePresenter()
    }


    override fun getMCUMovieSuccess(response: MovieListResponse?) {
        ToastUtils.showLong("" + response?.subjects?.size)

    }

}