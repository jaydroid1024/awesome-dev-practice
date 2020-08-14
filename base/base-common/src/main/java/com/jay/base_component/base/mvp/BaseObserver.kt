package com.jay.base_component.base.mvp

import com.jay.base_component.network.error.ExceptionHandler
import io.reactivex.observers.DisposableObserver
import io.reactivex.plugins.RxJavaPlugins

abstract class BaseObserver<T> : DisposableObserver<T> {


    private var baseView: IView? = null

    constructor() : super()

    constructor(view: IView?) : super() {
        baseView = view
    }

    override fun onStart() {
        super.onStart()
        baseView?.showLoading()
    }

    override fun onNext(response: T) {
        baseView?.dismissLoading()
        onSuccess(response)
    }

    override fun onError(e: Throwable) {
        ExceptionHandler.handleException(e)
    }

    private fun setRxJavaErrorHandler() {
        RxJavaPlugins.setErrorHandler(Throwable::printStackTrace)
    }

    abstract fun onSuccess(data: T)

    override fun onComplete() {
        baseView?.dismissLoading()
    }

    companion object {
        const val TAG = "BaseObserver"
    }

}