package com.jay.biz_mvvm.demo.v.user

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARPath
import com.jay.base_component.network.bean.wan.user.User
import com.jay.base_component.widget.ClearEditText
import com.jay.biz_mvvm.BR
import com.jay.biz_mvvm.R
import com.jay.biz_mvvm.databinding.BizMvvmActivityLoginBinding
import com.jay.biz_mvvm.demo.vm.LoginViewModel
import com.jay.biz_mvvm.mvvm.base.BaseMVVMActivity


@Route(path = ARPath.PathUser.LOGIN_ACTIVITY_PATH)
class LoginActivity : BaseMVVMActivity<BizMvvmActivityLoginBinding, LoginViewModel>(),
    View.OnClickListener {

    private lateinit var usernameEditText: ClearEditText
    private lateinit var passwordEditText: ClearEditText

    private lateinit var registerTxtView: TextView
    private lateinit var closeImgView: ImageView

    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    override fun initContentView(): Int {
        return R.layout.biz_mvvm_activity_login
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initViewObservable() {
        binding?.userViewModel?.userLD?.observe(this,
            { user -> binding?.tvUserRegister?.text = user.toString() })

    }


    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    override fun initVariableId(): Int {
        return BR.userViewModel
    }


    fun initView() {
        usernameEditText = binding!!.cetLoginUsername
        passwordEditText = binding!!.cetLoginPassword
        closeImgView = binding!!.ivLoginClose
        registerTxtView = binding!!.tvUserRegister
        closeImgView.setOnClickListener {
            this.finish()
        }
        val spannableString = SpannableString(registerTxtView.text.toString().trim())
        spannableString.setSpan(
            UnderlineSpan(),
            0,
            registerTxtView.text.toString().trim().length,
            SpannableString.SPAN_INCLUSIVE_EXCLUSIVE
        )
        registerTxtView.text = spannableString

        closeImgView.setOnClickListener(this)

        //todo debug
        usernameEditText.setText("mvvm")
        passwordEditText.setText("123456")
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_login_close -> {
                finish()
            }
        }
    }


    fun showLoading() {
//        loginView.setState(LoginView.STATE_LOADING)
    }

    fun dismissLoading() {
//        loginView.setState(LoginView.STATE_FAILED)
    }

    fun onLoginResult(username: String, user: User?) {

    }


    override fun onDestroy() {
        super.onDestroy()
//        loginView.release()
    }

    companion object {

        const val TAG = "Login"
    }

    override fun initVMType(): Int {
        TODO("Not yet implemented")
    }


}
