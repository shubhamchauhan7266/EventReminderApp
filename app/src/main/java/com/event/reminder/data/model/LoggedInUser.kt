package com.event.reminder.data.model

import com.event.reminder.data.BaseModel

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    val userId: String,
    val displayName: String
) : BaseModel()
