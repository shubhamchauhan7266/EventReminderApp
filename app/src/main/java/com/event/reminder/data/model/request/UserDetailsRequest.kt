package com.event.reminder.data.model.request

import java.io.Serializable

data class UserDetailsRequest(
    var accessToken: String?,
    var userId: String?
) : Serializable