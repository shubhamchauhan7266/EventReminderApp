package com.event.reminder.utills

import com.android.mvvmandroidlib.utills.LoggerUtils
import com.android.mvvmandroidlib.utills.SharedPreferencesUtils
import com.android.mvvmandroidlib.utills.StringUtils
import com.event.reminder.EventReminderApplication

/**
 * This util class is used to provide methods to store and read data by SharedPreference.
 * It will store data in app local data. It only store primitive type data.
 * This class is declared as object('Singleton').
 *
 * @author Shubham Chauhan
 */
object EventReminderSharedPrefUtils {

    private val TAG: String = EventReminderSharedPrefUtils::class.java.simpleName
    private const val FILE_NAME: String = "EventReminderSharedPreferences"
    private const val USER_LOGGED_IN: String = "userLoggedIn"
    private const val USER_ID: String = "userId"
    private const val ACCESS_TOKEN: String = "accessToken"
    private const val MOBILE_NUMBER: String = "mobileNumber"
    private const val REFRESHED_TOKEN: String = "refreshedToken"

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

    fun getMobileNumber(): String {

        LoggerUtils.info(TAG, "getMobileNumber")
        return SharedPreferencesUtils.getString(
            EventReminderApplication.getInstance().applicationContext,
            MOBILE_NUMBER,
            fileName = FILE_NAME
        ) ?: StringUtils.EMPTY
    }

    fun setMobileNumber(value: String) {

        LoggerUtils.info(TAG, "setMobileNumber")
        SharedPreferencesUtils.setString(
            EventReminderApplication.getInstance().applicationContext,
            MOBILE_NUMBER,
            value,
            fileName = FILE_NAME
        )
    }

    fun getRefreshedToken(): String {

        LoggerUtils.info(TAG, "getRefreshedToken")
        return SharedPreferencesUtils.getString(
            EventReminderApplication.getInstance().applicationContext,
            REFRESHED_TOKEN,
            fileName = FILE_NAME
        ) ?: StringUtils.EMPTY
    }

    fun setRefreshedToken(value: String) {

        LoggerUtils.info(TAG, "setRefreshedToken")
        SharedPreferencesUtils.setString(
            EventReminderApplication.getInstance().applicationContext,
            REFRESHED_TOKEN,
            value,
            fileName = FILE_NAME
        )
    }
}