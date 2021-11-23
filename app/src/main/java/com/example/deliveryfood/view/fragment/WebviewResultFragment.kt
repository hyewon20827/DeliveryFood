package com.example.deliveryfood.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.example.deliveryfood.R
import com.example.deliveryfood.base.BaseFragment
import com.example.deliveryfood.databinding.FragmentSecondBinding
import com.example.deliveryfood.viewmodel.DeliveryAddressViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WebviewResultFragment : BaseFragment<FragmentSecondBinding, DeliveryAddressViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_second
    override val viewmodel: DeliveryAddressViewModel by activityViewModels<DeliveryAddressViewModel>()

    private lateinit var resultHashMap : HashMap<String, String>

    companion object{
        fun newInstance(title : String) = WebviewResultFragment().apply {
            arguments = Bundle().apply {
                putString("title", title)
            }
        }
    }

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
        resultHashMap = viewmodel.address_api_result_hashmap.value!!
        setViewState()
    }

    override fun FragmentSecondBinding.onViewCreated() {

    }

    fun setViewState(){
        viewDataBinding.mainAddress.text = resultHashMap.get("address")
    }
}