package com.event.reminder.ui

import android.arch.lifecycle.Observer
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity<T : ViewDataBinding, V : BaseObservableViewModel> : AppCompatActivity() {

    protected lateinit var binding : T
    protected lateinit var viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onCreateBinding()
        setupToolbar()
        initMembers()
    }

    protected abstract fun onCreateBinding()

    protected abstract fun setupToolbar()

    protected open fun initMembers(){
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