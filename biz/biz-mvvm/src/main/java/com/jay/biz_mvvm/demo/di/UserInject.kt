package com.jay.biz_mvvm.demo.di

import com.jay.biz_mvvm.demo.m.repository.UserRepository
import com.jay.biz_mvvm.demo.m.repository.UserRepositoryImpl
import com.jay.biz_mvvm.demo.m.repository.source.local.UserLocalDataSourceImpl
import com.jay.biz_mvvm.demo.m.repository.source.remote.UserNetWorkDataSourceImpl

/**
 * 注入全局的数据仓库，可以考虑使用Dagger2,Hilt 等注入框架。
 */
object UserInject {

    fun provideUserRepository(): UserRepository {
        return UserRepositoryImpl(UserNetWorkDataSourceImpl(), UserLocalDataSourceImpl())
    }
}