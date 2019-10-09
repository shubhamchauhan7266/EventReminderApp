package com.event.reminder.api

import com.event.reminder.BuildConfig

object ApiConstant {

    private const val DEV_BASE_URL: String = "http://192.168.1.70:5555/api/"
    private const val PROD_BASE_URL : String = "http://api.event.reminder.com"

    const val LOGIN_API: String = "User/login"
    const val SIGN_UP_API: String = "User/signup"
    const val USER_DETAILS_API: String = "User/userDetails"

    var API_BASE_URL = if (BuildConfig.DEBUG) DEV_BASE_URL else PROD_BASE_URL
}