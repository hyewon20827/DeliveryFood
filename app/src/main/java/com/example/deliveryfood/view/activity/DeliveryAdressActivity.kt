package com.example.deliveryfood.view.activity

import android.app.Activity
import android.os.Bundle
import android.view.Gravity.BOTTOM
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.viewbinding.ViewBinding
import com.example.deliveryfood.R
import com.example.deliveryfood.viewmodel.DeliveryAdressViewModel
import org.koin.java.KoinJavaComponent.inject

class DeliveryAdressActivity : Activity() {

    private lateinit var viewDataBinding : ViewBinding

    private val layoutResouceId : Int
        get() = R.layout.activity_delivery_adress

    private val viewmodel by inject<DeliveryAdressViewModel>(DeliveryAdressViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityPopupSetting()
        viewDataBinding = DataBindingUtil.setContentView(this, layoutResouceId)
    }

    private fun activityPopupSetting(){
        window.apply {
            attributes = WindowManager.LayoutParams().apply {
                this.gravity = BOTTOM
            }
        }
    }
}