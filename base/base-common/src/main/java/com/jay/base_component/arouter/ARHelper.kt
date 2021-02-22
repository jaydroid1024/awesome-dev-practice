package com.jay.base_component.arouter

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.google.gson.Gson
import com.jay.base_component.constant.Constants
import com.jay.base_lib.utils.L
import java.util.*


/**
 * ARouter 帮助类
 * 路由路径
 * 跳转公共方法
 * @author wangxuejie
 * @date 2019-12-17 14:04
 * @version 1.0
 */
object ARHelper {

    /**
     * 获取通用服务
     */
    fun <T> getService(service: Class<out T>): T? {
        return ARouter.getInstance().navigation(service)
    }

    /**
     * 获取通用服务
     */
    @Suppress("UNCHECKED_CAST")
    fun <T> getService(path: String): T? {
        return ARouter.getInstance().build(path).navigation() as? T?
    }

    /**
     * 获取通用服务
     */
    @Suppress("UNCHECKED_CAST")
    fun <T> getService(path: String, activity: Activity, callBack: NavigationCallback): T? {
        return ARouter.getInstance().build(path).navigation(activity, callBack) as? T?
    }

    /**
     * ARouter通用跳转方法
     */
    fun routerToWithJson(mapParams: HashMap<String, Any>, path: String) {
        ARouter.getInstance()
            .build(path)
            .withString(Constants.IntentKey.JSON_PARAMS, mapParams.toJson())
            .navigation()
    }

    /**
     * ARouter通用跳转方法
     */
    fun routerToWithJson(path: String, mapParams: HashMap<String, Any>, flags: Int) {
        ARouter.getInstance()
            .build(path)
            .withString(Constants.IntentKey.JSON_PARAMS, mapParams.toJson())
            .withFlags(flags)
            .navigation()
    }

    /**
     * ARouter通用跳转方法
     */
    fun routerToWithJson(
        mapParams: HashMap<String, Any>,
        path: String,
        activity: Activity,
        requestCode: Int
    ) {
        ARouter.getInstance()
            .build(path)
            .withString(Constants.IntentKey.JSON_PARAMS, mapParams.toJson())
            .navigation(activity, requestCode)
    }

    /**
     * ARouter通用跳转方法
     */
    fun routerToWithJson(
        mapParams: HashMap<String, Any>,
        path: String,
        context: Activity,
        transition: IntArray
    ) {
        ARouter.getInstance()
            .build(path)
            .withString(Constants.IntentKey.JSON_PARAMS, mapParams.toJson())
            .withTransition(transition[0], transition[1])
            .navigation(context)
    }

    /**
     * ARouter通用跳转方法
     */
    fun routerToWithJson(
        mapParams: HashMap<String, Any>,
        path: String,
        context: Activity,
        requestCode: Int,
        transition: IntArray
    ) {
        ARouter.getInstance()
            .build(path)
            .withString(Constants.IntentKey.JSON_PARAMS, mapParams.toJson())
            .withTransition(transition[0], transition[1])
            .navigation(context, requestCode)
    }

    /**
     * 目标页面中获取参数Map
     */
    fun getParamsMapFromIntentByJson(intent: Intent?): Map<String, Any> {
        var paramsMap: Map<String, Any> = HashMap<String, Any>()
        if (intent != null && intent.hasExtra(Constants.IntentKey.JSON_PARAMS)) {
            val paramsJson = intent.getStringExtra(Constants.IntentKey.JSON_PARAMS) as String
            L.d("okhttp", "-------跳转后参数信息 --------\n $paramsJson")
            paramsMap = paramsJson.fromJsonToMap()
        }
        return paramsMap
    }

    /**
     * ARouter通用跳转方法
     */
    fun routerTo(path: String) {
        ARouter.getInstance().build(path).navigation()
    }

    /**
     * ARouter通用跳转方法
     */
    fun routerTo(path: String, context: Context) {
        ARouter.getInstance().build(path).navigation(context)
    }

    /**
     * ARouter通用跳转方法
     */
    fun routerTo(path: String, requestCode: Int, activity: Activity) {
        ARouter.getInstance().build(path).navigation(activity, requestCode)
    }

    /**
     * ARouter通用跳转方法
     */
    fun routerTo(path: String, context: Context, flags: Int) {
        ARouter.getInstance().build(path).withFlags(flags).navigation(context)
    }

    /**
     * ARouter通用跳转方法
     */
    fun routerTo(path: String, activity: Activity, callBack: NavigationCallback) {
        ARouter.getInstance().build(path).navigation(activity, callBack)
    }


    /**
     * ARouter通用跳转方法
     */
    fun routerTo(mapParams: HashMap<String, Any>, path: String) {
        ARouter.getInstance()
            .build(path)
            .withSerializable(Constants.IntentKey.MAP_PARAMS, mapParams)
            .navigation()
    }

    /**
     * ARouter通用跳转方法
     */
    fun routerTo(mapParams: HashMap<String, Any>, path: String, context: Context) {
        ARouter.getInstance()
            .build(path)
            .withSerializable(Constants.IntentKey.MAP_PARAMS, mapParams)
            .navigation(context)
    }

    /**
     * ARouter通用跳转方法
     */
    fun routerTo(listParams: ArrayList<Any>, path: String) {
        ARouter.getInstance()
            .build(path)
            .withSerializable(Constants.IntentKey.LIST_PARAMS, listParams)
            .navigation()
    }

    /**
     * ARouter通用跳转方法
     */
    fun routerTo(
        mapParams: HashMap<String, Any>,
        path: String,
        context: Activity,
        transition: IntArray
    ) {
        ARouter.getInstance()
            .build(path)
            .withSerializable(Constants.IntentKey.MAP_PARAMS, mapParams)
            .withTransition(transition[0], transition[1])
            .navigation(context)
    }

    /**
     * 获取Intent参数封装的 HashMap
     */
    fun getParamsMap(capacity: Int): HashMap<String, Any> {
        return HashMap(capacity)
    }

    private fun goToWeb(title: String, url: String) {
        val mapParams = HashMap<String, Any>()
        mapParams[Constants.MapKey.URL] = url
        mapParams[Constants.MapKey.TITLE] = url
        routerToWithJson(
            mapParams,
            ARPath.PathWeb.WEB_ACTIVITY_PATH
        )
    }


    /**
     * 目标页面中从HashMap中获取 String 类型的参数
     */
    fun getStrFromParamsMap(mapParams: Map<String, Any>?, key: String): String {
        L.e(TAG, "-------跳转后<K,V>信息 --------\n ${mapParams.toString()}")
        return mapParams?.let {
            it[key] as? String
        } ?: ""
    }

    /**
     * 目标页面中从HashMap中获取 Double 类型的参数
     */
    fun getDoubleFromParamsMap(mapParams: Map<String, Any>?, key: String): Double {
        L.e(TAG, "-------跳转后<K,V>信息 --------\n ${mapParams.toString()}")
        return mapParams?.let {
            it[key] as? Double
        } ?: 0.0
    }

    /**
     * Map转为Json字符串
     */
    private fun Map<String, Any>.toJson(): String {
        return Gson().toJson(this)
    }

    /**
     * Json转为Map
     */
    private fun String?.fromJsonToMap(): Map<String, Any> {
        return Gson().fromJson<Map<String, Any>>(this, Map::class.java)

    }

    object RequestCode {

        const val REQUEST_CODE_0X10001 = 0X1001


    }

    const val TAG = "okhttp"

}