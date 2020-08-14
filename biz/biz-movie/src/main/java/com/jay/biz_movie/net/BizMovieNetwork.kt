package  com.jay.biz_movie.net

import android.content.Context
import com.jay.base_component.arouter.ARHelper
import com.jay.base_component.arouter.ARPath
import com.jay.base_component.arouter.service.user.UserService
import com.jay.base_component.config.AppConfigHelper
import com.jay.base_component.network.auth.AuthAbstractNetwork
import com.jay.base_component.network.default_net.RxUtil
import com.jay.biz_movie.entity.MovieListResponse
import io.reactivex.Observable

/**
 * DefaultNetwork：
 * 真正做业务网络处理的子类，一个baseUrl对应一类网络请求，项目中存在多baseUrl需要新建该类实现
 * 也可以通过重写okHttpClientHandler返回父类一个RetrofitUrlManager包装过的 OkHttpClient 实现多baseurl切换
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2019-12-24 17:18
 */

class BizMovieNetwork(context: Context) : AuthAbstractNetwork<BizMovieApiService>(context) {
    /**
     * 用户服务管理类
     */
    var userService: UserService? = null

    init {
        //获取Token服务管理类实例
        userService =
            ARHelper.getService<UserService>(ARPath.PathUser.USER_SERVICE_PATH)
    }

    override val baseUrl: String
        get() = AppConfigHelper.getAppConfig()?.BIZ_MOVIE_DOUBAN_BASE_URL ?: ""

    override val apiServiceClass: Class<BizMovieApiService> get() = BizMovieApiService::class.java

    fun getMCUMovie(apikey: String?, start: Int, count: Int): Observable<MovieListResponse?> {
        return getApiService()
            .getMCUMovie(apikey,start,count)
            .compose(RxUtil.applyObservableTransformer())
    }

    companion object {
        private val TAG = BizMovieNetwork::class.java.simpleName
    }
}
