package com.event.reminder.data.repository

import androidx.lifecycle.MutableLiveData
import com.android.mvvmandroidlib.api.RequestNetworkManager
import com.android.mvvmandroidlib.api.SubscriptionCallback
import com.android.mvvmandroidlib.data.BaseResponseModel
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.repository.BaseRepository
import com.event.reminder.api.EventReminderApiHandler
import com.event.reminder.data.model.request.GetFriendStatusRequest
import com.event.reminder.data.model.request.UpdateUserDetailsRequest
import com.event.reminder.data.model.response.FriendStatusModel
import com.event.reminder.data.model.response.UserDetailsModel

object ProfileDetailsRepository : BaseRepository() {

    fun getUserDetails(
        userId: String,
        _userDetailsApiResult: MutableLiveData<ApiResult<UserDetailsModel>>
    ) {
        try {

            RequestNetworkManager.addRequest(
                EventReminderApiHandler.getAPIHandler()?.getAPIClient()!!.getUserDetails(userId),
                object : SubscriptionCallback<UserDetailsModel> {

                    override fun onSuccess(requestCode: Int, response: BaseResponseModel) {

                        if (response is UserDetailsModel) {
                            _userDetailsApiResult.value = ApiResult(success = response)
                        } else {
                            _userDetailsApiResult.value =
                                ApiResult(
                                    errorMessage = response.errorMessage,
                                    errorCode = response.statusCode
                                )
                        }
                    }

                    override fun onException(requestCode: Int, errCode: Int, errorMsg: String) {
                        _userDetailsApiResult.value =
                            ApiResult(errorMessage = errorMsg, errorCode = errCode)
                    }

                })

        } catch (e: Throwable) {
            _userDetailsApiResult.value = ApiResult(errorMessage = e.localizedMessage)
        }
    }

    fun getFriendStatus(
        request: GetFriendStatusRequest,
        _friendStatusApiResult: MutableLiveData<ApiResult<FriendStatusModel>>
    ) {
        try {

            RequestNetworkManager.addRequest(
                EventReminderApiHandler.getAPIHandler()?.getAPIClient()!!.getFriendStatus(request),
                object : SubscriptionCallback<FriendStatusModel> {

                    override fun onSuccess(requestCode: Int, response: BaseResponseModel) {

                        if (response is FriendStatusModel) {
                            _friendStatusApiResult.value = ApiResult(success = response)
                        } else {
                            _friendStatusApiResult.value =
                                ApiResult(
                                    errorMessage = response.errorMessage,
                                    errorCode = response.statusCode
                                )
                        }
                    }

                    override fun onException(requestCode: Int, errCode: Int, errorMsg: String) {
                        _friendStatusApiResult.value =
                            ApiResult(errorMessage = errorMsg, errorCode = errCode)
                    }
                })

        } catch (e: Throwable) {
            _friendStatusApiResult.value = ApiResult(errorMessage = e.localizedMessage)
        }
    }

    fun updateUserDetails(
        request: UpdateUserDetailsRequest,
        _updateUserDetailsResult: MutableLiveData<ApiResult<BaseResponseModel>>
    ) {
        try {

            RequestNetworkManager.addRequest(
                EventReminderApiHandler.getAPIHandler()?.getAPIClient()!!.updateUserDetails(request),
                object : SubscriptionCallback<FriendStatusModel> {

                    override fun onSuccess(requestCode: Int, response: BaseResponseModel) {
                        _updateUserDetailsResult.value = ApiResult(success = response)
                    }

                    override fun onException(requestCode: Int, errCode: Int, errorMsg: String) {
                        _updateUserDetailsResult.value =
                            ApiResult(errorMessage = errorMsg, errorCode = errCode)
                    }
                })

        } catch (e: Throwable) {
            _updateUserDetailsResult.value = ApiResult(errorMessage = e.localizedMessage)
        }
    }

}