package com.jay.biz_mvvm.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

/**
 *
 * @Description
 * @date 12/21/20 11:24 AM
 * @author BryceCui
 * @Version 1.0
 */
abstract class BaseVMFragment<VM : BaseViewModel> : Fragment() {

    var mViewModel: VM? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return initViewBinding()
    }

    abstract fun initViewBinding(): View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        creteViewModel()
        createObserver()
        initView()
        initData()

    }

    open fun initView() {

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
    open fun createObserver() {

    }


}