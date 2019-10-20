package com.event.reminder.ui.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import com.android.mvvmandroidlib.ui.BaseActivity
import com.android.mvvmandroidlib.utills.ToastUtils
import com.event.reminder.R
import com.event.reminder.databinding.SignUpActivityBinding
import com.event.reminder.ui.ViewModelFactory

class SignUpActivity : BaseActivity<SignUpActivityBinding, SignUpViewModel>() {

    override fun onCreateBinding() {
        binding.viewModel = viewModel
    }

    override fun getObservableViewModel(): SignUpViewModel {
        return ViewModelProviders.of(this, ViewModelFactory())
            .get(SignUpViewModel::class.java)
    }

    override fun getViewDataBinding(): SignUpActivityBinding {
        return DataBindingUtil.setContentView(this, R.layout.sign_up_activity)
    }

    override fun initMembers() {
        super.initMembers()

        viewModel.signUpResult!!.observe(this@SignUpActivity, Observer {
            val result = it ?: return@Observer

            when {
                result.success != null -> {

                    if (result.success!!.success) {

                        startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
                        finish()
                    } else {
                        result.success!!.errorMessage?.let { error -> viewModel.failedEventErrorMessage.sendEvent(error) }
                    }
                }
                result.errorMessage != null -> {
                    result.errorMessage?.let { error -> viewModel.failedEventErrorMessage.sendEvent(error)  }
                }
            }
        })
    }
}
