package com.event.reminder.data.model.request

import com.android.mvvmandroidlib.data.BaseRequestModel

/**
 * This data class is used as a request for Login API.
 *
 * @author Shubham Chauhan
 */
data class LoginRequest(
    var userName: String? = null,
    var password: String? = null
) : BaseRequestModel()