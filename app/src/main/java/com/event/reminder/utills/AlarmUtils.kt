package com.event.reminder.utills

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.android.mvvmandroidlib.utills.LoggerUtils
import com.event.reminder.constant.AppConstant
import com.event.reminder.ui.dashboard.DashboardActivity

object AlarmUtils {

    private val TAG = AlarmUtils::class.java.simpleName

    /**
     * Method is used to set alarm for important future events. It will open an Alarm Screen.
     * @param context
     * @param alarmTimeStamp
     */
    fun setAlarm(context: Context, alarmTimeStamp: Long) {
        LoggerUtils.debug(TAG, "setAlarm [Alarm TimeStamp : $alarmTimeStamp]")
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        // TODO Change Dashboard screen to Alarm Screen
        val alarmIntent = Intent(context, DashboardActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            AppConstant.ALARM_REQUEST,
            alarmIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

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