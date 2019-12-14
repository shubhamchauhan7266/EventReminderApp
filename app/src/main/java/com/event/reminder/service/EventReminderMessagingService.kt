package com.event.reminder.service

import android.graphics.Bitmap
import com.android.mvvmandroidlib.utills.LoggerUtils
import com.android.mvvmandroidlib.utills.StringUtils
import com.event.reminder.callback.IResultCallBack
import com.event.reminder.constant.BundleArgsConstant
import com.event.reminder.constant.ErrorConstant
import com.event.reminder.constant.NotificationType
import com.event.reminder.helper.BitmapFromImageUrlTask
import com.event.reminder.utills.AlarmUtils
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

                handleNotificationType(notificationType, remoteMessage.data)

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
            val title = remoteMessage.notification?.title ?: StringUtils.EMPTY
            val message = remoteMessage.notification?.body ?: StringUtils.EMPTY
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

    /**
     * Method is used to handle different types of notification.
     * @param notificationType
     * @param notificationData
     */
    private fun handleNotificationType(
        notificationType: Int,
        notificationData: Map<String, String>
    ) {

        LoggerUtils.debug(TAG, "handleNotificationType [Notification Type: $notificationType]")

        val title =
            notificationData[BundleArgsConstant.NOTIFICATION_TITLE] ?: StringUtils.EMPTY
        val message =
            notificationData[BundleArgsConstant.NOTIFICATION_MESSAGE] ?: StringUtils.EMPTY

        when (notificationType) {
            NotificationType.SILENT -> {

            }
            NotificationType.NORMAL -> {

                NotificationUtils.createNormalNotification(this, title, message)

            }
            NotificationType.BIG -> {

                val bigText = notificationData[BundleArgsConstant.NOTIFICATION_BIG_TEXT]
                    ?: StringUtils.EMPTY
                NotificationUtils.createBigNotification(this, title, message, bigText)

            }
            NotificationType.IMAGE -> {

                val imageUrl = notificationData[BundleArgsConstant.NOTIFICATION_IMAGE_URL]
                    ?: StringUtils.EMPTY
                BitmapFromImageUrlTask(object : IResultCallBack<Bitmap> {
                    override fun onSuccess(result: Bitmap) {
                        LoggerUtils.debug(TAG, "onSuccess")
                        NotificationUtils.createImageNotification(
                            this@EventReminderMessagingService
                            , title, message, result
                        )
                    }

                    override fun onFailure(error: String) {
                        LoggerUtils.debug(TAG, "onFailure [Error : $error]")
                    }
                }).execute(imageUrl)

            }
            NotificationType.FRIEND_REQUEST -> {

                val senderID = notificationData[BundleArgsConstant.NOTIFICATION_TITLE]
                    ?: StringUtils.EMPTY
                val receiverID = notificationData[BundleArgsConstant.NOTIFICATION_TITLE]
                    ?: StringUtils.EMPTY
                NotificationUtils.createFriendRequestNotification(
                    this,
                    title,
                    message,
                    senderID,
                    receiverID,
                    ""
                )

            }
            NotificationType.ALARM -> {

                val alarmTimeStamp: Long =
                    notificationData[BundleArgsConstant.ALARM_TIMESTAMP]?.toLong()
                        ?: ErrorConstant.INVALID_LONG
                if (alarmTimeStamp != ErrorConstant.INVALID_LONG) {
                    AlarmUtils.setAlarm(this, alarmTimeStamp)
                } else {
                    LoggerUtils.debug(TAG, "Time stamp is invalid")
                }

            }
            NotificationType.SCREEN_ACTION -> {

                NotificationUtils.createScreenActionNotification(
                    this,
                    title,
                    message,
                    ""
                )

            }
        }
    }
}
