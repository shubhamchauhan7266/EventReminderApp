package com.event.reminder.ui.authentication.signup

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.mvvmandroidlib.ui.BaseFragment
import com.event.reminder.R
import com.event.reminder.callback.INavigationCallback
import com.event.reminder.constant.NavigationConstant
import com.event.reminder.databinding.SignUpFragmentBinding
import com.event.reminder.ui.ViewModelFactory

class SignUpFragment : BaseFragment<SignUpFragmentBinding, SignUpViewModel>() {

    private var iNavigationCallback: INavigationCallback? = null

    override fun onCreateViewBinding() {
        binding.viewModel = viewModel
    }

    override fun getObservableViewModel(): SignUpViewModel {
        return ViewModelProviders.of(this, ViewModelFactory())
            .get(SignUpViewModel::class.java)
    }

    override fun getViewDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): SignUpFragmentBinding {
        return DataBindingUtil.inflate(
            inflater, R.layout.fragment_sign_up, container, false
        )
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        iNavigationCallback = context as INavigationCallback
    }

    override fun setInitialData() {
        super.setInitialData()

        viewModel.signUpResult!!.observe(this@SignUpFragment, Observer {
            val result = it ?: return@Observer

            when {
                result.success != null -> {

                    if (result.success!!.success) {
                        iNavigationCallback?.navigateTo(NavigationConstant.LOGIN_SCREEN)
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
