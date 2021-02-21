package com.jay.biz_mvvm.mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

/**
 *
 * @Description  使用ViewModel的简单封装基本功能
 * @date 12/18/20 3:02 PM
 * @author BryceCui
 * @Version 1.0
 */
abstract class BaseVMActivity<VM : BaseViewModel> : AppCompatActivity() {

    var mViewModel: VM? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
        creteViewModel()
        createObserver()
        initView()
        initData()
    }

    open fun initView() {

    }

    open fun initViewBinding() {

    }

    open fun initData() {

    }


    /**
     * 获取ViewModel
     *
     */
    private fun creteViewModel() {
        mViewModel = ViewModelProvider(this).get(getVmClazz(this))
    }

    /**
     * 创建LiveData观察
     * 例：mViewModel.mLiveData.observe(this,Observer<Result<T>> { data ->})
     * 例：mViewModel.run{
     *    mLiveData.observe(this,Observer<Result<T>> { data ->})
     *    }
     */
    open fun createObserver() {}


}