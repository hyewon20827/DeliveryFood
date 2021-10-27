package com.example.deliveryfood.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.deliveryfood.R
import com.example.deliveryfood.base.BaseFragment
import com.example.deliveryfood.databinding.FragmentMyPageBinding
import com.example.deliveryfood.viewmodel.MyPageViewModel
import org.koin.android.ext.android.inject


class MyPageFragment : BaseFragment<FragmentMyPageBinding,MyPageViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_my_page

    override val viewmodel by inject<MyPageViewModel>()

    companion object{
        fun newInstance(title : String) = MyPageFragment().apply {
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

    override fun FragmentMyPageBinding.onCreateView() {

    }

    override fun FragmentMyPageBinding.onViewCreated() {

    }

}