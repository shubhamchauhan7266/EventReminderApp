package com.event.reminder.data.model.request

import com.android.mvvmandroidlib.data.BaseRequestModel

data class LoginRequest(
    var userName: String? = null,
    var password: String? = null
) : BaseRequestModel()