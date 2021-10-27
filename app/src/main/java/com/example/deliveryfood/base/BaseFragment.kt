package com.example.deliveryfood.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.deliveryfood.R

abstract class BaseFragment<T : ViewDataBinding, R : BaseViewModel> : Fragment() {

    lateinit var viewDataBinding : T

    abstract val layoutResourceId : Int

    abstract val viewmodel : R

    abstract fun initStartView()

    abstract fun initDataBinding()

    abstract fun initAfterBinding()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)
        viewDataBinding.onCreateView()
        viewDataBinding.onViewCreated()

        initStartView()
        initDataBinding()
        initAfterBinding()

        return viewDataBinding.root
    }

    open fun T.onCreateView() = Unit

    open fun T.onViewCreated() = Unit
}