package com.event.reminder.data.model.response

import com.android.mvvmandroidlib.data.BaseResponseModel
import java.io.Serializable

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    val accessToken: String?,
    val userDetails: LoggedInUserDetails?
) : BaseResponseModel()

data class LoggedInUserDetails(
    val userId: String?,
    val userName: String?,
    val emailId: String?,
    val phoneNumber: String?
) : Serializable
