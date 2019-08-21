package com.android.mvvmandroidlib.ui

import android.content.Context
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.mvvmandroidlib.viewmodel.BaseObservableViewModel

abstract class BaseFragment<T : ViewDataBinding, V : BaseObservableViewModel> : Fragment() {

    protected lateinit var binding: T
    protected lateinit var viewModel: V

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.viewModel = getObservableViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        this.binding = getViewDataBinding(inflater, container)
        onCreateViewBinding()
        setupToolbar()
        initMembers()

        return binding.root
    }

    protected abstract fun onCreateViewBinding()

    protected abstract fun getObservableViewModel(): V

    protected abstract fun getViewDataBinding(inflater: LayoutInflater, container: ViewGroup?): T

    protected open fun setupToolbar() {

    }

    protected open fun initMembers() {
    }
}