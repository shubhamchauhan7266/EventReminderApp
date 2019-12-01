package com.event.reminder.ui.dashboard

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.android.mvvmandroidlib.ui.BaseActivity
import com.event.reminder.R
import com.event.reminder.callback.INavigationCallback
import com.event.reminder.databinding.DashboardActivityBinding
import com.event.reminder.enums.NavigationScreen
import com.event.reminder.ui.ViewModelFactory
import com.event.reminder.ui.dashboard.profile.ProfileDetailsFragmentDirections
import com.google.android.material.bottomnavigation.BottomNavigationView


class DashboardActivity : BaseActivity<DashboardActivityBinding, DashboardViewModel>(),
    INavigationCallback {

    private lateinit var navController: NavController

    override fun onCreateBinding() {
        binding.viewModel = viewModel
        navController = findNavController(R.id.nav_dashboard_fragment)
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

    override fun setInitialData() {
        super.setInitialData()
        binding.navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        onNavDestinationSelected(
            item,
            navController
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    override fun navigateTo(navigationScreen: NavigationScreen, bundle: Bundle?) {
        when (navigationScreen) {
            NavigationScreen.PROFILE_SCREEN -> {
                binding.navView.selectedItemId = R.id.profileDetailsFragment
            }
            NavigationScreen.PROFILE_TO_OTP_SCREEN -> {
                navController.navigate(ProfileDetailsFragmentDirections.actionProfileDetailsFragmentToOTPVerificationFragment())
            }
            else -> {

            }
        }
    }

}
