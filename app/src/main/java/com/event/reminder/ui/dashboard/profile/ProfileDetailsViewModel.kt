package com.event.reminder.ui.dashboard.profile

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.mvvmandroidlib.data.BaseResponseModel
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.viewmodel.BaseObservableViewModel
import com.event.reminder.BR
import com.event.reminder.data.model.request.GetFriendStatusRequest
import com.event.reminder.data.model.request.UpdateFriendStatusRequest
import com.event.reminder.data.model.request.UpdateUserDetailsRequest
import com.event.reminder.data.model.response.FriendStatusModel
import com.event.reminder.data.model.response.UserDetailsModel
import com.event.reminder.data.repository.ProfileDetailsRepository
import com.event.reminder.utills.EventReminderSharedPrefUtils

class ProfileDetailsViewModel(private val profileDetailsRepository: ProfileDetailsRepository) :
    BaseObservableViewModel() {

    private val _updateUserDetailsResult: MutableLiveData<ApiResult<BaseResponseModel>> =
        MutableLiveData()
    private val _updateFriendStatusResult: MutableLiveData<ApiResult<BaseResponseModel>> =
        MutableLiveData()
    val updateFriendStatusResult: LiveData<ApiResult<BaseResponseModel>> = _updateFriendStatusResult
    private val _userDetailsResult: MutableLiveData<ApiResult<UserDetailsModel>> = MutableLiveData()
    private val _friendStatusResult: MutableLiveData<ApiResult<FriendStatusModel>> =
        MutableLiveData()

    var editableProfile: Boolean? = null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.editableProfile)
        }

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

    var fullName: String? = null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.fullName)
        }

    var emailId: String? = null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.emailId)
        }

    var dateOfBirth: String? = null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.dateOfBirth)
        }

    var gender: String? = null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.gender)
        }

    var phoneNumber: String? = null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.phoneNumber)
        }

    fun getUserDetailsApiResult(userId: String): LiveData<ApiResult<UserDetailsModel>> {
        // can be launched in a separate asynchronous job
        profileDetailsRepository.getUserDetails(
            userId,
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

    fun onEditClick() {
        editableProfile = true
    }

    fun onCancelClick() {
        editableProfile = false
    }

    fun updateProfile(): LiveData<ApiResult<BaseResponseModel>> {
        profileDetailsRepository.updateUserDetails(
            UpdateUserDetailsRequest(
                userId = EventReminderSharedPrefUtils.getUserId(),
                firstName = fullName,
                emailAddress = emailId,
                phoneNumber = phoneNumber,
                gender = gender,
                imageUrl = ""
            ),
            _updateUserDetailsResult
        )
        return _updateUserDetailsResult
    }

    fun updateFriendStatus(status: Int) {
        profileDetailsRepository.updateFriendStatus(
            UpdateFriendStatusRequest(
                firstUserId = "",
                secondUserId = "",
                actionUserId = "",
                friendStatus = status
            ),
            _updateFriendStatusResult
        )
    }
}