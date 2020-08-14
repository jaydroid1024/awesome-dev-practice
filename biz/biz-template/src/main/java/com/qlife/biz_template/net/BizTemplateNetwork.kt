package  com.qlife.biz_setting.net

import android.content.Context
import com.qlife.base_component.BuildConfig
import com.qlife.base_component.arouter.ARHelper
import com.qlife.base_component.arouter.path.ARPath
import com.qlife.base_component.arouter.service.token.TokenService
import com.qlife.base_component.arouter.service.user.UserService
import com.qlife.base_component.bean.bean.Ok
import com.qlife.base_component.helper.AppConfigHelper
import com.qlife.base_component.net.auth.AuthAbstractNetwork
import io.reactivex.Observable
import java.util.*

/**
 * DefaultNetwork：
 * 真正做业务网络处理的子类，一个baseUrl对应一类网络请求，项目中存在多baseUrl需要新建该类实现
 * 也可以通过重写okHttpClientHandler返回父类一个RetrofitUrlManager包装过的 OkHttpClient 实现多baseurl切换
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2019-12-24 17:18
 */

class BizTemplateNetwork(context: Context) : AuthAbstractNetwork<BizTemplateApiService>(context) {

    /**
     * 用户服务管理类
     */
    var userService: UserService? = null
    var tokenService: TokenService? = null

    init {
        //获取Token服务管理类实例
        userService =
            ARHelper.getService<UserService>(ARPath.PathUser.USER_SERVICE_PATH)
        tokenService =
            ARHelper.getService<TokenService>(ARPath.PathUser.TOKEN_SERVICE_PATH)
    }

    override val baseUrl: String
        get() = AppConfigHelper.getAppConfig()?.BASE_URL ?: BuildConfig.BASE_URL

    override val apiServiceClass: Class<BizTemplateApiService> get() = BizTemplateApiService::class.java

    fun logout(): Observable<Ok> {
        val map = HashMap<String, Any?>(2)
        map["access_token"] = tokenService?.getBossToken()?.accessToken ?: ""
        return getApiService().logout(map)
    }

    companion object {
        private val TAG = BizTemplateNetwork::class.java.simpleName
    }
}
