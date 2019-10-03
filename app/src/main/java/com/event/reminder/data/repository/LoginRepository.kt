package com.event.reminder.data.repository

import android.arch.lifecycle.MutableLiveData
import com.android.mvvmandroidlib.api.RequestNetworkManager
import com.android.mvvmandroidlib.api.SubscriptionCallback
import com.android.mvvmandroidlib.data.BaseResponseModel
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.repository.BaseRepository
import com.event.reminder.api.EventReminderApiHandler
import com.event.reminder.data.model.request.LoginRequest
import com.event.reminder.data.model.response.LoggedInUser

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

object LoginRepository : BaseRepository() {

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
    }

    fun login(
        request: LoginRequest,
        _loginResult: MutableLiveData<ApiResult<LoggedInUser>>
    ) {
        // handle login
        try {

            RequestNetworkManager.addRequest(
                EventReminderApiHandler.getAPIHandler()?.getAPIClient()!!.login(request),
                object : SubscriptionCallback<LoggedInUser> {

                    override fun onSuccess(requestCode: Int, response: BaseResponseModel) {

                        if (response is LoggedInUser) {
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

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}
