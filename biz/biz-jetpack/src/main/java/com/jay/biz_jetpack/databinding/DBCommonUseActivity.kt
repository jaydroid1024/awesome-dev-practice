package com.jay.biz_jetpack.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARPath
import com.jay.biz_jetpack.R
import com.jay.biz_jetpack.databinding.data.User

@Route(path = ARPath.PathJetPack.DATA_BINDING_USE_ACTIVITY_PATH)
class DBCommonUseActivity : AppCompatActivity() {

    private val user = User("Jay-002", "Droid")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindViewWithDataBinding()
    }

    private fun bindViewWithDataBinding() {
        /*
        BizJetpackActivityDataBindingBinding.java
        位置：build/generated/data_binding_base_class_source_out/debug/out/包名/databinding/BizJetpackActivityDataBindingBinding.java
        作用：持有含有id的View的引用 / 持有绑定的类的引用

        BizJetpackActivityDataBindingBindingImpl.java
        位置：build/generated/source/kapt/debug/包名/databinding/BizJetpackActivityDataBindingBindingImpl.java
        作用：持有没有id的View的引用 / 具体实现了绑定

        BR.java
        位置：/build/generated/source/kapt/debug/包名/BR.java
        作用：BR文件储存了引入的数据类的id， 功能与R文件类似

        DataBinderMapperImpl.java
        位置： /build/generated/source/kapt/debug/包名/DataBinderMapperImpl.java
        作用： 这个类主要是提供了从布局文件layoutId到ViewDataBinding类对象的映射，主要是用于在加载Layout返回对应的ViewDataBinding对象。

        流程分析
        DataBindingUtil.setContentView（)
        activity.setContentView(layoutId);
        bindToAddedViews()//判断布局是否有多个子布局，如果有则遍历存入View数组，最后调用不同参数的bind方法
        DataBinderMapperImpl-getDataBinder() // 获得对应Layout的VIewDataBinding类实例
        ...

         */


        val binding: BizJetpackActivityDataBindingUseBinding =
            DataBindingUtil.setContentView(this, R.layout.biz_jetpack_activity_data_binding_use)
        binding.user = user

    }


}