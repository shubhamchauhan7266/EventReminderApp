package com.event.reminder.ui.dashboard.profile


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.mvvmandroidlib.ui.BaseFragment
import com.event.reminder.R
import com.event.reminder.databinding.ProfileDetailsFragmentBinding
import com.event.reminder.ui.ViewModelFactory

class ProfileDetailsFragment : BaseFragment<ProfileDetailsFragmentBinding, ProfileDetailsViewModel>() {
    override fun onCreateViewBinding(savedInstanceState: Bundle?) {
        binding.viewModel = viewModel

        viewModel.getUserDetailsApiResult().observe(this@ProfileDetailsFragment, Observer {
            val result = it ?: return@Observer

            when {
                result.success != null -> {

                    val userDetails = result.success
                    if (userDetails?.success == true) {
                        binding.userDetails = userDetails.userDetails
                    } else {
                        result.success!!.errorMessage?.let { error -> viewModel.failedEventErrorMessage.sendEvent(error)
                        }
                    }
                }
                result.errorMessage != null -> {
                    result.errorMessage?.let { error -> viewModel.failedEventErrorMessage.sendEvent(error)
                    }
                }
            }
        })
    }

    override fun getObservableViewModel(): ProfileDetailsViewModel {
        return ViewModelProviders.of(this, ViewModelFactory())
            .get(ProfileDetailsViewModel::class.java)
    }

    override fun getViewDataBinding(inflater: LayoutInflater, container: ViewGroup?): ProfileDetailsFragmentBinding {
        return DataBindingUtil.inflate(
            inflater, R.layout.fragment_profile_details, container, false
        )
    }
}
