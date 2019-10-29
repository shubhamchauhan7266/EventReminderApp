package com.event.reminder.ui.dashboard

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.design.widget.BottomNavigationView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.android.mvvmandroidlib.ui.BaseActivity
import com.event.reminder.R
import com.event.reminder.constant.NavigationConstant
import com.event.reminder.databinding.DashboardActivityBinding
import com.event.reminder.ui.ViewModelFactory
import com.event.reminder.ui.dashboard.home.HomeFragment


class DashboardActivity : BaseActivity<DashboardActivityBinding, DashboardViewModel>(),
    HomeFragment.IOnStartCallback {

    private lateinit var navController: NavController

    override fun onCreateBinding() {
        binding.viewModel = viewModel
        navController = Navigation.findNavController(this@DashboardActivity, R.id.nav_dashboard_fragment)
        binding.navView.setupWithNavController(
            navController
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
        super.setupToolbar()
        binding.toolbar
        setSupportActionBar(binding.toolbar)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home_menu_option, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setInitialData() {
        super.setInitialData()
        binding.navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        viewModel.navigationEvent.observe(this@DashboardActivity, Observer { navigation ->

            when (navigation) {
                NavigationConstant.PROFILE_SCREEN -> {
                    binding.navView.selectedItemId = R.id.profileDetailsFragment
                }
                else -> {

                }
            }

        })
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        if (item.itemId != R.id.homeFragment) {
            binding.toolbar.visibility = View.GONE
        }
        onNavDestinationSelected(
            item,
            navController
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    override fun onHomeFragmentLoad() {
        binding.toolbar.visibility = View.VISIBLE
    }
}
