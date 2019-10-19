package com.event.reminder.data.repository

import android.arch.lifecycle.MutableLiveData
import com.android.mvvmandroidlib.api.RequestNetworkManager
import com.android.mvvmandroidlib.api.SubscriptionCallback
import com.android.mvvmandroidlib.data.BaseResponseModel
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.repository.BaseRepository
import com.event.reminder.api.EventReminderApiHandler
import com.event.reminder.data.model.request.LoginRequest
import com.event.reminder.data.model.response.LoggedInUserModel

/**
 * Class that requests authentication and userModel information from the remote data source and
 * maintains an in-memory cache of login status and userModel credentials information.
 */

object LoginRepository : BaseRepository() {

    fun login(
        request: LoginRequest,
        _loginResult: MutableLiveData<ApiResult<LoggedInUserModel>>
    ) {
        // handle login
        try {

            RequestNetworkManager.addRequest(
                EventReminderApiHandler.getAPIHandler()?.getAPIClient()!!.login(request),
                object : SubscriptionCallback<LoggedInUserModel> {

                    override fun onSuccess(requestCode: Int, response: BaseResponseModel) {

                        if (response is LoggedInUserModel) {
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
