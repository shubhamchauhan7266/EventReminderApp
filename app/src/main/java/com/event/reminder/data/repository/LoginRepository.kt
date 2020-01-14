package com.event.reminder.data.repository

import androidx.lifecycle.MutableLiveData
import com.android.mvvmandroidlib.api.RequestNetworkManager
import com.android.mvvmandroidlib.api.SubscriptionCallback
import com.android.mvvmandroidlib.data.BaseResponseModel
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.repository.BaseRepository
import com.event.reminder.api.EventReminderApiHandler
import com.event.reminder.data.model.request.LoginRequest
import com.event.reminder.data.model.response.LoggedInUserModel

/**
 * This repository class is used to call API related to Login when network is available
 * to maintains an in-memory cache and fetch updated data from that.
 * When network is not available then it only get data from local database.
 *
 * @author Shubham Chauhan
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
