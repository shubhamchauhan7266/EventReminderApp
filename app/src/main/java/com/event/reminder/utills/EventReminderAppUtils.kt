package com.event.reminder.utills

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.android.mvvmandroidlib.utills.AlarmUtils
import com.android.mvvmandroidlib.utills.LoggerUtils
import com.event.reminder.constant.AppConstant
import com.event.reminder.receiver.AlarmReceiver

/**
 * This utils class is used to provide some common methods which is accessed by whole app.
 *
 * @author Shubham Chauhan
 */
object EventReminderAppUtils {
    private val TAG = EventReminderAppUtils::class.java.simpleName

    /**
     * Method is used to set alarm.
     */
    fun setAlarmForEvent(context: Context, alarmTimeStamp: Long) {
        LoggerUtils.debug(TAG, "setAlarmForEvent [alarmTimeStamp:$alarmTimeStamp]")
        // TODO Change Dashboard screen to Alarm Screen
        val alarmIntent = Intent(context, AlarmReceiver::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            AppConstant.ALARM_REQUEST,
            alarmIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        AlarmUtils.setExactAlarm(context, alarmTimeStamp, pendingIntent)
    }
}