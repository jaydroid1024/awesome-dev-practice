package com.jay.biz_mvvm.databinding

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARPath
import com.jay.biz_mvvm.R
import com.jay.biz_mvvm.databinding.data.UserObservableViewModel
import com.jay.biz_mvvm.databinding.data.UserViewModel


@Route(path = ARPath.PathJetPack.DATA_BINDING_VIEW_MODEL_ACTIVITY_PATH)
class DBViewModelActivity : AppCompatActivity() {

    //Kotlin 扩展 方式实例化 ViewModel 默认 SavedStateViewModelFactory
    private val userModel: UserViewModel by viewModels()
    private val userObservableViewModel: UserObservableViewModel by viewModels()
    val viewModel: UserViewModel by viewModels { SavedStateViewModelFactory(application, this) }

    // 或者不使用 ktx
    val viewModel2 = ViewModelProvider(this, SavedStateViewModelFactory(application, this))
        .get(UserViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindData()
    }

    private fun bindData() {

        val binding: BizJetpackActivityDataBindingViewModelBinding =
            DataBindingUtil.setContentView(
                this,
                R.layout.biz_jetpack_activity_data_binding_view_model
            )
        binding.userViewModel = userModel
        binding.userObservableViewModel = userObservableViewModel

        updateData(userModel, userObservableViewModel)

        binding.tvLabelViewModel.setOnClickListener {
            updateData(userModel, userObservableViewModel)
        }

    }

    private fun updateData(
        userModel: UserViewModel,
        userObservableViewModel: UserObservableViewModel
    ) {

        userModel.firstName = "DBViewModelActivity-jay1: " + System.currentTimeMillis() + "\n"
        userModel.lastName = "DBViewModelActivity-droid1: " + System.currentTimeMillis()

        userObservableViewModel.firstName = "DBViewModelActivity-jay2: " + System.currentTimeMillis() + "\n"
        userObservableViewModel.lastName = "DBViewModelActivity-droid2: " + System.currentTimeMillis()
        userObservableViewModel.notifyChange()

    }

}