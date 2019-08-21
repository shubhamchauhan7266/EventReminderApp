package com.event.reminder.ui.dashboard

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.design.widget.BottomNavigationView
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.android.mvvmandroidlib.ui.BaseActivity
import com.event.reminder.R
import com.event.reminder.databinding.DashboardActivityBinding
import com.event.reminder.ui.ViewModelFactory


class DashboardActivity : BaseActivity<DashboardActivityBinding, DashboardViewModel>() {
    override fun onCreateBinding() {
        binding.viewModel = viewModel
        binding.navView.setupWithNavController(
            Navigation.findNavController(
                this@DashboardActivity,
                R.id.nav_dashboard_fragment
            )
        )
    }

    override fun getViewDataBinding(): DashboardActivityBinding {
        return DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
    }

    override fun getObservableViewModel(): DashboardViewModel {
        return ViewModelProviders.of(this, ViewModelFactory())
            .get(DashboardViewModel::class.java)
    }

    override fun setupToolbar() {

    }

    override fun initMembers() {
        super.initMembers()
        binding.navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        onNavDestinationSelected(
            item,
            Navigation.findNavController(this@DashboardActivity, R.id.nav_dashboard_fragment)
        )
    }
}
