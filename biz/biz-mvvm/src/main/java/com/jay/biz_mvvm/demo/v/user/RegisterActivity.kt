package com.jay.biz_mvvm.demo.v.user

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARPath
import com.jay.base_component.network.bean.wan.user.RegisterResponse
import com.jay.base_component.widget.ClearEditText
import com.jay.biz_mvvm.R

@Route(path = ARPath.PathUser.REGISTER_ACTIVITY_PATH)
class RegisterActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var usernameEditText: ClearEditText
    private lateinit var passwordEditText: ClearEditText
    private lateinit var repasswordEditText: ClearEditText
    private lateinit var registerBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.biz_mvvm_activity_register)
    }


    fun initView() {
        toolbar = findViewById(R.id.tb_register)
        setSupportActionBar(toolbar)
        supportActionBar?.elevation = 10f
        supportActionBar?.setTitle("注册")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                finish()
            }
        })
        usernameEditText = findViewById(R.id.cet_register_username)
        passwordEditText = findViewById(R.id.cet_register_password)
        repasswordEditText = findViewById(R.id.cet_register_repassword)
        registerBtn = findViewById(R.id.btn_register)
    }

    fun initData() {

//        registerBtn.setOnClickListener(this)
    }

    fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_register -> {
                register()
            }
        }
    }

    fun register() {
        val username = usernameEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()
        val repassword = repasswordEditText.text.toString().trim()
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_LONG).show()
            return
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_LONG).show()
            return
        }
        if (TextUtils.isEmpty(repassword)) {
            Toast.makeText(this, "请再次输入密码", Toast.LENGTH_LONG).show()
            return
        }
        if (!TextUtils.equals(password, repassword)) {
            Toast.makeText(this, "两次密码不一致", Toast.LENGTH_LONG).show()
            return
        }
//        presenter.register(username, password, repassword)
    }


    fun onRegisterResult(result: RegisterResponse?) {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show()
        finish()
    }
}
