package com.jay.biz_mvvm.databinding.data

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

/**
 * UserViewModel
 * @author wangxuejie
 * @version 1.0
 * @date 12/25/20
 */
class UserViewModel(state: SavedStateHandle) : ViewModel() {

    var firstName: String = ""

    var lastName: String = ""
}
