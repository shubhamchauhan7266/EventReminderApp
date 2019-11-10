package com.event.reminder.ui.otp

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
import com.event.reminder.databinding.OTPVerificationFragmentBinding
import com.event.reminder.ui.ViewModelFactory

class OTPVerificationFragment :
    BaseFragment<OTPVerificationFragmentBinding, OTPVerificationViewModel>() {

    private var iNavigationCallback: INavigationCallback? = null

    override fun onCreateViewBinding() {
        binding.viewModel = viewModel
    }

    override fun getObservableViewModel(): OTPVerificationViewModel {
        return ViewModelProviders.of(this, ViewModelFactory())
            .get(OTPVerificationViewModel::class.java)
    }

    override fun getViewDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): OTPVerificationFragmentBinding {
        return DataBindingUtil.inflate(
            inflater, R.layout.fragment_otpverification, container, false
        )
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        iNavigationCallback = context as INavigationCallback
    }

    override fun setInitialData() {
        super.setInitialData()

        viewModel.validateOTPResult!!.observe(this@OTPVerificationFragment, Observer {
            val result = it ?: return@Observer

            when {
                result.success != null -> {

                    if (result.success!!.success) {

                    } else {
                        result.success!!.errorMessage?.let { error -> viewModel.failedEventErrorMessage.sendEvent(error) }
                    }
                }
                result.errorMessage != null -> {
                    result.errorMessage?.let { error -> viewModel.failedEventErrorMessage.sendEvent(error)  }
                }
            }
        })

        viewModel.generateOTPResult!!.observe(this@OTPVerificationFragment, Observer {
            val result = it ?: return@Observer

            when {
                result.success != null -> {

                    if (result.success!!.success) {
                        iNavigationCallback?.navigateTo(NavigationConstant.LOGIN_SCREEN, null)
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
