package com.event.reminder.ui.login

import com.event.reminder.data.model.response.LoggedInUserModel

/**
 * Authentication result : success (userModel details) or errorCode message.
 */
data class LoginResult(
    val success: LoggedInUserModel? = null,
    val errorCode: Int = 101,
    val errorMessage: String? = null
)
