package com.event.reminder.api

import com.event.reminder.BuildConfig

object ApiConstant {

    // Base Url Constant
    private const val DEV_BASE_URL: String = "http://192.168.1.80:5555/api/"
    private const val PROD_BASE_URL : String = "http://api.event.reminder.com"
    var API_BASE_URL = if (BuildConfig.DEBUG) DEV_BASE_URL else PROD_BASE_URL


    // Timeout Constant
    const val NETWORK_TIMEOUT = 20L


    // Header Constant
    const val HEADER_ACCEPT = "Accept"
    const val HEADER_CONTENT_TYPE = "Content-Type"
    const val HEADER_APP_VERSION = "AppVersion"
    const val HEADER_API_VERSION = "ApiVersion"
    const val HEADER_AUTHORIZATION = "Authorization"
    const val HEADER_TYPE_APPLICATION_JSON = "application/json"


    // Api Constant
    const val LOGIN_API: String = "User/login"
    const val SIGN_UP_API: String = "User/signup"
    const val USER_DETAILS_API: String = "AuthenticatedUser/details"
    const val UPDATE_USER_DETAILS_API: String = "AuthenticatedUser/updateUser"
    const val NOTIFICATION_DETAILS_LIST_API: String = "Notification/notificationsList"
    const val FRIEND_REQUEST_DETAILS_LIST_API: String = "Friends/friendRequestDetailsList"
    const val FRIEND_DETAILS_LIST_API: String = "Friends/friendDetails"
    const val UPDATE_FRIEND_STATUS_API: String = "Friends/updateFriendStatus"
    const val GET_FRIEND_STATUS_API: String = "Friends/getFriendStatus"
    const val GENERATE_OTP_API: String = "AuthenticatedUser/generateOTP"
    const val VALIDATE_OTP_API: String = "AuthenticatedUser/validateOTP"


    // Query Constant
    const val BEARER = "Bearer "
    const val USER_ID = "userId"
}