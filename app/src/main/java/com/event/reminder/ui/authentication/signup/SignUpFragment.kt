package com.event.reminder.ui.authentication.signup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.mvvmandroidlib.ui.BaseFragment
import com.event.reminder.R
import com.event.reminder.callback.INavigationCallback
import com.event.reminder.constant.BundleArgsConstant
import com.event.reminder.databinding.SignUpFragmentBinding
import com.event.reminder.enums.NavigationScreen
import com.event.reminder.ui.ViewModelFactory

/**
 * This Fragment Class is used to provide layout and lifecycle callback to handle UI for sign up screen.
 *
 * @author Shubham Chauhan
 */
class SignUpFragment : BaseFragment<SignUpFragmentBinding, SignUpViewModel>() {

    private var iNavigationCallback: INavigationCallback? = null

    override fun onCreateViewBinding(savedInstanceState: Bundle?) {
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        iNavigationCallback = context as INavigationCallback
    }

    override fun setInitialData() {
        super.setInitialData()

        viewModel.signUpResult?.observe(this@SignUpFragment, Observer {
            val result = it ?: return@Observer

            when {
                result.success != null -> {

                    if (result.success?.success == true) {
                        val bundle = Bundle()
                        bundle.putString(BundleArgsConstant.EMAIL_ID, viewModel.emailId)
                        bundle.putString(BundleArgsConstant.MOBILE_NUMBER, viewModel.phoneNumber)
                        iNavigationCallback?.navigateTo(
                            NavigationScreen.SIGN_UP_TO_OTP_SCREEN,
                            bundle
                        )
                    } else {
                        result.success?.errorMessage?.let { error ->
                            viewModel.failedEventErrorMessage.sendEvent(
                                error
                            )
                        }
                    }
                }
                result.errorMessage != null -> {
                    result.errorMessage?.let { error -> viewModel.failedEventErrorMessage.sendEvent(error)  }
                }
            }
        })
    }
}
