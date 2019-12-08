package com.event.reminder.service

import android.app.IntentService
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import com.android.mvvmandroidlib.utills.LoggerUtils
import com.event.reminder.R
import com.event.reminder.constant.AppConstant
import com.event.reminder.utills.NotificationUtils

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * this service handle friend request action like accept, reject etc...
 *
 * @author Shubham Chauhan
 */
class HandleFriendRequestService : IntentService("HandleFriendRequestService") {
    private val TAG: String = HandleFriendRequestService::class.java.simpleName

    override fun onHandleIntent(intent: Intent?) {
        LoggerUtils.info(TAG, "HandleFriendRequestService started")

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(AppConstant.FRIEND_REQUEST_NOTIFICATION_REQUEST)

        val notification = NotificationUtils.createNotificationBuilder(
            this,
            getString(R.string.friend_request),
            getString(R.string.handle_friend_request_action)
        ).build()
        startForeground(AppConstant.FOREGROUND_NOTIFICATION_ID, notification)

        updateStatusOfFriendRequest(intent)
    }

    private fun updateStatusOfFriendRequest(intent: Intent?) {
        LoggerUtils.info(TAG, "updateStatusOfFriendRequest")
        when (intent?.action) {

            getString(R.string.accept) -> {
                LoggerUtils.info(TAG, "handle accept action")
            }
            getString(R.string.reject) -> {
                LoggerUtils.info(TAG, "handle reject action")
            }
            else -> {
                LoggerUtils.info(TAG, getString(R.string.unknown_case))
            }
        }
    }
}
