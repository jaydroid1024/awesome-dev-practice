package com.jay.biz_mvvm.mvvm

/**
 *
 * @Description 数据结果返回处理
 * @date 12/14/20 5:16 PM
 * @author BryceCui
 * @Version 1.0
 */
data class Result<out T>(
    val status: Status,
    val resultData: T?,
    val errorCode: Int?,
    val message: String?
) {

    companion object {
        fun <T> success(data: T): Result<T> {
            return Result(Status.SUCCESS, data, null, null)
        }

        fun <T> error(errorCode: Int?, message: String?, data: T? = null): Result<T> {
            return Result(Status.ERROR, data, errorCode, message)
        }

    }

    enum class Status {
        SUCCESS,
        ERROR
//        , LOADING
    }
}