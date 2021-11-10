package com.example.deliveryfood.base

import android.app.Activity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.viewbinding.ViewBinding

abstract class ActivityBase<T : ViewBinding, R : BaseViewModel> : Activity() {

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