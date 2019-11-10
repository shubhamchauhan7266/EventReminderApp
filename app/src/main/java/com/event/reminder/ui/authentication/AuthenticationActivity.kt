package com.event.reminder.ui.authentication

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.android.mvvmandroidlib.ui.BaseActivity
import com.android.mvvmandroidlib.utills.LoggerUtils
import com.event.reminder.R
import com.event.reminder.callback.INavigationCallback
import com.event.reminder.constant.NavigationConstant
import com.event.reminder.databinding.AuthenticationActivityBinding
import com.event.reminder.ui.ViewModelFactory

class AuthenticationActivity :
    BaseActivity<AuthenticationActivityBinding, AuthenticationViewModel>(),
    INavigationCallback {

    private val TAG: String = AuthenticationActivity::class.java.simpleName
    private lateinit var navController: NavController

    override fun onCreateBinding() {
        binding.viewModel = viewModel
        navController = findNavController(R.id.nav_authentication_fragment)
    }

    override fun getObservableViewModel(): AuthenticationViewModel {
        return ViewModelProviders.of(this, ViewModelFactory())
            .get(AuthenticationViewModel::class.java)
    }

    override fun getViewDataBinding(): AuthenticationActivityBinding {
        return DataBindingUtil.setContentView(this, R.layout.activity_authentication)
    }

    override fun navigateTo(navigationConstant: NavigationConstant) {
        when (navigationConstant) {
            NavigationConstant.SIGN_UP_SCREEN -> {
                navController.navigate(R.id.action_loginFragment_to_signUpFragment)
            }
            NavigationConstant.DASHBOARD_SCREEN -> {
                navController.navigate(R.id.action_loginFragment_to_dashboardActivity)
                finish()
            }
            NavigationConstant.SPLASH_TO_DASHBOARD_SCREEN -> {
                navController.navigate(R.id.action_splashFragment_to_dashboardActivity)
                finish()
            }
            NavigationConstant.FORGET_PASSWORD_SCREEN -> {
                navController.navigate(R.id.action_loginFragment_to_forgetPasswordFragment)
            }
            NavigationConstant.LOGIN_SCREEN -> {
                navController.popBackStack()
            }
            NavigationConstant.SPLASH_TO_LOGIN_SCREEN -> {
                navController.navigate(R.id.action_splashFragment_to_loginFragment)
            }
            else -> {
                LoggerUtils.error(TAG, getString(R.string.unknown_navigation))
            }
        }
    }
}
