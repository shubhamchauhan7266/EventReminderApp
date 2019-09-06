package com.android.mvvmandroidlib.ui

import android.content.Context
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.viewModel = getObservableViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        this.binding = getViewDataBinding(inflater, container)
        onCreateViewBinding()
        setupToolbar()
        initMembers()

        return binding.root
    }

    protected abstract fun onCreateViewBinding()

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
    protected open fun initMembers() {
    }
}