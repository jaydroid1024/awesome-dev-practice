package com.jay.biz_mvvm.demo.m.repository.source.local.preference

import com.jay.base_component.BuildConfig
import com.jay.base_component.network.bean.wan.user.User
import com.jay.lib_persistence.sp.BasePreferences
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable

class UserPreferences : BasePreferences() {

    override val preferencesGroup: String =
        UserPreferences::class.java.simpleName.toLowerCase() + BuildConfig.DEBUG

    fun saveToken(token: String) {
        saveData(UserPreferencesKey.TOKEN, token)
    }

    fun getUserName(): String {
        return getString(UserPreferencesKey.SP_NAME)
    }

    fun saveUserName(userName: String) {
        saveData(UserPreferencesKey.SP_NAME, userName)
    }

    fun cacheUserInfo(data: User) {
        saveData(UserPreferencesKey.SP_USER_INFO, data)
    }

    val token: String get() = getString(UserPreferencesKey.TOKEN)

    val cacheToken: Flowable<String>
        get() = getDataAsObservable(UserPreferencesKey.TOKEN, String::class.java)
            .toFlowable(BackpressureStrategy.LATEST)
}