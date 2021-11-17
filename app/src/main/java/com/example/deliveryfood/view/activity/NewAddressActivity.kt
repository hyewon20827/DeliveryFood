package com.example.deliveryfood.view.activity

import android.os.Bundle
import android.view.MenuItem
import com.example.deliveryfood.R
import com.example.deliveryfood.base.BaseActivity
import com.example.deliveryfood.databinding.ActivityNewAddressBinding
import com.example.deliveryfood.view.fragment.WebviewFragment
import com.example.deliveryfood.viewmodel.DeliveryAddressViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewAddressActivity : BaseActivity<ActivityNewAddressBinding, DeliveryAddressViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_new_address
    override val viewmodel : DeliveryAddressViewModel by viewModel()

    override fun initStartView() {

    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager.beginTransaction().add(R.id.nav_host_fragment_content_new_address, WebviewFragment()).commitAllowingStateLoss()
    }

    override fun ActivityNewAddressBinding.onCreate() {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}