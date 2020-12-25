package com.jay.biz_jetpack.databinding.data

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.jay.biz_jetpack.BR

/**
 * ObservableObject
 * @author wangxuejie
 * @version 1.0
 * @date 12/25/20
 */
class ObservableUser : BaseObservable() {

    @get:Bindable
    var firstName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.firstName)
        }

    @get:Bindable
    var lastName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.lastName)
        }
}