package com.event.reminder.ui.dashboard

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.design.widget.BottomNavigationView
import com.android.mvvmandroidlib.ui.BaseActivity
import com.event.reminder.R
import com.event.reminder.databinding.HomeActivityBinding
import com.event.reminder.ui.ViewModelFactory

class HomeActivity : BaseActivity<HomeActivityBinding, HomeViewModel>() {

    override fun getViewDataBinding(): HomeActivityBinding {
        return DataBindingUtil.setContentView(this, R.layout.activity_home)
    }

    override fun getObservableViewModel(): HomeViewModel {
        return ViewModelProviders.of(this, ViewModelFactory())
            .get(HomeViewModel::class.java)
    }

    override fun onCreateBinding() {
        binding.viewModel = viewModel
    }

    override fun setupToolbar() {

    }

    override fun initMembers() {
        super.initMembers()
        binding.navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                binding.message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                binding.message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                binding.message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
