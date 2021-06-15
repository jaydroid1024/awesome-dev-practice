package com.jay.lib_kotlin

import java.net.URL


/**
 *
 * @author :BryceCui
 * @date 2019-12-05 15:04
 * @version 1.0
 *
 */
object UrlParse {

    const val TAG = "UrlParse"


    /**
     * 获得Url参数字符串
     * @param url url地址
     * @return 参数字符串
     */
    fun getUrlParamStr(url: String): String {
        val mUrl = stringToURL(url) ?: return ""
        return mUrl.query
    }

    /**
     * 获得url的协议+域+路径（即url路径问号左侧的内容）
     * @param url url地址
     * @return url的协议+域+路径
     */
    fun getUrlHostAndPath(url: String): String {
        return if (url.contains("?")) {
            url.substring(0, url.indexOf("?"))
        } else url
    }


    /**
     * 字符串转为URL对象
     * @param url url字符串
     * @return url对象
     */
    private fun stringToURL(url: String?): URL? {
        if (url == null || url.isEmpty() || !url.contains("://")) {
            return null
        }
        return try {
            val sbUrl = StringBuilder("http")
            sbUrl.append(url.substring(url.indexOf("://")))
            URL(sbUrl.toString())
        } catch (ex: Exception) {
            null
        }
    }


    class UrlEntity {
        /**
         * 基础url
         */
        var baseUrl: String? = null

        /**
         * url参数
         */
        var params: HashMap<String, String>? = null
    }


    /**
     * 解析url
     *
     * @param url
     * @return
     */
    fun getUrlParamsNew(url: String?): HashMap<String, String> {
        val queryPairs = LinkedHashMap<String, String>()
        var url = url
        val entity = UrlEntity()
        if (url == null) {
            return queryPairs
        }
        url = url.trim { it <= ' ' }
        if (url == "") {
            return queryPairs
        }
        val urlParts = url.split("\\?".toRegex()).toTypedArray()
        entity.baseUrl = urlParts[0]
        //没有参数
        if (urlParts.size == 1) {
            return queryPairs
        }
        //有参数
        val params = urlParts[1].split("&".toRegex()).toTypedArray()
        entity.params = HashMap()
        for (param in params) {
            val keyValue = param.split("=".toRegex()).toTypedArray()
            queryPairs[keyValue[0]] = keyValue[1]
        }
        return queryPairs
    }
}
 
