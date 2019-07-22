package com.event.reminder.ui.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.view.inputmethod.EditorInfo
import com.android.mvvmandroidlib.ui.BaseActivity
import com.event.reminder.R
import com.event.reminder.databinding.LoginActivityBinding
import com.event.reminder.ui.ViewModelFactory
import com.event.reminder.ui.dashboard.HomeActivity

class LoginActivity : BaseActivity<LoginActivityBinding, LoginViewModel>() {

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

        viewModel.loginResult.observe(this@LoginActivity, Observer {
            val loggedInUser = it ?: return@Observer

            if (loggedInUser.status) {
                startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                finish()
            } else {
                // TODO Show alert
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
