package com.event.reminder.ui.dashboard.profile

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.viewmodel.BaseObservableViewModel
import com.event.reminder.data.model.response.UserDetails
import com.event.reminder.data.model.response.UserDetailsModel
import com.event.reminder.data.repository.ProfileDetailsRepository
import com.event.reminder.utills.EventReminderSharedPrefUtils

class ProfileDetailsViewModel(private val profileDetailsRepository: ProfileDetailsRepository) :
    BaseObservableViewModel() {

    private val _userDetailsResult: MutableLiveData<ApiResult<UserDetailsModel>> = MutableLiveData()
    val userDetails: MutableLiveData<UserDetails> = MutableLiveData()

    fun getUserDetailsApiResult(): LiveData<ApiResult<UserDetailsModel>> {
        // can be launched in a separate asynchronous job
        profileDetailsRepository.getUserDetails(EventReminderSharedPrefUtils.getUserId(), _userDetailsResult)
        return _userDetailsResult
    }
}