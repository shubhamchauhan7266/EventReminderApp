package com.event.reminder.ui.dashboard.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.mvvmandroidlib.ui.BaseFragment
import com.event.reminder.R
import com.event.reminder.databinding.CreateEventFragmentBinding
import com.event.reminder.ui.ViewModelFactory

/**
 * This Fragment Class is used to provide layout and lifecycle callback to handle UI for create event screen.
 *
 * @author Shubham Chauhan
 */
class CreateEventFragment : BaseFragment<CreateEventFragmentBinding, CreateEventViewModel>() {

    override fun onCreateViewBinding(savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
    }

    override fun getObservableViewModel(): CreateEventViewModel {
        return ViewModelProviders.of(this, ViewModelFactory())
            .get(CreateEventViewModel::class.java)
    }

    override fun getViewDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): CreateEventFragmentBinding {
        return DataBindingUtil.inflate(
            inflater, R.layout.fragment_create_event, container, false
        )
    }

    override fun setInitialData() {
        super.setInitialData()

        viewModel.createEventResult?.observe(this@CreateEventFragment, Observer {
            val result = it ?: return@Observer

            when {
                result.success != null -> {

                    if (result.success?.success == true) {
                        /*val bundle = Bundle()
                        bundle.putString(BundleArgsConstant.EMAIL_ID, viewModel.emailId)
                        bundle.putString(BundleArgsConstant.MOBILE_NUMBER, viewModel.phoneNumber)
                        iNavigationCallback?.navigateTo(
                            NavigationScreen.SIGN_UP_TO_OTP_SCREEN,
                            bundle
                        )*/
                    } else {
                        result.success?.errorMessage?.let { error ->
                            viewModel.failedEventErrorMessage.sendEvent(
                                error
                            )
                        }
                    }
                }
                result.errorMessage != null -> {
                    result.errorMessage?.let { error ->
                        viewModel.failedEventErrorMessage.sendEvent(
                            error
                        )
                    }
                }
            }
        })

        viewModel.updateEventResult?.observe(this@CreateEventFragment, Observer {
            val result = it ?: return@Observer

            when {
                result.success != null -> {

                    if (result.success?.success == true) {
                        /*val bundle = Bundle()
                        bundle.putString(BundleArgsConstant.EMAIL_ID, viewModel.emailId)
                        bundle.putString(BundleArgsConstant.MOBILE_NUMBER, viewModel.phoneNumber)
                        iNavigationCallback?.navigateTo(
                            NavigationScreen.SIGN_UP_TO_OTP_SCREEN,
                            bundle
                        )*/
                    } else {
                        result.success?.errorMessage?.let { error ->
                            viewModel.failedEventErrorMessage.sendEvent(
                                error
                            )
                        }
                    }
                }
                result.errorMessage != null -> {
                    result.errorMessage?.let { error ->
                        viewModel.failedEventErrorMessage.sendEvent(
                            error
                        )
                    }
                }
            }
        })
    }
}
