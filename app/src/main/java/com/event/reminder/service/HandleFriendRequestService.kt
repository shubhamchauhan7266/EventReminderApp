package com.event.reminder.service

import android.app.IntentService
import android.content.Intent
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

    override fun onHandleIntent(intent: Intent?) {

        val notification = NotificationUtils.createNotificationBuilder(
            this,
            getString(R.string.friend_request),
            getString(R.string.handle_friend_request_action)
        ).build()
        startForeground(AppConstant.FOREGROUND_NOTIFICATION_ID, notification)
    }
}
