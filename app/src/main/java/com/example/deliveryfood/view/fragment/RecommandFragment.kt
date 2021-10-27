package com.example.deliveryfood.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.deliveryfood.R
import com.example.deliveryfood.base.BaseFragment
import com.example.deliveryfood.databinding.FragmentRecommandBinding
import com.example.deliveryfood.viewmodel.RecommandViewModel
import org.koin.android.ext.android.inject


class RecommandFragment : BaseFragment<FragmentRecommandBinding, RecommandViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_recommand
    override val viewmodel by inject<RecommandViewModel>()

    companion object{
        fun newInstance(title : String) = RecommandFragment().apply {
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

    override fun FragmentRecommandBinding.onCreateView() {

    }

    override fun FragmentRecommandBinding.onViewCreated() {

    }
}