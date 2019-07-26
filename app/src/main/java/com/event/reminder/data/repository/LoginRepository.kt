package com.event.reminder.data.repository

import com.android.mvvmandroidlib.api.RequestNetworkManager
import com.android.mvvmandroidlib.api.SubscriptionCallback
import com.android.mvvmandroidlib.common.Result
import com.android.mvvmandroidlib.data.BaseErrorModel
import com.android.mvvmandroidlib.data.BaseResponseModel
import com.android.mvvmandroidlib.repository.BaseRepository
import com.event.reminder.api.EventReminderApiHandler
import com.event.reminder.data.model.EventErrorModel
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

    fun login(username: String, password: String): Result<LoggedInUser>? {
        // handle login
        var result: Result<LoggedInUser>? = null
        try {

            RequestNetworkManager.addRequest(
                100,
                EventReminderApiHandler.getAPIHandler()?.getAPIClient()!!.logout(),
                EventErrorModel::class.java,
                object : SubscriptionCallback<LoggedInUser, EventErrorModel> {
                    override fun onServerError(requestCode: Int, response: BaseErrorModel) {

                    }

                    override fun onSuccess(requestCode: Int, response: BaseResponseModel) {

                        if (response is LoggedInUser)
                            result = Result.Success(response)
                    }

                    override fun onException(requestCode: Int, errCode: Int, errorMsg: String) {
                    }

                })
//            val fakeUser =
//                LoggedInUser(UUID.randomUUID().toString(), "Jane Doe")
//            fakeUser.status = true
//            result =  Result.Success(fakeUser)
        } catch (e: Throwable) {
            result = Result.Error("Error : $e")
        }

//        if (result is Result.Success) {
//            setLoggedInUser(result.data)
//        }

        return result
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}
