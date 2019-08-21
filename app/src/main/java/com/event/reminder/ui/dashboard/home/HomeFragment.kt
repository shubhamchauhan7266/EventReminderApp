package com.event.reminder.ui.dashboard.home

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.mvvmandroidlib.ui.BaseFragment
import com.event.reminder.R
import com.event.reminder.databinding.HomeFragmentBinding
import com.event.reminder.ui.ViewModelFactory

class HomeFragment : BaseFragment<HomeFragmentBinding, HomeViewModel>() {
    override fun onCreateViewBinding() {
        binding.viewModel = viewModel
    }

    override fun getObservableViewModel(): HomeViewModel {
        return ViewModelProviders.of(this, ViewModelFactory())
            .get(HomeViewModel::class.java)
    }

    override fun getViewDataBinding(inflater: LayoutInflater, container: ViewGroup?): HomeFragmentBinding {
        return DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )
    }
}
