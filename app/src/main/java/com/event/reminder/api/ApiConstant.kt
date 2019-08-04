package com.event.reminder.api

import com.event.reminder.BuildConfig

object ApiConstant {

    private const val DEV_BASE_URL: String = "http://192.168.1.70:65420/Api/"
    private const val PROD_BASE_URL : String = "http://api.event.reminder.com"

    var API_BASE_URL = if (BuildConfig.DEBUG) DEV_BASE_URL else PROD_BASE_URL
}