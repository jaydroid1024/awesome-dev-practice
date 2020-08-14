package com.qlife.biz_setting.net

import com.qlife.base_component.bean.bean.Ok
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import java.util.*


/**
 * BossApiService
 * @author wangxuejie
 * @date 2019-12-25 17:51
 * @version 1.0
 */
interface BizTemplateApiService {

    /**
     * 用户注销登录
     *
     * @param request 请求数据
     *
     * @return Ok
     */
    @POST("/2.0")
    @Headers("X-CMD: qlife.auth.auth.logout")
    fun logout(@Body request: HashMap<String, Any?>): Observable<Ok>
}
