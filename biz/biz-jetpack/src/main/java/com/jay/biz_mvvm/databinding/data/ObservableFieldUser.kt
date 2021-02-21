package com.jay.biz_mvvm.databinding.data

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableArrayMap
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt

/**
 * ObservableFiel
 * @author wangxuejie
 * @version 1.0
 * @date 12/25/20
 */
class ObservableFieldUser {

    val firstName = ObservableField<String>()

    val lastName = ObservableField<String>()

    val age = ObservableInt()

    val address = ObservableArrayMap<String, Any>().apply {
        put("city", "北京")
        put("area", "朝阳")
    }
    val hobby = ObservableArrayList<Any>().apply {
        add("撸码")
        add("电影")
        add(1024)
    }

}