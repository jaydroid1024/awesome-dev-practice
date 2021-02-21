//package com.jay.biz_mvvm.hint
//
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.databinding.DataBindingUtil
//import com.alibaba.android.arouter.facade.annotation.Route
//import com.jay.base_component.arouter.ARPath
//import com.jay.biz_mvvm.R
//import com.jay.biz_mvvm.databinding.BizJetpackActivityHintUseBinding
//import com.jay.biz_mvvm.hint.data.User
//import dagger.hilt.android.AndroidEntryPoint
//import javax.inject.Inject
//
//@AndroidEntryPoint // @AndroidEntryPoint 会为项目中的每个 Android 类生成一个单独的 Hilt 组件。这些组件可以从它们各自的父类接收依赖项，如[组件层次结构](https://developer.android.com/training/dependency-injection/hilt-android#component-hierarchy)中所述。
//@Route(path = ARPath.PathJetPack.HINT_USE_ACTIVITY_PATH)
//class HintUseActivity : AppCompatActivity() {
//
//    @Inject
//    lateinit var user: User
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        bindViewWithDataBinding()
//    }
//
//    private fun bindViewWithDataBinding() {
//        val binding: BizJetpackActivityHintUseBinding =
//            DataBindingUtil.setContentView(this, R.layout.biz_jetpack_activity_hint_use)
//        binding.user = user
//    }
//
//
//}