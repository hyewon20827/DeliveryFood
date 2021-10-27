package com.example.deliveryfood.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.deliveryfood.R
import com.example.deliveryfood.base.BaseFragment
import com.example.deliveryfood.databinding.FragmentCategoryBinding
import com.example.deliveryfood.viewmodel.CategoryViewModel
import org.koin.android.ext.android.inject

class CategoryFragment : BaseFragment<FragmentCategoryBinding, CategoryViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_category
    override val viewmodel by inject<CategoryViewModel>()

    companion object{
        fun newInstance(title : String) = CategoryFragment().apply {
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

    override fun FragmentCategoryBinding.onCreateView() {

    }

    override fun FragmentCategoryBinding.onViewCreated() {

    }
}