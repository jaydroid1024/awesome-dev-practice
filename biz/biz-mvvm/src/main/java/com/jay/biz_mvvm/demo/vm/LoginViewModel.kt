package com.jay.biz_mvvm.demo.vm

import android.app.Application
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.jay.base_component.R
import com.jay.base_component.arouter.ARHelper
import com.jay.base_component.arouter.ARPath
import com.jay.base_component.base.mvp.BaseObserver
import com.jay.base_component.network.bean.wan.user.User
import com.jay.base_lib.utils.ToastUtils
import com.jay.biz_mvvm.demo.m.repository.UserRepository
import com.jay.biz_mvvm.demo.m.repository.UserRepositoryImpl
import com.jay.biz_mvvm.demo.m.repository.source.local.UserLocalDataSourceImpl
import com.jay.biz_mvvm.demo.m.repository.source.remote.UserNetWorkDataSourceImpl
import com.jay.biz_mvvm.mvvm.base.BaseViewModel
import kotlinx.coroutines.launch

/**
 * @author wangxuejie
 * @version 1.0
 * @date 1/2/21
 */
class LoginViewModel(val app: Application, val state: SavedStateHandle) : BaseViewModel(app) {

    private var mLoginRepository: UserRepository? = null
    val userNameLD: MutableLiveData<String> by lazy { MutableLiveData<String>("mvvm") }
    val passwordLD: MutableLiveData<String> by lazy { MutableLiveData<String>("123456") }
    val userLD: MutableLiveData<User> by lazy { MutableLiveData<User>() }

    init {
        mLoginRepository =
            UserRepositoryImpl(UserNetWorkDataSourceImpl(), UserLocalDataSourceImpl())

        state.set("id", "123")
    }

    fun onRegister() {
        ARHelper.routerTo(ARPath.PathUser.REGISTER_ACTIVITY_PATH)
    }

    fun onLogin() {
        val username = userNameLD.value.toString().trim()
        val password = passwordLD.value.toString().trim()
        if (TextUtils.isEmpty(username)) {
            ToastUtils.showShort("请输入用户名")
            return
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtils.showShort("请输入密码")
            return
        }
        login(username, password)
        loginWithCoroutine(username, password)
    }

    private fun loginWithCoroutine(username: String, password: String) {

        viewModelScope.launch {
            // Coroutine that will be canceled when the ViewModel is cleared.
        }

    }


    fun login(username: String, password: String) {
        addSubscribe(mLoginRepository!!.login(username, password),
            object : BaseObserver<User>() {
                override fun onSuccess(user: User?) {
                    ToastUtils.showShort("user:" + user)

                    Log.d("Jay", app.getString(R.string.base_component_app_name))
                    Log.d("Jay", state.get("id"))
                    userLD.value = user
                }
            })


    }


}