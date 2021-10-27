package com.example.deliveryfood.view.activity

import android.os.Bundle
import com.example.deliveryfood.R
import com.example.deliveryfood.base.BaseActivity
import com.example.deliveryfood.databinding.ActivityCartBinding
import com.example.deliveryfood.viewmodel.CartViewModel
import org.koin.android.ext.android.inject

class CartActivity : BaseActivity<ActivityCartBinding, CartViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_cart
    override val viewmodel by inject<CartViewModel>()

    companion object{
        fun newInstance(title : String) = CartActivity()
    }

    override fun initStartView() {

    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun ActivityCartBinding.onCreate() {

    }
}