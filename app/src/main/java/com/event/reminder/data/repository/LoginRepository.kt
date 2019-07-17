package com.event.reminder.data.repository

import com.android.mvvmandroidlib.common.Result
import com.android.mvvmandroidlib.repository.BaseRepository
import com.event.reminder.data.model.response.LoggedInUser
import java.util.*

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

    fun login(username: String, password: String): Result<LoggedInUser> {
        // handle login
        var result : Result<LoggedInUser>
        try {
            // TODO: handle loggedInUser authentication
            val fakeUser =
                LoggedInUser(UUID.randomUUID().toString(), "Jane Doe")
            fakeUser.status = true
            result =  Result.Success(fakeUser)
        } catch (e: Throwable) {
            result = Result.Error("Error : $e")
        }

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}
