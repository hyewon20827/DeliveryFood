package com.example.deliveryfood.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.deliveryfood.R
import com.example.deliveryfood.base.BaseFragment
import com.example.deliveryfood.databinding.FragmentSecondBinding
import com.example.deliveryfood.viewmodel.DeliveryAddressViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WebviewResultFragment : BaseFragment<FragmentSecondBinding, DeliveryAddressViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_second
    override val viewmodel: DeliveryAddressViewModel by viewModel()


    override fun initStartView() {

    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun FragmentSecondBinding.onCreateView() {

    }

    override fun FragmentSecondBinding.onViewCreated() {

    }
}