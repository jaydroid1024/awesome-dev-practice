package com.jay.biz_mvvm.room.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author wangxuejie
 * @version 1.0
 * @date 1/21/21
 */
@Entity
data class User(

    @PrimaryKey(autoGenerate = true)
    val uid: Int,

    @ColumnInfo(name = "first_name")
    val firstName: String?,

    @ColumnInfo(name = "last_name")
    val lastName: String?

)