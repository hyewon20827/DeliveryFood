package com.example.deliveryfood.view.activity

import android.os.Bundle
import com.example.deliveryfood.R
import com.example.deliveryfood.base.BaseActivity
import com.example.deliveryfood.databinding.ActivityLoginBinding
import com.example.deliveryfood.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_login
    override val viewmodel : LoginViewModel by viewModel()

    override fun initStartView() {

    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}