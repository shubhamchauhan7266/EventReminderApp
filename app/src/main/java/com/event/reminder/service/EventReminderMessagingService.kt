package com.event.reminder.service

import com.android.mvvmandroidlib.utills.LoggerUtils
import com.event.reminder.constant.BundleArgsConstant
import com.event.reminder.constant.NotificationType
import com.event.reminder.utills.EventReminderSharedPrefUtils
import com.event.reminder.utills.NotificationUtils
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

/**
 * This service class is used to receive all type of notification from fire base server.
 *
 * @author Shubham Chauhan
 */
class EventReminderMessagingService : FirebaseMessagingService() {
    private val TAG: String = EventReminderMessagingService::class.java.simpleName

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        LoggerUtils.debug(TAG, "Message received From: " + remoteMessage.from)

        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            LoggerUtils.debug(TAG, "Message data payload: " + remoteMessage.data)

            if (remoteMessage.data.containsKey(BundleArgsConstant.NOTIFICATION_TYPE)) {
                val notificationType =
                    remoteMessage.data[BundleArgsConstant.NOTIFICATION_TYPE]?.toInt()
                        ?: NotificationType.SILENT

                LoggerUtils.debug(TAG, "Notification Type: $notificationType")
                when (notificationType) {
                    NotificationType.SILENT -> {

                    }
                    NotificationType.NORMAL -> {

                    }
                    NotificationType.BIG -> {

                    }
                    NotificationType.IMAGE -> {

                    }
                    NotificationType.FRIEND_REQUEST -> {

                    }
                    NotificationType.ALARM -> {

                    }
                    NotificationType.SCREEN_ACTION -> {

                    }
                }
            } else {
                LoggerUtils.debug(TAG, "No Notification Type is received")
            }
        }

        // Check if message contains a notification payload.
        if (remoteMessage.notification != null) {
            LoggerUtils.debug(
                TAG,
                "Message Notification Body: " + remoteMessage.notification?.body
            )
            val title = remoteMessage.notification?.title
            val message = remoteMessage.notification?.body
            NotificationUtils.createNormalNotification(this, title, message)
        }
    }

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    override fun onNewToken(token: String) {
        LoggerUtils.debug(TAG, "Refreshed token: $token")

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(token)
    }

    /**
     * Method is used to send refreshed token to server.
     */
    private fun sendRegistrationToServer(token: String) {
        LoggerUtils.info(TAG, "sendRegistrationToServer")
        EventReminderSharedPrefUtils.setRefreshedToken(token)
    }
}
