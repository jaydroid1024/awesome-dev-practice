package com.jay.biz_mvvm.mvvm

import retrofit2.Response

/**
 *
 * @Description  网络请求处理
 * @date 12/16/20 9:50 AM
 * @author BryceCui
 * @Version 1.0
 */
open class BaseRepository {
    /**
     * 实际的网络请求处理逻辑方法
     *
     * @param T  解析后数据
     * @param call  retrofit service
     * @return
     */
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            //请求成功返回成功状态
            if (response.isSuccessful) {
                //如果body 为空返回error
                val body = response.body() ?: return error(
                    null,
                    response
                )
                return Result.success(body)
            }
            //不成功的error
            return error(null, response)
        } catch (exception: Exception) {
            //抛异常的error
            return error(exception, null)
        }
    }
}

