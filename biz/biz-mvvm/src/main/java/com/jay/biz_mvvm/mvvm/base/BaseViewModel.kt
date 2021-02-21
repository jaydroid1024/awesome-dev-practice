package com.jay.biz_mvvm.mvvm.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.jay.base_component.base.mvp.BaseObserver
import com.jay.base_component.network.bean.wan.BaseResponse
import com.jay.base_component.network.default_net.RxUtil
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel(application: Application) : AndroidViewModel(application), ILifecycle {

    //管理RxJava，主要针对RxJava异步操作造成的内存泄漏
    private var mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    protected fun addSubscribe(disposable: Disposable) {
        mCompositeDisposable.add(disposable)
    }

    fun <T> addSubscribe(observable: Observable<BaseResponse<T>>, baseObserver: BaseObserver<T>) {
        val observer = observable
            .compose(RxUtil.applyObservableTransformer())
            .subscribeWith(baseObserver)
        mCompositeDisposable.add(observer)
    }

    private fun clearSubscribe() {
        mCompositeDisposable.clear()
    }

    /**
     * ViewModel 销毁时会执行
     */
    override fun onCleared() {
        super.onCleared()
        clearSubscribe()
    }

    override fun onAny(owner: LifecycleOwner, event: Lifecycle.Event) {}
    override fun onCreate() {}
    override fun onDestroy() {}
    override fun onStart() {}
    override fun onStop() {}
    override fun onResume() {}
    override fun onPause() {}

}