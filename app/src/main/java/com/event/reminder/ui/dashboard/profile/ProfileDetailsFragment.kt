package com.event.reminder.ui.dashboard.profile


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.mvvmandroidlib.ui.BaseFragment
import com.android.mvvmandroidlib.utills.StringUtils
import com.event.reminder.R
import com.event.reminder.databinding.ProfileDetailsFragmentBinding
import com.event.reminder.ui.ViewModelFactory
import com.event.reminder.utills.EventReminderSharedPrefUtils

class ProfileDetailsFragment :
    BaseFragment<ProfileDetailsFragmentBinding, ProfileDetailsViewModel>() {
    private var isFriendProfile: Boolean = false
    private var friendId: String? = null

    override fun onCreateViewBinding(savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
        arguments?.let {
            isFriendProfile = it.getBoolean(IS_FRIEND_PROFILE, false)
            friendId = it.getString(FRIEND_ID, null)
        }
        viewModel.friendProfile = isFriendProfile
    }

    override fun setInitialData() {
        super.setInitialData()

        val userId: String = if (isFriendProfile) {
            friendId ?: StringUtils.EMPTY
        } else {
            EventReminderSharedPrefUtils.getUserId()
        }
        viewModel.getUserDetailsApiResult(userId).observe(this@ProfileDetailsFragment, Observer {
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

        viewModel.updateProfile().observe(this@ProfileDetailsFragment, Observer {
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

        viewModel.updateFriendStatusResult.observe(this@ProfileDetailsFragment, Observer {
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

        if (isFriendProfile) {
            friendId?.let {
                viewModel.getFriendStatus(it)
                    .observe(this@ProfileDetailsFragment, Observer { result ->
                        when {
                            result.success != null -> {

                                val friendStatusModel = result.success
                                if (friendStatusModel?.success == true) {
                                    viewModel.friendStatus = friendStatusModel.friendStatus
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
        }
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

    companion object {
        private const val IS_FRIEND_PROFILE: String = "isFriendProfile"
        private const val FRIEND_ID: String = "friendId"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param isFriendProfile
         * @param friendId
         * @return A new instance of fragment ProfileDetailsFragment.
         */
        @JvmStatic
        fun newInstance(isFriendProfile: Boolean, friendId: String?) =
            ProfileDetailsFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(IS_FRIEND_PROFILE, isFriendProfile)
                    putString(FRIEND_ID, friendId)
                }
            }
    }
}
