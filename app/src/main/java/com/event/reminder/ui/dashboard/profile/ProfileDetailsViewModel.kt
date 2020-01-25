package com.event.reminder.ui.dashboard.profile

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.mvvmandroidlib.data.BaseResponseModel
import com.android.mvvmandroidlib.exception.MathUtilParseException
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.utills.LoggerUtils
import com.android.mvvmandroidlib.utills.MathUtils
import com.android.mvvmandroidlib.utills.StringUtils
import com.android.mvvmandroidlib.viewmodel.BaseObservableViewModel
import com.event.reminder.BR
import com.event.reminder.constant.FriendStatus
import com.event.reminder.data.model.request.GetFriendStatusRequest
import com.event.reminder.data.model.request.UpdateFriendStatusRequest
import com.event.reminder.data.model.request.UpdateUserDetailsRequest
import com.event.reminder.data.model.response.FriendStatusModel
import com.event.reminder.data.model.response.UserDetailsModel
import com.event.reminder.data.repository.ProfileDetailsRepository
import com.event.reminder.utills.EventReminderSharedPrefUtils

class ProfileDetailsViewModel(private val profileDetailsRepository: ProfileDetailsRepository) :
    BaseObservableViewModel() {

    private val TAG: String = ProfileDetailsViewModel::class.java.simpleName

    private val _updateUserDetailsResult: MutableLiveData<ApiResult<BaseResponseModel>> =
        MutableLiveData()
    val updateUserDetailsResult: LiveData<ApiResult<BaseResponseModel>> = _updateUserDetailsResult
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

    var friendStatus: Int = FriendStatus.NOT_A_FRIEND
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.friendStatus)
        }

    var friendID: String? = null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.friendID)
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

    fun getUserDetailsApiResult(): LiveData<ApiResult<UserDetailsModel>> {
        // can be launched in a separate asynchronous job
        val userId: String = if (friendProfile == true) {
            friendID ?: StringUtils.EMPTY
        } else {
            EventReminderSharedPrefUtils.getUserId()
        }
        profileDetailsRepository.getUserDetails(
            userId,
            _userDetailsResult
        )
        return _userDetailsResult
    }

    fun getFriendStatus(): LiveData<ApiResult<FriendStatusModel>> {
        // can be launched in a separate asynchronous job
        profileDetailsRepository.getFriendStatus(
            GetFriendStatusRequest(
                userId = EventReminderSharedPrefUtils.getUserId(),
                friendId = friendID ?: StringUtils.EMPTY
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

    fun updateProfile() {
        profileDetailsRepository.updateUserDetails(
            UpdateUserDetailsRequest(
                userId = EventReminderSharedPrefUtils.getUserId(),
                firstName = fullName,
                emailAddress = emailId,
                phoneNumber = phoneNumber,
                gender = gender,
                imageUrl = "",
                postalCode = "758588"
            ),
            _updateUserDetailsResult
        )
    }

    fun updateFriendStatus() {
        try {
            profileDetailsRepository.updateFriendStatus(
                UpdateFriendStatusRequest(
                    firstUserId = MathUtils.getMin(
                        EventReminderSharedPrefUtils.getUserId(),
                        friendID ?: StringUtils.EMPTY
                    ),
                    secondUserId = MathUtils.getMax(
                        EventReminderSharedPrefUtils.getUserId(),
                        friendID ?: StringUtils.EMPTY
                    ),
                    actionUserId = EventReminderSharedPrefUtils.getUserId(),
                    friendStatus = friendStatus ?: FriendStatus.NOT_A_FRIEND
                ),
                _updateFriendStatusResult
            )
        } catch (e: MathUtilParseException) {
            LoggerUtils.debug(TAG, LoggerUtils.getStackTraceString(e))
            failedEventErrorMessage.sendEvent(e.localizedMessage)
        }
    }
}