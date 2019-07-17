package com.android.mvvmandroidlib.ui

import android.arch.lifecycle.Observer
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        onCreateViewBinding(inflater, container)
        setupToolbar()
        initMembers()

        return binding.root
    }

    protected abstract fun onCreateViewBinding(inflater: LayoutInflater, container: ViewGroup?)

    protected open fun setupToolbar() {

    }

    protected open fun initMembers() {
        viewModel.startProgressEvent.observe(this, Observer {
            //            DialogUtils.showProgressDialog(this, it!!)
        })

        viewModel.stopProgressEvent.observe(this, Observer {
            //            DialogUtils.hideProgressDialog()
        })

        viewModel.failedEvent.observe(this, Observer {
            //            DialogUtils.hideProgressDialog()
        })
    }
}