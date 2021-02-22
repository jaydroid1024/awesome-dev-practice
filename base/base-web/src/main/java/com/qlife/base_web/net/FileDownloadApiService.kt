package com.qlife.base_web.net


import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

/**
 * pdf文件下载
 *
 * @author gaoxiaoiduo
 * @version 1.0
 * @date 19/1/21上午10:52
 */
interface FileDownloadApiService {
    /**
     * 下载文件
     *
     * @param url 下载地址
     *
     * @return ResponseBody
     */
    @Streaming
    @GET
    fun downloadFileService(@Url url: String): Call<ResponseBody>
}
