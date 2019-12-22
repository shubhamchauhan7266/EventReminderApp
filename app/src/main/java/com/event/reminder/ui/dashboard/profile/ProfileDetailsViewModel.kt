package com.event.reminder.ui.dashboard.profile

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.viewmodel.BaseObservableViewModel
import com.event.reminder.BR
import com.event.reminder.data.model.request.GetFriendStatusRequest
import com.event.reminder.data.model.response.FriendStatusModel
import com.event.reminder.data.model.response.UserDetailsModel
import com.event.reminder.data.repository.ProfileDetailsRepository
import com.event.reminder.utills.EventReminderSharedPrefUtils

class ProfileDetailsViewModel(private val profileDetailsRepository: ProfileDetailsRepository) :
    BaseObservableViewModel() {

    private val _userDetailsResult: MutableLiveData<ApiResult<UserDetailsModel>> = MutableLiveData()
    private val _friendStatusResult: MutableLiveData<ApiResult<FriendStatusModel>> =
        MutableLiveData()

    var friendProfile: Boolean? = null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.friendProfile)
        }

    var friendStatus: Int? = null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.friendStatus)
        }

    fun getUserDetailsApiResult(): LiveData<ApiResult<UserDetailsModel>> {
        // can be launched in a separate asynchronous job
        profileDetailsRepository.getUserDetails(
            EventReminderSharedPrefUtils.getUserId(),
            _userDetailsResult
        )
        return _userDetailsResult
    }

    fun getFriendStatus(friendID: String): LiveData<ApiResult<FriendStatusModel>> {
        // can be launched in a separate asynchronous job
        profileDetailsRepository.getFriendStatus(
            GetFriendStatusRequest(
                userId = EventReminderSharedPrefUtils.getUserId(),
                friendId = friendID
            ),
            _friendStatusResult
        )
        return _friendStatusResult
    }
}