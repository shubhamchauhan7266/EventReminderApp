package com.event.reminder.ui.login

import com.event.reminder.data.model.response.LoggedInUser

/**
 * Authentication result : success (user details) or errorCode message.
 */
data class LoginResult(
    val success: LoggedInUser? = null,
    val errorCode: Int = 101,
    val errorMessage: String? = null
)
