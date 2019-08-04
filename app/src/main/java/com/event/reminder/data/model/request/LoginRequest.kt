package com.event.reminder.data.model.request

import java.io.Serializable

data class LoginRequest(
    var userName: String? = null,
    var password: String? = null
) : Serializable