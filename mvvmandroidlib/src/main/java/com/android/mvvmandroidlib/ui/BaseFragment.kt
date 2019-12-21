package com.android.mvvmandroidlib.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.android.mvvmandroidlib.R
import com.android.mvvmandroidlib.utills.ToastUtils
import com.android.mvvmandroidlib.viewmodel.BaseObservableViewModel

/**
 * This abstract class is used as a base class for all Fragment. This class has two property binding and viewModel
 * which will used by all subclasses.
 * Also, this class has define some structure which will be followed by its subclasses.
 *
 * @param <T> ViewDataBinding
 * @param <V> BaseObservableViewModel
 *
 * @author Shubham Chauhan
 */
abstract class BaseFragment<T : ViewDataBinding, V : BaseObservableViewModel> : Fragment() {

    protected lateinit var binding: T
    protected lateinit var viewModel: V

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.viewModel = getObservableViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        this.binding = getViewDataBinding(inflater, container)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        onCreateViewBinding(savedInstanceState)
        setupToolbar()
        setInitialData()
    }

    protected abstract fun onCreateViewBinding(savedInstanceState: Bundle?)

    protected abstract fun getObservableViewModel(): V

    protected abstract fun getViewDataBinding(inflater: LayoutInflater, container: ViewGroup?): T

    /**
     * Method is used to set toolbar.
     */
    protected open fun setupToolbar() {

    }

    /**
     * Method is used to initialize and setting initial data.
     */
    protected open fun setInitialData() {
        viewModel.progressEvent.observe(this, Observer {
            //            DialogUtils.showProgressDialog(this, it!!)
        })

        viewModel.failedEventErrorCode.observe(this, Observer {
            ToastUtils.showMessage(activity!!, it ?: R.string.unknown_error)
        })

        viewModel.failedEventErrorMessage.observe(this, Observer {
            ToastUtils.showMessage(activity!!, it ?: getString(R.string.unknown_error))
        })
    }
}