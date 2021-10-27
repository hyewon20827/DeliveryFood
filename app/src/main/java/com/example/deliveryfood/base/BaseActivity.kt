package com.example.deliveryfood.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T : ViewBinding, R : BaseViewModel> : AppCompatActivity() {

    lateinit var viewDataBinding : T

    abstract val layoutResourceId : Int

    abstract val viewmodel : R

    abstract fun initStartView()

    abstract fun initDataBinding()

    abstract fun initAfterBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, layoutResourceId)
        viewDataBinding.onCreate()

        initStartView()
        initDataBinding()
        initAfterBinding()
    }

    open fun T.onCreate() = Unit
}