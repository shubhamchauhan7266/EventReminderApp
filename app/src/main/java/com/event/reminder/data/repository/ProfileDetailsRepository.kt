package com.event.reminder.data.repository

import androidx.lifecycle.MutableLiveData
import com.android.mvvmandroidlib.api.RequestNetworkManager
import com.android.mvvmandroidlib.api.SubscriptionCallback
import com.android.mvvmandroidlib.data.BaseResponseModel
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.repository.BaseRepository
import com.event.reminder.api.EventReminderApiHandler
import com.event.reminder.data.model.request.GetFriendStatusRequest
import com.event.reminder.data.model.request.UpdateFriendStatusRequest
import com.event.reminder.data.model.request.UpdateUserDetailsRequest
import com.event.reminder.data.model.response.FriendStatusModel
import com.event.reminder.data.model.response.UserDetailsModel

/**
 * This repository class is used to call API related to User Profile when network is available
 * to maintains an in-memory cache and fetch updated data from that.
 * When network is not available then it only get data from local database.
 *
 * @author Shubham Chauhan
 */
object ProfileDetailsRepository : BaseRepository() {

    /**
     * Method is used to fetch User Details from database.
     * @param userId userId
     * @param _userDetailsApiResult LiveData object
     */
    fun getUserDetails(
        userId: String,
        _userDetailsApiResult: MutableLiveData<ApiResult<UserDetailsModel>>
    ) {
        try {

            EventReminderApiHandler.getAPIHandler()?.getAPIClient()?.getUserDetails(userId)?.let {
                RequestNetworkManager.addRequest(
                    it,
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
            }

        } catch (e: Throwable) {
            _userDetailsApiResult.value = ApiResult(errorMessage = e.localizedMessage)
        }
    }

    /**
     * Method is used to fetch friend status from database.
     * @param request GetFriendStatusRequest
     * @param _friendStatusApiResult LiveData object
     */
    fun getFriendStatus(
        request: GetFriendStatusRequest,
        _friendStatusApiResult: MutableLiveData<ApiResult<FriendStatusModel>>
    ) {
        try {

            EventReminderApiHandler.getAPIHandler()?.getAPIClient()?.getFriendStatus(request)?.let {
                RequestNetworkManager.addRequest(
                    it,
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
            }

        } catch (e: Throwable) {
            _friendStatusApiResult.value = ApiResult(errorMessage = e.localizedMessage)
        }
    }

    /**
     * Method is used to update user details to database.
     * @param request UpdateUserDetailsRequest
     * @param _updateUserDetailsResult LiveData object
     */
    fun updateUserDetails(
        request: UpdateUserDetailsRequest,
        _updateUserDetailsResult: MutableLiveData<ApiResult<BaseResponseModel>>
    ) {
        try {

            EventReminderApiHandler.getAPIHandler()?.getAPIClient()?.updateUserDetails(request)
                ?.let {
                    RequestNetworkManager.addRequest(
                        it,
                        object : SubscriptionCallback<BaseResponseModel> {

                            override fun onSuccess(requestCode: Int, response: BaseResponseModel) {
                                _updateUserDetailsResult.value = ApiResult(success = response)
                            }

                            override fun onException(
                                requestCode: Int,
                                errCode: Int,
                                errorMsg: String
                            ) {
                                _updateUserDetailsResult.value =
                                    ApiResult(errorMessage = errorMsg, errorCode = errCode)
                            }
                        })
                }

        } catch (e: Throwable) {
            _updateUserDetailsResult.value = ApiResult(errorMessage = e.localizedMessage)
        }
    }

    /**
     * Method is used to update friend status to database.
     * @param request UpdateFriendStatusRequest
     * @param _updateFriendStatusResult LiveData object
     */
    fun updateFriendStatus(
        request: UpdateFriendStatusRequest,
        _updateFriendStatusResult: MutableLiveData<ApiResult<FriendStatusModel>>
    ) {
        try {

            EventReminderApiHandler.getAPIHandler()?.getAPIClient()?.updateFriendStatus(request)
                ?.let {
                    RequestNetworkManager.addRequest(
                        it,
                        object : SubscriptionCallback<FriendStatusModel> {

                            override fun onSuccess(requestCode: Int, response: BaseResponseModel) {
                                if (response is FriendStatusModel) {
                                    _updateFriendStatusResult.value = ApiResult(success = response)
                                } else {
                                    _updateFriendStatusResult.value =
                                        ApiResult(
                                            errorMessage = response.errorMessage,
                                            errorCode = response.statusCode
                                        )
                                }
                            }

                            override fun onException(
                                requestCode: Int,
                                errCode: Int,
                                errorMsg: String
                            ) {
                                _updateFriendStatusResult.value =
                                    ApiResult(errorMessage = errorMsg, errorCode = errCode)
                            }
                        })
                }

        } catch (e: Throwable) {
            _updateFriendStatusResult.value = ApiResult(errorMessage = e.localizedMessage)
        }
    }
}