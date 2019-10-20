package com.event.reminder.data.repository

import android.arch.lifecycle.MutableLiveData
import com.android.mvvmandroidlib.api.RequestNetworkManager
import com.android.mvvmandroidlib.api.SubscriptionCallback
import com.android.mvvmandroidlib.data.BaseResponseModel
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.repository.BaseRepository
import com.event.reminder.api.EventReminderApiHandler
import com.event.reminder.data.model.response.UserDetailsModel
import com.event.reminder.utills.EventReminderSharedPrefUtils

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
                        _userDetailsApiResult.value = ApiResult(errorMessage = errorMsg, errorCode = errCode)
                    }

                })

        } catch (e: Throwable) {
            _userDetailsApiResult.value = ApiResult(errorMessage = e.localizedMessage)
        }
    }

}