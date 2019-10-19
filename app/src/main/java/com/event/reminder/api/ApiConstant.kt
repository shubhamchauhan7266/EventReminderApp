package com.event.reminder.api

import com.event.reminder.BuildConfig

object ApiConstant {

    private const val DEV_BASE_URL: String = "http://192.168.1.80:5555/api/"
    private const val PROD_BASE_URL : String = "http://api.event.reminder.com"

    const val LOGIN_API: String = "user/login"
    const val SIGN_UP_API: String = "user/signup"
    const val USER_DETAILS_API: String = "authenticatedUser/details"
    const val UPDATE_USER_DETAILS_API: String = "authenticatedUser/updateUser"
    const val NOTIFICATION_DETAILS_LIST_API: String = "authenticatedUser/notificationDetails"
    const val FRIEND_REQUEST_DETAILS_LIST_API: String = "authenticatedUser/friendRequestDetails"
    const val FRIEND_DETAILS_LIST_API: String = "authenticatedUser/friendDetails"

    const val USER_ID = "userId";

    var API_BASE_URL = if (BuildConfig.DEBUG) DEV_BASE_URL else PROD_BASE_URL
}