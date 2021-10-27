package com.example.deliveryfood.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.deliveryfood.R
import com.example.deliveryfood.base.BaseFragment
import com.example.deliveryfood.databinding.FragmentSearchBinding
import com.example.deliveryfood.viewmodel.SearchViewModel
import org.koin.android.ext.android.inject


class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_search
    override val viewmodel by inject<SearchViewModel>()

    companion object{
        fun newInstance(title : String) = SearchFragment().apply {
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

    override fun FragmentSearchBinding.onCreateView() {

    }

    override fun FragmentSearchBinding.onViewCreated() {

    }
}