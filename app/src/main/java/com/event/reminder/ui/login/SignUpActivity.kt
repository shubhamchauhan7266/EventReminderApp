package com.event.reminder.ui.login

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import com.android.mvvmandroidlib.ui.BaseActivity
import com.event.reminder.R
import com.event.reminder.databinding.SignUpActivityBinding
import com.event.reminder.ui.ViewModelFactory

class SignUpActivity : BaseActivity<SignUpActivityBinding, SignUpViewModel>() {

    override fun onCreateBinding() {
    }

    override fun getObservableViewModel(): SignUpViewModel {
        return ViewModelProviders.of(this, ViewModelFactory())
            .get(SignUpViewModel::class.java)
    }

    override fun getViewDataBinding(): SignUpActivityBinding {
        return DataBindingUtil.setContentView(this, R.layout.sign_up_activity)
    }

}
