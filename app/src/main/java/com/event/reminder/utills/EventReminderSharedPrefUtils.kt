package com.event.reminder.utills

import com.android.mvvmandroidlib.utills.LoggerUtils
import com.android.mvvmandroidlib.utills.SharedPreferencesUtils
import com.event.reminder.EventReminderApplication

object EventReminderSharedPrefUtils {

    private val TAG: String = EventReminderSharedPrefUtils::class.java.simpleName
    private const val FILE_NAME: String = "EventReminderSharedPreferences"
    private const val USER_LOGGED_IN: String = "userLoggedIn"

    fun isUserLoggedIn(): Boolean {

        LoggerUtils.info(TAG, "isUserLoggedIn")
        return SharedPreferencesUtils.getBoolean(
            EventReminderApplication.getInstance(),
            USER_LOGGED_IN,
            fileName = FILE_NAME
        )
    }

    fun setUserLoggedIn(value: Boolean) {

        LoggerUtils.info(TAG, "setUserLoggedIn")
        SharedPreferencesUtils.setBoolean(
            EventReminderApplication.getInstance(),
            USER_LOGGED_IN,
            value,
            fileName = FILE_NAME
        )
    }
}