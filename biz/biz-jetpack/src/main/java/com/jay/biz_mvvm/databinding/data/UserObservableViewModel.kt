package com.jay.biz_mvvm.databinding.data

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.ViewModel

/**
 * UserViewModel
 * @author wangxuejie
 * @version 1.0
 * @date 12/25/20
 */
class UserObservableViewModel : ViewModel(), Observable {

    var firstName: String = ""

    var lastName: String = ""

    private val callbacks: PropertyChangeRegistry = PropertyChangeRegistry()


    /**
     * Adds a callback to listen for changes to the Observable.
     * @param callback The callback to start listening.
     */
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    /**
     * Removes a callback from those listening for changes.
     * @param callback The callback that should stop listening.
     */
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.remove(callback)
    }

    /**
     * 通知观察者该实例的所有属性已更改。
     */
    fun notifyChange() {
        callbacks.notifyCallbacks(this, 0, null)
    }

    /**
     * 通知观察者特定属性已更改。 更改的属性的吸气剂应使用@Bindable批注标记，以在BR类中生成一个用作fieldId参数的字段。
     * @param fieldId为可绑定字段生成的BR ID。
     */
    fun notifyPropertyChanged(fieldId: Int) {
        callbacks.notifyCallbacks(this, fieldId, null)
    }
}
