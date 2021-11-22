package com.example.deliveryfood.view.activity

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.deliveryfood.R
import com.example.deliveryfood.base.BaseActivity
import com.example.deliveryfood.databinding.ActivityNewAddressBinding
import com.example.deliveryfood.utils._enum.WebviewPageType
import com.example.deliveryfood.view.fragment.WebviewFragment
import com.example.deliveryfood.view.fragment.WebviewResultFragment
import com.example.deliveryfood.viewmodel.DeliveryAddressViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.IllegalArgumentException

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

        viewmodel.current_fragment_type.observe(this){
            changeCurrentFragment(it)
        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
        supportFragmentManager.beginTransaction().add(R.id.nav_host_fragment_content_new_address, WebviewFragment.newInstance(WebviewPageType.WEBVIEW1.title)).commitAllowingStateLoss()
    }

    override fun ActivityNewAddressBinding.onCreate() {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    private fun changeCurrentFragment(webviewPageType : WebviewPageType){
        val transaction = supportFragmentManager.beginTransaction()

        var targetFragment = supportFragmentManager.findFragmentByTag(webviewPageType.tag)

        if(targetFragment == null){
            targetFragment = getFragment(webviewPageType)
            transaction.addToBackStack(null).replace(R.id.nav_host_fragment_content_new_address, targetFragment, webviewPageType.tag)
        }

        transaction.show(targetFragment)

        WebviewPageType.values()
            .filterNot{it == webviewPageType}
            .forEach { type ->
                supportFragmentManager.findFragmentByTag(type.tag)?.let {
                    transaction.hide(it)
                }
            }

        transaction.commitAllowingStateLoss()
    }

    private fun getFragment(webviewPageType : WebviewPageType) : Fragment {
        return when(webviewPageType.tag){
            "address api" -> WebviewFragment.newInstance(webviewPageType.title)
            "address api result" -> WebviewResultFragment.newInstance(webviewPageType.title)
            else -> throw IllegalArgumentException("not found menu item id")
        }
    }
}