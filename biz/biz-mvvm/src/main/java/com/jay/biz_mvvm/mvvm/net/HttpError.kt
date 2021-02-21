package com.jay.biz_mvvm.mvvm.net

import com.google.gson.annotations.SerializedName

/**
 * 服务器端返回错误信息
 *
 * @author gaoxiaoiduo
 * @date 2019-12-09 17:34
 * @version 1.0
 */
open class HttpError {

    @SerializedName("err_code")
    var err_code: Int = 0

    @SerializedName("err_name")
    var err_name: String? = null

    @SerializedName("message")
    var message: String? = null

    @SerializedName("zh_message")
    var zh_message: String? = null
}
