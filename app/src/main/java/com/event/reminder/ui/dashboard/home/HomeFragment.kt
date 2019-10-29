package com.event.reminder.ui.dashboard.home

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.mvvmandroidlib.ui.BaseFragment
import com.event.reminder.R
import com.event.reminder.adapter.HomeEventListAdapter
import com.event.reminder.databinding.HomeFragmentBinding
import com.event.reminder.ui.ViewModelFactory

class HomeFragment : BaseFragment<HomeFragmentBinding, HomeViewModel>() {

    private var iOnStartCallback: IOnStartCallback? = null

    override fun onCreateViewBinding() {
        binding.viewModel = viewModel

        initializeAdapter()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        iOnStartCallback = context as IOnStartCallback
    }

    /**
     * Method is used to initialize adapter list
     */
    private fun initializeAdapter() {
        binding.rvHomeEventList.layoutManager = LinearLayoutManager(activity)
        binding.rvHomeEventList.itemAnimator = DefaultItemAnimator()
        binding.rvHomeEventList.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.rvHomeEventList.adapter = HomeEventListAdapter(null)
    }

    override fun getObservableViewModel(): HomeViewModel {
        return ViewModelProviders.of(this, ViewModelFactory())
            .get(HomeViewModel::class.java)
    }

    override fun getViewDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): HomeFragmentBinding {
        return DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )
    }

    override fun onStart() {
        super.onStart()
        iOnStartCallback?.onHomeFragmentLoad()
    }

    /**
     * Interface is used to give callback when home fragment is loaded successfully.
     */
    interface IOnStartCallback {
        fun onHomeFragmentLoad()
    }
}
