package com.event.reminder.utills

import com.android.mvvmandroidlib.utills.LoggerUtils
import com.android.mvvmandroidlib.utills.SharedPreferencesUtils
import com.android.mvvmandroidlib.utills.StringUtils
import com.event.reminder.EventReminderApplication

object EventReminderSharedPrefUtils {

    private val TAG: String = EventReminderSharedPrefUtils::class.java.simpleName
    private const val FILE_NAME: String = "EventReminderSharedPreferences"
    private const val USER_LOGGED_IN: String = "userLoggedIn"
    private const val USER_ID: String = "userId"
    private const val ACCESS_TOKEN: String = "accessToken"

    fun isUserLoggedIn(): Boolean {

        LoggerUtils.info(TAG, "isUserLoggedIn")
        return SharedPreferencesUtils.getBoolean(
            EventReminderApplication.getInstance().applicationContext,
            USER_LOGGED_IN,
            fileName = FILE_NAME
        )
    }

    fun setUserLoggedIn(value: Boolean) {

        LoggerUtils.info(TAG, "setUserLoggedIn")
        SharedPreferencesUtils.setBoolean(
            EventReminderApplication.getInstance().applicationContext,
            USER_LOGGED_IN,
            value,
            fileName = FILE_NAME
        )
    }

    fun getUserId(): String {

        LoggerUtils.info(TAG, "getUserId")
        return SharedPreferencesUtils.getString(
            EventReminderApplication.getInstance().applicationContext,
            USER_ID,
            fileName = FILE_NAME
        ) ?: StringUtils.EMPTY
    }

    fun setUserId(value: String) {

        LoggerUtils.info(TAG, "setUserId")
        SharedPreferencesUtils.setString(
            EventReminderApplication.getInstance().applicationContext,
            USER_ID,
            value,
            fileName = FILE_NAME
        )
    }

    fun getAccessToken(): String {

        LoggerUtils.info(TAG, "getAccessToken")
        return SharedPreferencesUtils.getString(
            EventReminderApplication.getInstance().applicationContext,
            ACCESS_TOKEN,
            fileName = FILE_NAME
        ) ?: StringUtils.EMPTY
    }

    fun setAccessToken(value: String) {

        LoggerUtils.info(TAG, "setAccessToken")
        SharedPreferencesUtils.setString(
            EventReminderApplication.getInstance().applicationContext,
            ACCESS_TOKEN,
            value,
            fileName = FILE_NAME
        )
    }

}