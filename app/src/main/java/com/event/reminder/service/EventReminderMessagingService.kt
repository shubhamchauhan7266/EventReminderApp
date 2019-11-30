package com.event.reminder.service

import com.android.mvvmandroidlib.utills.LoggerUtils
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

/**
 * This service class is used to received notification from fire base server.
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

            /*if (*//* Check if data needs to be processed by long running job *//* true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                scheduleJob()
            } else {
                // Handle message within 10 seconds
                handleNow()
            }*/

        }

        // Check if message contains a notification payload.
        if (remoteMessage.notification != null) {
            LoggerUtils.debug(
                TAG,
                "Message Notification Body: " + remoteMessage.notification?.body
            )
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

    }
}
