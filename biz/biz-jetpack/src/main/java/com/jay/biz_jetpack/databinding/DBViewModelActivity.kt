package com.jay.biz_jetpack.databinding

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARPath
import com.jay.biz_jetpack.R
import com.jay.biz_jetpack.databinding.data.UserObservableViewModel
import com.jay.biz_jetpack.databinding.data.UserViewModel

@Route(path = ARPath.PathJetPack.DATA_BINDING_VIEW_MODEL_ACTIVITY_PATH)
class DBViewModelActivity : AppCompatActivity() {

    private val userModel: UserViewModel by viewModels()
    private val userObservableViewModel: UserObservableViewModel by viewModels()


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