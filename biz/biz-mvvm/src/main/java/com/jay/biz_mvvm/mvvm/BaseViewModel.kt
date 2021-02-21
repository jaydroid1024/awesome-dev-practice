package com.jay.biz_mvvm.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *
 * @Description  切换线程
 * @date 12/16/20 10:45 AM
 * @author BryceCui
 * @Version 1.0
 */
open class BaseViewModel : ViewModel() {

    var loadingLiveData = MutableLiveData<Boolean>()

    var booleanValue = MutableLiveData<Boolean>()

    override fun onCleared() {
        super.onCleared()
    }

}