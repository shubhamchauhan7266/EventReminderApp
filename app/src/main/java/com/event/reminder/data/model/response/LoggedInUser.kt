package com.event.reminder.data.model.response

import com.android.mvvmandroidlib.data.BaseModel

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    val userId: String,
    val displayName: String
) : BaseModel()
