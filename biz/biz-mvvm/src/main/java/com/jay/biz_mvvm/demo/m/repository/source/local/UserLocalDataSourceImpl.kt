package com.jay.biz_mvvm.demo.m.repository.source.local

import com.jay.base_component.network.bean.wan.user.User
import com.jay.biz_mvvm.demo.m.repository.source.local.preference.UserPreferences

/**
 * 本地数据实现层，SharePreference、Room
 * 本地数据的获取方式可通过组合的方式更换
 */
class UserLocalDataSourceImpl : UserLocalDataSource {

    private var mUserPreferences: UserPreferences = UserPreferences()

    override fun saveUserName(userName: String) {
        mUserPreferences.saveUserName(userName)
    }

    override fun savePassword(password: String) {
//        SPUtils.getInstance().put("password", password);
    }

    override fun getUserName(): String {
        return mUserPreferences.getUserName()
    }

    override fun cacheUserInfo(data: User?) {
        if (data == null) {
            return
        }
        mUserPreferences.cacheUserInfo(data)


    }

}

