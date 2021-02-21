package com.jay.biz_mvvm.mvvm

import android.util.Log
import com.google.gson.JsonSyntaxException
import com.jay.base_component.bean.net.ErrorCode
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.net.UnknownServiceException

/**
 *  统一处理网络请求错误信息及错误日志
 *
 * @param T  数据泛型
 * @param exception  异常
 * @param response  Response
 * @return
 */
fun <T> error(exception: Exception?, response: Response<T>?): Result<T> {
    val errorCode = response?.code()
    val message = response?.message()
    Log.e("Result：", "retrofit request error message : $errorCode")
    Log.e("Result：", "retrofit request error code: $message")
    //请求失败异常处理
    if (exception != null) {
        when (exception) {
            is HttpException -> {
                return Result.error(errorCode = ErrorCode.DATA_PARSE_ERROR, message = "数据解析失败")
            }
            is NumberFormatException,
            is JsonSyntaxException -> {
                return Result.error(errorCode = ErrorCode.DATA_PARSE_ERROR, message = "数据解析失败")
            }
            is ConnectException,
            is UnknownHostException,
            is UnknownServiceException,
            is SocketTimeoutException -> {
                return Result.error(
                    errorCode = ErrorCode.NETWORK_ERROR,
                    message = "网络异常，请检查您当前的网络设置"
                )
            }
            is NullPointerException -> {
                return Result.error(errorCode = ErrorCode.NULl_POINT_ERROR, message = "数据异常")
            }
            else -> {
                return Result.error(
                    errorCode = ErrorCode.UNKNOWN_ERROR, message = exception.localizedMessage
                        ?: "Unknown Error"
                )
            }

        }
    }
    //请求失败错误码处理
    when (errorCode) {
        ErrorCode.SERVICE_ERROR -> {
            return Result.error(errorCode = ErrorCode.SERVICE_ERROR, message = "服务器端错误")
        }
        ErrorCode.BAD_GATEWAY -> {
            return Result.error(errorCode = ErrorCode.BAD_GATEWAY, message = "系统超时，请稍后尝试！")
        }
        ErrorCode.APP_ERROR -> {
            var errorStr: String? = null
            try {
                errorStr = response.errorBody()!!.source()
                    .readUtf8()
            } catch (e1: IOException) {
                e1.printStackTrace()
            }

            return Result.error(errorCode = errorCode, message = errorStr)
        }
    }
    return Result.error(errorCode = errorCode, message = message)
}
