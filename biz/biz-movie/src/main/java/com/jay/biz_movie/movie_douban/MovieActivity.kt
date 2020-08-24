package com.jay.biz_movie.movie_douban

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.google.gson.reflect.TypeToken
import com.jay.base_component.arouter.ARHelper
import com.jay.base_component.arouter.ARPath
import com.jay.base_component.base.mvp.BaseMVPActivity
import com.jay.base_component.constant.Constants
import com.jay.base_lib.utils.*
import com.jay.biz_movie.R
import com.jay.biz_movie.data.MovieDataHelper
import com.jay.biz_movie.databinding.BizMovieActivityMovieBinding
import com.jay.biz_movie.entity.MovieDetailsResponse
import com.jay.biz_movie.entity.MovieItem
import com.jay.biz_movie.entity.MovieListResponse
import com.jay.biz_movie.marqueerecyclerview.LooperLayoutManager
import com.jay.biz_movie.movie_douban.mvp.MovieContract
import com.jay.biz_movie.movie_douban.mvp.MoviePresenter
import com.moxun.tagcloudlib.view.TagCloudView


@Route(path = ARPath.PathMovie.MOVIE_ACTIVITY_PATH)
class MovieActivity : BaseMVPActivity<MovieContract.View, MovieContract.Presenter>(),
    MovieContract.View, OnItemClickListener {


    private lateinit var binding: BizMovieActivityMovieBinding

    private var marvelMCUMovieList: MutableList<MovieItem>? = null

    private var mcuIdList: ArrayList<String>? = null

    private var mcuIndex: Int = 0

    private var movieType: String = ""

    private val moviePic =
        "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p725871968.webp"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        binding = BizMovieActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initIntent()

//        initMovieList()

//        testData()

//        initMCU()

        goToSpeechPage()
    }

    private fun initIntent() {
        val mMapParams =
            intent.getSerializableExtra(Constants.IntentKey.MAP_PARAMS) as? java.util.HashMap<*, *>
        movieType = mMapParams.let { (it?.get(Constants.MapKey.TYPE) as? String).toString() }
        L.d("Jay", "movieType:$movieType")

    }


    private fun goToSpeechPage() {
        val map = HashMap<String, Any>()
        when (movieType) {
            "1" -> {
                map[Constants.MapKey.TITLE] = "漫威电影宇宙之无限传奇系列电影\n第一阶段电影"
            }
            "2" -> {
                map[Constants.MapKey.TITLE] = "漫威电影宇宙之无限传奇系列电影\n第二阶段电影"
            }
            "3" -> {
                map[Constants.MapKey.TITLE] = "漫威电影宇宙之无限传奇系列电影\n第三阶段电影"
            }
            else -> {
                map[Constants.MapKey.TITLE] = "漫威电影宇宙之无限传奇系列电影"

            }
        }

//        map[Constants.MapKey.TITLE] = "漫威电影宇宙之无限传奇系列电影\n第一阶段电影"
        ARHelper.routerToWithJson(map, ARPath.PathSpeech.SPEECH_ACTIVITY_PATH, this, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        initMovieList()
    }


    private fun initMovieList() {
        val layoutManager = LooperLayoutManager()
        val layoutManagerNormal = LinearLayoutManager(this)
        layoutManager.setLooperEnable(false)
        binding.recyclerView.layoutManager = layoutManagerNormal
        binding.recyclerView.adapter = homeAdapter
        binding.recyclerView.setAutoRun(true)
        binding.recyclerView.start()
    }

    /**
     * RV适配器
     */
    private val homeAdapter by lazy {
        val a = MovieDataHelper.getMarvelMCUMovieTimeOrderedList(movieType)
        for (movieItem in a) {
            if (!movieItem.isHeader) {
                val output =
                    movieItem.year + "-" + movieItem.title + "(" + movieItem.originalTitle + ") \n"
//                + "![](" + getPhoto(movieItem) + ") \n"
                Log.d("Jay", output)
            }

        }
        MovieAdapter(a)
            .apply {
                animationEnable = true
                val top =
                    layoutInflater.inflate(R.layout.biz_movie_top_view, binding.recyclerView, false)

                val top1Img = top.findViewById<ImageView>(R.id.iv_top1)
                val top2Img = top.findViewById<ImageView>(R.id.iv_top2)
                top2Img.visibility = View.GONE
                val top3Img = top.findViewById<ImageView>(R.id.iv_top3)
                var tagCloudView = top.findViewById<TagCloudView>(R.id.tag_cloud)
                val top1Pic =
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1597591248997&di=bbdd5132ec439bac3b29a41d16a6a7cb&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171105%2F84372d114505486487a790d5dec8adaf.jpeg"
                val top2Pic =
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1597591290385&di=82d75a18d07dd652b5030ac8c10f90f4&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20190303%2Fdd73dfbf267f474198c7bed4120b0f7e.jpeg"
                val top3Pic =
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1597590820219&di=fd4817599736ea22c967901b93a72fd4&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn14%2F173%2Fw2000h1373%2F20180817%2Ffdd8-hhvciiw6328880.jpg"
                Glide.with(this@MovieActivity)
                    .load(top1Pic)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(top1Img!!)
                Glide.with(this@MovieActivity)
                    .load(top2Pic)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(top2Img!!)
                Glide.with(this@MovieActivity)
                    .load(top3Pic)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(top3Img!!)
                val arrayList = ArrayList<String>()

                a.forEach {
                    it.casts?.forEach {
                        arrayList.add(it?.avatars?.medium ?: "")
                    }
                }

                val textTagsAdapter = TextTagsAdapter(arrayList)
                tagCloudView.setAdapter(textTagsAdapter)
                addHeaderView(top)
                setOnItemClickListener(this@MovieActivity)


            }
    }

    private fun getPhoto(movieItem: MovieItem): String {
        var pic = ""
        movieItem.photos?.forEach {
            if(it.image?.endsWith(".jpg")!!){

                return it.image!!
            }
        }
        return pic
    }

    private fun initMCU() {


    }


    private fun testData() {
        //        presenter.getMCUMovie()
        //        presenter.getMovieDetails()

        //        getIdList()
        //        getMCUList(mcuIndex)

//        getCacheMovie()
    }

    /**
     * 初始化省市配置
     */
    private fun getCacheMovie() {
        val string = FileUtils.readFileContent(this, "mcu_movie_23.json")
        val listType = object : TypeToken<List<MovieItem>>() {}.type
        marvelMCUMovieList = GsonUtils.fromJson<MovieItem>(string, listType)

        Log.d("okhttp", marvelMCUMovieList.toString())

        marvelMCUMovieList!!.sortBy {
            it.year
        }
        for ((ind, i) in marvelMCUMovieList!!.withIndex()) {
            i.index = ind.toString()
            Log.d("okhttp", "index: " + i)
        }
        val json = GsonUtils.toJson(marvelMCUMovieList)
        Log.d("okhttp", "总共：" + marvelMCUMovieList?.size)
        SPUtils.put(this, Constants.SP.MCU_MOVIE, json)
        val cacheFile = SPUtils.get(this, Constants.SP.MCU_MOVIE, "") as String
        Log.d("okhttp", cacheFile)

    }

    private fun getIdList() {
        if (mcuIdList == null) {
            mcuIdList = ArrayList()
        }
        val mcuMap = MovieDataHelper.getMarvelMCUMovieNameIdMap()
        mcuMap.forEach {
            mcuIdList!!.add(it.value)
        }

    }

    private fun getMCUList(mcuIndex: Int) {
        presenter.getMovieDetails(mcuIdList!![mcuIndex])

    }

    override fun getMovieDetailsSuccess(data: MovieDetailsResponse?) {

        ToastUtils.showLong("第 $mcuIndex 个电影加载完了，名字是：${data?.title}")
        Log.d("okhttp", "第 $mcuIndex 个电影加载完了，名字是：${data?.title}")
        Log.d("okhttp", data.toString())
        setMovieItemList(data)

        if (mcuIndex < mcuIdList!!.size - 1) {
            mcuIndex++
            getMCUList(mcuIndex)
        } else {
            ToastUtils.showLong("全部加载完了")
            marvelMCUMovieList!!.sortBy {
                it.year
            }
            val json = GsonUtils.toJson(marvelMCUMovieList)
            Log.d("okhttp", "总共：" + marvelMCUMovieList?.size)
            SPUtils.put(this, Constants.SP.MCU_MOVIE, json)
            val cacheFile = SPUtils.get(this, Constants.SP.MCU_MOVIE, "") as String
            Log.d("okhttp", cacheFile)

        }


    }

    private fun setMovieItemList(data: MovieDetailsResponse?) {
        if (marvelMCUMovieList == null) {
            marvelMCUMovieList = ArrayList()
        }
        val movieItem = MovieItem()
        movieItem.id = data?.id ?: ""
        movieItem.title = data?.title ?: ""
        movieItem.originalTitle = data?.originalTitle ?: ""
        movieItem.year = data?.year ?: ""
        movieItem.photos = data?.photos
        movieItem.casts = data?.casts
        marvelMCUMovieList!!.add(movieItem)


    }

    override fun getLayoutResId(): Int {
        return 0
    }




    private val homeItemData: ArrayList<MovieItemEntity>
        get() = arrayListOf(
            MovieItemEntity(headerTitle = "第三阶段"),
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