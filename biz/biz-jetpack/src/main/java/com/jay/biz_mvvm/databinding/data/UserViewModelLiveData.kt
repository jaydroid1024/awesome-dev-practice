package com.jay.biz_mvvm.databinding.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * UserViewModel
 * @author wangxuejie
 * @version 1.0
 * @date 12/25/20
 */
class UserViewModelLiveData(application: Application) : AndroidViewModel(application) {

    var firstName: String = ""

    var lastName: String = ""

    // Create a LiveData with a String
    val currentName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

}
