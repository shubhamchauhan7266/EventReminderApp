package com.event.reminder.ui.authentication

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.android.mvvmandroidlib.ui.BaseActivity
import com.android.mvvmandroidlib.utills.LoggerUtils
import com.android.mvvmandroidlib.utills.StringUtils
import com.event.reminder.R
import com.event.reminder.callback.INavigationCallback
import com.event.reminder.constant.BundleArgsConstant
import com.event.reminder.constant.NavigationConstant
import com.event.reminder.databinding.AuthenticationActivityBinding
import com.event.reminder.ui.ViewModelFactory
import com.event.reminder.ui.authentication.login.LoginFragmentDirections
import com.event.reminder.ui.authentication.signup.SignUpFragmentDirections
import com.event.reminder.ui.authentication.splash.SplashFragmentDirections

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

    override fun navigateTo(navigationConstant: NavigationConstant, bundle: Bundle?) {
        when (navigationConstant) {
            NavigationConstant.SIGN_UP_SCREEN -> {
                navController.navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
            }
            NavigationConstant.DASHBOARD_SCREEN -> {
                navController.navigate(LoginFragmentDirections.actionLoginFragmentToDashboardActivity())
                finish()
            }
            NavigationConstant.SPLASH_TO_DASHBOARD_SCREEN -> {
                navController.navigate(SplashFragmentDirections.actionSplashFragmentToDashboardActivity())
                finish()
            }
            NavigationConstant.FORGET_PASSWORD_SCREEN -> {
                navController.navigate(LoginFragmentDirections.actionLoginFragmentToForgetPasswordFragment())
            }
            NavigationConstant.LOGIN_SCREEN -> {
                navController.popBackStack()
            }
            NavigationConstant.SPLASH_TO_LOGIN_SCREEN -> {
                navController.navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
            }
            NavigationConstant.SIGN_UP_TO_OTP_SCREEN -> {
                val mobileNumber =
                    bundle?.getString(BundleArgsConstant.MOBILE_NUMBER) ?: StringUtils.EMPTY
                val emailId = bundle?.getString(BundleArgsConstant.EMAIL_ID) ?: StringUtils.EMPTY
                navController.navigate(
                    SignUpFragmentDirections.actionSignUpFragmentToOTPVerificationFragment(
                        mobileNumber, emailId
                    )
                )
            }
            else -> {
                LoggerUtils.error(TAG, getString(R.string.unknown_navigation))
            }
        }
    }
}
