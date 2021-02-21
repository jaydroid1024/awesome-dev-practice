package com.jay.biz_mvvm.mvvm.base

/**
 *
 * @Description 数据结果返回处理
 * @date 12/14/20 5:16 PM
 * @author BryceCui
 * @Version 1.0
 */
data class BaseResponse<out T>(
    val status: Status,
    val resultData: T?,
    val errorCode: Int?,
    val message: String?
) {

    companion object {

        fun <T> success(data: T): BaseResponse<T> {
            return BaseResponse(Status.SUCCESS, data, null, null)
        }

        fun <T> error(errorCode: Int?, message: String?, data: T? = null): BaseResponse<T> {
            return BaseResponse(Status.ERROR, data, errorCode, message)
        }

    }

    enum class Status {
        SUCCESS,
        ERROR
    }
}