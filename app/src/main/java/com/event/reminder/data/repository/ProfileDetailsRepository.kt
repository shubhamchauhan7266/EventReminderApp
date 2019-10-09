package com.event.reminder.data.repository

import android.arch.lifecycle.MutableLiveData
import com.android.mvvmandroidlib.api.RequestNetworkManager
import com.android.mvvmandroidlib.api.SubscriptionCallback
import com.android.mvvmandroidlib.data.BaseResponseModel
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.repository.BaseRepository
import com.event.reminder.api.EventReminderApiHandler
import com.event.reminder.data.model.request.UserDetailsRequest
import com.event.reminder.data.model.response.UserDetailsModel

object ProfileDetailsRepository : BaseRepository() {

    fun getUserDetails(
        request: UserDetailsRequest,
        _loginResult: MutableLiveData<ApiResult<UserDetailsModel>>
    ) {
        try {

            RequestNetworkManager.addRequest(
                EventReminderApiHandler.getAPIHandler()?.getAPIClient()!!.getUserDetails(request),
                object : SubscriptionCallback<UserDetailsModel> {

                    override fun onSuccess(requestCode: Int, response: BaseResponseModel) {

                        if (response is UserDetailsModel) {
                            _loginResult.value = ApiResult(success = response)
                        } else {
                            _loginResult.value =
                                ApiResult(
                                    errorMessage = response.errorMessage,
                                    errorCode = response.statusCode
                                )
                        }
                    }

                    override fun onException(requestCode: Int, errCode: Int, errorMsg: String) {
                        _loginResult.value = ApiResult(errorMessage = errorMsg, errorCode = errCode)
                    }

                })

        } catch (e: Throwable) {
            _loginResult.value = ApiResult(errorMessage = e.localizedMessage)
        }
    }

}