package com.event.reminder.ui.dashboard.profile


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.mvvmandroidlib.ui.BaseFragment
import com.android.mvvmandroidlib.utills.ToastUtils
import com.event.reminder.R
import com.event.reminder.databinding.ProfileDetailsFragmentBinding
import com.event.reminder.ui.ViewModelFactory

class ProfileDetailsFragment : BaseFragment<ProfileDetailsFragmentBinding, ProfileDetailsViewModel>() {
    override fun onCreateViewBinding() {
        binding.viewModel = viewModel

        viewModel.getUserDetailsApiResult().observe(this@ProfileDetailsFragment, Observer {
            val result = it ?: return@Observer

            when {
                result.success != null -> {

                    val userDetails = result.success
                    if (userDetails?.success == true) {
                        viewModel.userDetails.value = userDetails.userDetails
                    } else {
                        result.success!!.errorMessage?.let { it1 ->
                            ToastUtils.showMessage(
                                activity?.application!!,
                                it1
                            )
                        }
                    }
                }
                result.errorMessage != null -> {
                    result.errorMessage?.let { it1 ->
                        ToastUtils.showMessage(
                            activity?.application!!,
                            it1
                        )
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
