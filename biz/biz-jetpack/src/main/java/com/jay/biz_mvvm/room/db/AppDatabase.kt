package com.jay.biz_mvvm.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jay.biz_mvvm.room.dao.UserDao
import com.jay.biz_mvvm.room.data.User

/**
 * @author wangxuejie
 * @version 1.0
 * @date 1/21/21
 */

@Database(entities = [User::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}