package com.jay.biz_mvvm.databinding

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARPath
import com.jay.biz_mvvm.R
import com.jay.biz_mvvm.databinding.data.UserViewModelLiveData

@Route(path = ARPath.PathJetPack.DATA_BINDING_LIVE_DATA_ACTIVITY_PATH)
class DBLiveDataActivity : AppCompatActivity() {

    private val model: UserViewModelLiveData by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindData()
    }

    private fun bindData() {

        val binding: BizJetpackActivityDataBindingLiveDataBinding =
            DataBindingUtil.setContentView(
                this,
                R.layout.biz_jetpack_activity_data_binding_live_data
            )
        // Specify the current activity as the lifecycle owner.
        binding.lifecycleOwner = this
        binding.userViewModelLiveData = model
        updateData(binding)


    }

    /**
     * 在Activity中自己实现观察数据变化
     *
     * @param binding
     */
    private fun observeLiveData(binding: BizJetpackActivityDataBindingLiveDataBinding) {
        // Create the observer which updates the UI.
//        val nameObserver = Observer<String> { newName ->
//            // Update the UI, in this case, a TextView.
//            binding.tvLabelViewModel.text = newName
//            Log.d("Jay", "newName: $newName")
//        }
//
//        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
//        model.currentName.observe(this, nameObserver)
//
    }

    private fun updateData(binding: BizJetpackActivityDataBindingLiveDataBinding) {

        binding.tvLabelViewModel.setOnClickListener {
            Log.d("Jay", "setOnClickListener: ")
            updateData(binding)
        }
        model.firstName = "jay1: " + System.currentTimeMillis() + "\n"
        model.lastName = "droid1: " + System.currentTimeMillis()
        model.currentName.value = "droid1: " + System.currentTimeMillis()

    }

}