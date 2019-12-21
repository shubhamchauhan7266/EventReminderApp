package com.android.mvvmandroidlib.utills

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build

/**
 * This util class is used to provide method to set and cancel alarm
 * This class is declared as object('Singleton').
 *
 * @author Shubham Chauhan
 */
object AlarmUtils {

    private val TAG = AlarmUtils::class.java.simpleName

    /**
     * Method is used to set alarm for important future events.
     * @param context
     * @param alarmTimeStamp
     */
    fun setExactAlarm(context: Context, alarmTimeStamp: Long, pendingIntent: PendingIntent) {
        LoggerUtils.debug(TAG, "setExactAlarm [Alarm TimeStamp : $alarmTimeStamp]")
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            LoggerUtils.info(TAG, "Called setAndAllowWhileIdle")
            alarmManager.setAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                alarmTimeStamp,
                pendingIntent
            )
        } else {
            LoggerUtils.info(TAG, "Called setExact")
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, alarmTimeStamp, pendingIntent)
        }
    }

    fun cancelNextAlarm(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val pendingIntent = alarmManager.nextAlarmClock.showIntent
        alarmManager.cancel(pendingIntent)
    }

    fun cancelAlarm(context: Context, pendingIntent: PendingIntent) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)
    }
}