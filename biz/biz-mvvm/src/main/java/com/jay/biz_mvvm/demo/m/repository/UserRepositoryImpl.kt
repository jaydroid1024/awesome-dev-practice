package com.jay.biz_mvvm.demo.m.repository

import com.jay.base_component.network.bean.wan.BaseResponse
import com.jay.base_component.network.bean.wan.user.User
import com.jay.biz_mvvm.demo.m.repository.source.local.UserLocalDataSource
import com.jay.biz_mvvm.demo.m.repository.source.remote.UserNetWorkDataSource
import io.reactivex.Observable

/**
 * MVVM 的 数据实现层（Model），统一组件的数据仓库
 * 包含网络数据和本地数据（每个组件都应该定义自己的 XxxRepository ）
 * 数据仓库通过组合的形式引入 HttpDataSource 和 LocalDataSource
 * 根据需求获取网络数据还是本地数据
 */
class UserRepositoryImpl(
    private val mNetWorkDataSource: UserNetWorkDataSource,
    private val mLocalDataSource: UserLocalDataSource
) : UserRepository {

    override fun saveUserName(userName: String) {
        mLocalDataSource.saveUserName(userName)
    }

    override fun getUserName(): String {
        return mLocalDataSource.getUserName()
    }



    override fun login(userName: String, password: String): Observable<BaseResponse<User>> {
        return mNetWorkDataSource.login(userName, password)
            .doOnNext {
                mLocalDataSource.cacheUserInfo(it.data)
            }
    }

}