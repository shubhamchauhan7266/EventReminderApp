package com.event.reminder.ui.dashboard.profile

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.mvvmandroidlib.data.BaseResponseModel
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.viewmodel.BaseObservableViewModel
import com.event.reminder.BR
import com.event.reminder.constant.ErrorConstant
import com.event.reminder.constant.FriendStatus
import com.event.reminder.data.model.request.GetFriendStatusRequest
import com.event.reminder.data.model.request.UpdateFriendStatusRequest
import com.event.reminder.data.model.request.UpdateUserDetailsRequest
import com.event.reminder.data.model.response.FriendStatusModel
import com.event.reminder.data.model.response.UserDetailsModel
import com.event.reminder.data.repository.ProfileDetailsRepository
import com.event.reminder.utills.EventReminderSharedPrefUtils

/**
 * This ViewModel class is worked as a Observable ViewModel that is responsible for preparing and managing
 * the data for an [android.app.Activity] or a [androidx.fragment.app.Fragment].
 * It also handles the communication of the Activity / Fragment with the rest of the application
 * (e.g. calling the business logic classes).
 *
 * @author Shubham Chauhan
 */
class ProfileDetailsViewModel(private val profileDetailsRepository: ProfileDetailsRepository) :
    BaseObservableViewModel() {

    private val TAG: String = ProfileDetailsViewModel::class.java.simpleName

    private val _updateUserDetailsResult: MutableLiveData<ApiResult<BaseResponseModel>> =
        MutableLiveData()
    val updateUserDetailsResult: LiveData<ApiResult<BaseResponseModel>> = _updateUserDetailsResult
    private val _userDetailsResult: MutableLiveData<ApiResult<UserDetailsModel>> = MutableLiveData()
    private val _friendStatusResult: MutableLiveData<ApiResult<FriendStatusModel>> =
        MutableLiveData()
    val friendStatusResult: LiveData<ApiResult<FriendStatusModel>> = _friendStatusResult

    var editableProfile: Boolean = false
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.editableProfile)
        }

    var friendProfile: Boolean = false
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

    var actionUserId: String = ErrorConstant.DEFAULT_USER_ID
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.actionUserId)
        }

    var friendID: String = ErrorConstant.DEFAULT_USER_ID
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

    /**
     * Method is used to fetch User Details.
     * @return LiveData object to observe user details.
     */
    fun getUserDetailsApiResult(): LiveData<ApiResult<UserDetailsModel>> {
        // can be launched in a separate asynchronous job
        val userId: String = if (friendProfile) {
            friendID
        } else {
            EventReminderSharedPrefUtils.getUserId()
        }
        profileDetailsRepository.getUserDetails(
            userId,
            _userDetailsResult
        )
        return _userDetailsResult
    }

    /**
     * Method is used to get Friend Status.
     */
    fun getFriendStatus() {
        // can be launched in a separate asynchronous job
        profileDetailsRepository.getFriendStatus(
            GetFriendStatusRequest(
                userId = EventReminderSharedPrefUtils.getUserId(),
                friendId = friendID
            ),
            _friendStatusResult
        )
    }

    /**
     * Action performed by click on edit button.
     */
    fun onEditClick() {
        editableProfile = true
    }

    /**
     * Action performed by click on cancel button.
     */
    fun onCancelClick() {
        editableProfile = false
    }

    /**
     * Method is used to update User Details.
     */
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

    /**
     * Method is used to update Friend Status.
     * @param isFriendStatus
     */
    fun updateFriendStatus(isFriendStatus: Boolean) {
        profileDetailsRepository.updateFriendStatus(
            UpdateFriendStatusRequest(
                userId = EventReminderSharedPrefUtils.getUserId(),
                friendId = friendID,
                actionUserId = EventReminderSharedPrefUtils.getUserId(),
                friendStatus = friendStatus  // TODO need to use updated friend status
            ),
            _friendStatusResult
        )
    }
}