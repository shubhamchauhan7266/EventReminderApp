package com.event.reminder.ui.dashboard.profile


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.mvvmandroidlib.ui.BaseFragment
import com.event.reminder.R
import com.event.reminder.constant.BundleArgsConstant
import com.event.reminder.constant.ErrorConstant
import com.event.reminder.databinding.ProfileDetailsFragmentBinding
import com.event.reminder.ui.ViewModelFactory

class ProfileDetailsFragment :
    BaseFragment<ProfileDetailsFragmentBinding, ProfileDetailsViewModel>() {

    override fun onCreateViewBinding(savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
        arguments?.let {
            viewModel.friendProfile = it.getBoolean(BundleArgsConstant.IS_FRIEND_PROFILE, false)
            viewModel.friendID =
                it.getString(BundleArgsConstant.FRIEND_ID, ErrorConstant.DEFAULT_USER_ID)
        }
    }

    override fun setInitialData() {
        super.setInitialData()

        if (viewModel.friendProfile == true) {
            viewModel.getFriendStatus()
        }
        viewModel.getUserDetailsApiResult().observe(this@ProfileDetailsFragment, Observer {
            val result = it ?: return@Observer

            when {
                result.success != null -> {

                    val userDetails = result.success
                    if (userDetails?.success == true) {
                        binding.userDetails = userDetails.userDetails
                    } else {
                        result.success?.errorMessage?.let { error ->
                            viewModel.failedEventErrorMessage.sendEvent(error)
                        }
                    }
                }
                result.errorMessage != null -> {
                    result.errorMessage?.let { error ->
                        viewModel.failedEventErrorMessage.sendEvent(error)
                    }
                }
            }
        })

        viewModel.updateUserDetailsResult.observe(this@ProfileDetailsFragment, Observer {
            val result = it ?: return@Observer

            when {
                result.success != null -> {
                    if (result.success?.success == true) {
                        viewModel.editableProfile = false
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

        viewModel.friendStatusResult.observe(this@ProfileDetailsFragment, Observer { result ->
            when {
                result.success != null -> {

                    val friendStatusModel = result.success
                    if (friendStatusModel?.success == true) {
                        viewModel.friendStatus = friendStatusModel.friendStatus
                        viewModel.actionUserId = friendStatusModel.actionUserId
                    } else {
                        result.success?.errorMessage?.let { error ->
                            viewModel.failedEventErrorMessage.sendEvent(error)
                        }
                    }
                }
                result.errorMessage != null -> {
                    result.errorMessage?.let { error ->
                        viewModel.failedEventErrorMessage.sendEvent(error)
                    }
                }
            }
        })
    }

    override fun getObservableViewModel(): ProfileDetailsViewModel {
        return ViewModelProviders.of(this, ViewModelFactory())
            .get(ProfileDetailsViewModel::class.java)
    }

    override fun getViewDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ProfileDetailsFragmentBinding {
        return DataBindingUtil.inflate(
            inflater, R.layout.fragment_profile_details, container, false
        )
    }
}
