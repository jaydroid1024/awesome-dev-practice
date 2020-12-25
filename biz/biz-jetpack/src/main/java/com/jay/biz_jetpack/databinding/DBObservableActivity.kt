package com.jay.biz_jetpack.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARPath
import com.jay.biz_jetpack.R
import com.jay.biz_jetpack.databinding.data.ObservableFieldUser
import com.jay.biz_jetpack.databinding.data.ObservableUser
import kotlinx.android.synthetic.main.biz_jetpack_activity_data_binding_observabe.*

@Route(path = ARPath.PathJetPack.DATA_BINDING_OBSERVABLE_ACTIVITY_PATH)
class DBObservableActivity : AppCompatActivity() {

    private val observableFieldUser = ObservableFieldUser()
    private val observableUser = ObservableUser()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindData()
        updateData()
    }

    private fun bindData() {
        val binding: BizJetpackActivityDataBindingObservabeBinding =
            DataBindingUtil.setContentView(
                this,
                R.layout.biz_jetpack_activity_data_binding_observabe
            )
        binding.observableFieldUser = observableFieldUser
        binding.observableUser = observableUser
    }

    private fun updateData() {

        tv_label.setOnClickListener {
            updateData()
        }

        observableFieldUser.firstName.set("jay1: " + System.currentTimeMillis() + "\n")
        observableFieldUser.lastName.set("droid1: " + System.currentTimeMillis())

        observableUser.firstName = "jay2: " + System.currentTimeMillis() + "\n"
        observableUser.lastName = "droid2: " + System.currentTimeMillis()

    }

}