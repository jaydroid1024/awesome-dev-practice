package com.jay.biz_jetpack.databinding.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * UserViewModel
 * @author wangxuejie
 * @version 1.0
 * @date 12/25/20
 */
class UserViewModelLiveData : ViewModel() {

    var firstName: String = ""

    var lastName: String = ""

    // Create a LiveData with a String
    val currentName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

}
