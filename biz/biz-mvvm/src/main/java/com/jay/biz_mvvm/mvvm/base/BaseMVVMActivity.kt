package com.jay.biz_mvvm.mvvm.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType


/**
 * MVVM BaseActivity
 */
abstract class BaseMVVMActivity<V : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {

    protected var binding: V? = null

    protected var viewModel: VM? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //初始化 ViewModel
        initViewModel()
        //私有的初始化Databinding和ViewModel方法
        initViewDataBinding()
        //页面事件监听的方法，一般用于ViewModel层转到View层的事件注册
        initViewObservable()
    }

    /**
     * 注入绑定
     */
    private fun initViewDataBinding() {
        //DataBindingUtil类需要在project的build中配置 dataBinding {enabled true }, 同步后会自动关联android.databinding包
        //layout 文件中需要包含<layout> ... </layout> 根标签
        binding = DataBindingUtil.setContentView(this, initContentView())
        //关联ViewModel
        binding?.setVariable(initVariableId(), viewModel)
        //支持LiveData绑定xml，数据改变，UI自动会更新
        binding?.lifecycleOwner = this
        //让ViewModel拥有View的生命周期感应
        lifecycle.addObserver(viewModel!!)
    }

    /**
     * 初始化根布局,用于生成 DataBinding 类
     *
     * @return 布局layout的id
     */
    abstract fun initContentView(): Int

    /**
     * 初始化 ViewModel 的 id,用于绑定到 DataBinding
     *
     * @return BR 的 viewModel id
     */
    abstract fun initVariableId(): Int

    /**
     * 默认：VM_TYPE_APP
     *
     * VM_TYPE_APP : By AndroidViewModelFactory
     *
     * VM_TYPE_STATE : By SavedStateViewModelFactory
     *
     * @return VM_TYPE
     */
    protected open fun initVMType(): Int = VM_TYPE_APP

    /**
     * 初始化 ViewModel
     *
     * @return 继承 BaseViewModel 的 ViewModel
     */
    protected open fun initViewModel() {
        if (initVMType() == VM_TYPE_APP) {
            getAppViewModel()
        } else {
            getStateViewModel()
        }
    }

    /**
     * AndroidViewModelFactory:
     * 可以构造带 Application 的 AndroidViewModel 和普通的无参数的 ViewModel
     *
     * SavedStateViewModelFactory:
     * 可以构造 带 Application 的 AndroidViewModel 和带有 SavedStateHandle 的 ViewModel
     */
    private fun getAppViewModel() {
        //AndroidViewModelFactory 可以构造带 Application 的 AndroidViewModel 和普通的无参数的 ViewModel
        val androidViewModelFactory =
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        viewModel = ViewModelProvider(this, androidViewModelFactory).get(getVmClazz(this))
    }

    private fun getStateViewModel() {
        //SavedStateViewModelFactory 可以构造 带 Application 的 AndroidViewModel 和带有 SavedStateHandle 的 ViewModel
        val savedStateViewModelFactory = SavedStateViewModelFactory(application, this)
        viewModel = ViewModelProvider(this, savedStateViewModelFactory).get(getVmClazz(this))
    }


    /**
     * 获取当前类绑定的泛型ViewModel-clazz
     */
    @Suppress("UNCHECKED_CAST")
    fun <VM> getVmClazz(obj: Any): VM {
        return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as VM
    }


    /**
     * 创建LiveData观察
     * 例：mViewModel.mLiveData.observe(this,Observer<Result<T>> { data ->})
     * 例：mViewModel.run{
     *    mLiveData.observe(this,Observer<Result<T>> { data ->})
     *    }
     */
    protected open fun initViewObservable() {}


    override fun onDestroy() {
        super.onDestroy()
        if (binding != null) {
            binding!!.unbind()
        }
    }

    companion object {

        /**
         * App ViewModel
         */
        private const val VM_TYPE_APP = 0

        /**
         * State ViewModel
         */
        private const val VM_TYPE_STATE = 1
    }

}