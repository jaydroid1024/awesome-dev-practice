package com.jay.base_component.base.mvp

import com.jay.base_component.network.bean.wan.BaseResponse
import com.jay.base_component.network.error.ExceptionHandler
import io.reactivex.observers.DisposableObserver
import io.reactivex.plugins.RxJavaPlugins

abstract class BaseObserver<T> : DisposableObserver<BaseResponse<T>> {


    constructor() : super()

    override fun onStart() {
        super.onStart()
    }

    override fun onNext(response: BaseResponse<T>) {
        onSuccess(response.data)
    }

    override fun onError(e: Throwable) {
        ExceptionHandler.handleException(e)
    }

    private fun setRxJavaErrorHandler() {
        RxJavaPlugins.setErrorHandler(Throwable::printStackTrace)
    }

    abstract fun onSuccess(data: T?)

    override fun onComplete() {

    }

    companion object {
        const val TAG = "BaseObserver"
    }

}