package com.jay.base_component.config.service

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.gson.reflect.TypeToken
import com.jay.base_component.arouter.ARPath
import com.jay.base_component.arouter.service.config.HideService
import com.jay.base_component.config.AppConfig
import com.jay.base_component.constant.Constants
import com.jay.base_lib.utils.GsonUtils
import com.jay.base_lib.utils.SPUtils

/**
 * app config 对外暴露的服务
 * @author wangxuejie
 * @date 2019-12-17 16:01
 * @version 1.0
 */
@Route(path = ARPath.PathHide.HIDE_SERVICE_PATH, name = "hide 信息服务")
class HideServiceImpl : HideService {

    var context: Context? = null

    override fun init(context: Context?) {
        this.context = context
    }

    override fun getAppConfig(): AppConfig? {
        return if (SPUtils.contains(context, Constants.SP.CONFIG_INFO)) {
            GsonUtils.fromJson(
                SPUtils.get(context, Constants.SP.CONFIG_INFO, "") as String,
                AppConfig::class.java
            )
        } else {
            return null
        }
    }

    override fun setAppConfig(appConfig: AppConfig?) {
        SPUtils.put(context, Constants.SP.CONFIG_INFO, GsonUtils.toJson(appConfig))
    }

    override fun getAppConfigLocalList(): MutableList<AppConfig>? {
        return if (SPUtils.contains(context, Constants.SP.CONFIG_LOCAL_LIST)) {
            val listType = object : TypeToken<List<AppConfig>>() {}.type
            val text: String = SPUtils.get(context, Constants.SP.CONFIG_LOCAL_LIST, "") as String
            GsonUtils.fromJson<AppConfig>(text, listType)
        } else {
            null
        }
    }

    override fun setAppConfigLocalList(appConfigList: MutableList<AppConfig>?) {
        SPUtils.put(context, Constants.SP.CONFIG_LOCAL_LIST, GsonUtils.toJson(appConfigList))
    }

}