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
import com.event.reminder.databinding.LoginActivityBinding
import com.event.reminder.ui.ViewModelFactory
import com.event.reminder.ui.dashboard.DashboardActivity

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

    override fun initMembers() {
        super.initMembers()

        viewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            binding.btLogin.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                binding.etUsername.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                binding.etPassword.error = getString(loginState.passwordError)
            }
        })

        viewModel.loginResult?.observe(this@LoginActivity, Observer {
            val result = it ?: return@Observer

            when {
                result.success != null -> {

                    val loggedInUser = result.success
                    if (loggedInUser!!.status) {

                        startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
                        finish()
                    } else {
                        result.success!!.errorMessage?.let { it1 -> ToastUtils.showMessage(application, it1) }
                    }
                }
                result.errorMessage != null -> {
                    result.errorMessage?.let { it1 -> ToastUtils.showMessage(application, it1) }
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
}
