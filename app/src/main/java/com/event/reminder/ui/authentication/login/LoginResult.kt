package com.event.reminder.ui.authentication.login

import com.event.reminder.constant.ErrorConstant
import com.event.reminder.data.model.response.LoggedInUserModel

/**
 * Authentication result : success (userModel details) or errorCode message.
 */
data class LoginResult(
    val success: LoggedInUserModel? = null,
    val errorCode: Int = ErrorConstant.INVALID_NUMBER,
    val errorMessage: String? = null
)
