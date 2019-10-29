package com.event.reminder.ui.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.view.inputmethod.EditorInfo
import com.android.mvvmandroidlib.ui.BaseActivity
import com.android.mvvmandroidlib.utills.LoggerUtils
import com.android.mvvmandroidlib.utills.ToastUtils
import com.event.reminder.R
import com.event.reminder.constant.NavigationConstant
import com.event.reminder.data.model.response.LoggedInUserModel
import com.event.reminder.databinding.LoginActivityBinding
import com.event.reminder.ui.ViewModelFactory
import com.event.reminder.ui.dashboard.DashboardActivity
import com.event.reminder.utills.EventReminderSharedPrefUtils

class LoginActivity : BaseActivity<LoginActivityBinding, LoginViewModel>() {

    private val TAG: String = LoginActivity::class.java.simpleName
    override fun getViewDataBinding(): LoginActivityBinding {
        return DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

    override fun getObservableViewModel(): LoginViewModel {
        return ViewModelProviders.of(this, ViewModelFactory())
            .get(LoginViewModel::class.java)
    }

    override fun onCreateBinding() {
        binding.viewModel = viewModel
    }

    override fun setupToolbar() {
        ToastUtils.showMessage(application, R.string.app_name)
    }

    override fun setInitialData() {
        super.setInitialData()

        viewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            if(loginState.isDataValid){
                binding.ilUsername.isErrorEnabled = false
                binding.ilPassword.isErrorEnabled = false
            }else{
                if (loginState.usernameError != null) {
                    binding.ilUsername.isErrorEnabled = true
                    binding.ilUsername.error = getString(loginState.usernameError)
                }
                if (loginState.passwordError != null) {
                    binding.ilPassword.isErrorEnabled = true
                    binding.ilPassword.error = getString(loginState.passwordError)
                }
            }

        })

        viewModel.loginResult?.observe(this@LoginActivity, Observer {
            val result = it ?: return@Observer

            when {
                result.success != null -> {

                    val loggedInUser = result.success
                    if (loggedInUser?.success == true) {
                        savedDataToLocalStorage(loggedInUser)
                        startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
                        finish()
                    } else {
                        result.success!!.errorMessage?.let { error -> viewModel.failedEventErrorMessage.sendEvent(error) }
                    }
                }
                result.errorMessage != null -> {
                    result.errorMessage?.let { error -> viewModel.failedEventErrorMessage.sendEvent(error) }
                }
            }
        })

        viewModel.navigationEvent.observe(this@LoginActivity, Observer {

            when (it ?: return@Observer) {
                NavigationConstant.SIGN_UP_SCREEN -> {
                    startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
                }
                NavigationConstant.HOME_SCREEN -> {
                    startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
                }
                NavigationConstant.FORGET_PASSWORD_SCREEN -> {
                    ToastUtils.showMessage(this@LoginActivity, R.string.under_development)
                }
                else -> {
                    LoggerUtils.error(TAG, getString(R.string.unknown_navigation))
                }
            }
        })

        binding.etPassword.apply {

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        viewModel.login()
                }
                false
            }
        }
    }

    /**
     * Method is used to set data to local storage.
     *
     *@param loggedInUser: logged in user details
     */
    private fun savedDataToLocalStorage(loggedInUser: LoggedInUserModel) {
        EventReminderSharedPrefUtils.setUserLoggedIn(true)
        loggedInUser.accessToken?.let { accessToken ->
            EventReminderSharedPrefUtils.setAccessToken(accessToken)
        }
        loggedInUser.userDetails?.userId?.let { userId ->
            EventReminderSharedPrefUtils.setUserId(userId)
        }
    }
}
