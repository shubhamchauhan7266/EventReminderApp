package com.event.reminder.data.model.response

import com.android.mvvmandroidlib.data.BaseResponseModel
import java.io.Serializable

/**
 * Data class that captures userModel information for logged in users retrieved from LoginRepository
 */
data class LoggedInUserModel(
    val accessToken: String?,
    val userDetails: LoggedInUserDetails?
) : BaseResponseModel()

data class LoggedInUserDetails(
    val userId: String?,
    val firstName: String?,
    val lastName: String?,
    val emailAddress: String?,
    val phoneNumber: String?,
    val gender: String?  // TODO change gender in integer form.
) : Serializable
