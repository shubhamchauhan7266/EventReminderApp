package com.event.reminder.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.android.mvvmandroidlib.utills.LoggerUtils
import com.event.reminder.R
import com.event.reminder.constant.AppConstant
import com.event.reminder.constant.BundleArgsConstant
import com.event.reminder.enums.NotificationType
import com.event.reminder.ui.authentication.AuthenticationActivity
import com.event.reminder.ui.dashboard.DashboardActivity
import com.event.reminder.utills.EventReminderSharedPrefUtils
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
                        ?: NotificationType.SILENT.ordinal

                LoggerUtils.debug(TAG, "Notification Type: $notificationType")
                when (notificationType) {
                    NotificationType.SILENT.ordinal -> {

                    }
                    NotificationType.NORMAL.ordinal -> {

                    }
                    NotificationType.BIG.ordinal -> {

                    }
                    NotificationType.IMAGE.ordinal -> {

                    }
                    NotificationType.FRIEND_REQUEST.ordinal -> {

                    }
                    NotificationType.ALARM.ordinal -> {

                    }
                    NotificationType.SCREEN_ACTION.ordinal -> {

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
            createNormalNotification(title, message)
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
     * This method is used to create a notification builder.
     * @param title
     * @param body
     */
    private fun createNotificationBuilder(
        title: String?,
        body: String?
    ): NotificationCompat.Builder {

        return NotificationCompat.Builder(this, AppConstant.CHANNEL_ID)
            .setSmallIcon(R.drawable.profile)
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
    }

    /**
     * Method is used to send normal notification.
     * @param title
     * @param message
     */
    private fun createNormalNotification(title: String?, message: String?) {

        val notificationBuilder = createNotificationBuilder(title, message)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel(notificationManager)

        notificationManager.notify(
            AppConstant.NOTIFICATION_REQUEST,
            notificationBuilder.build()
        )
    }

    /**
     * Method is used to send big notification.
     * @param title
     * @param message
     */
    fun createBigNotification(title: String?, message: String?, bigText: String) {

        val notificationBuilder = createNotificationBuilder(title, message)
        notificationBuilder.setStyle(
            NotificationCompat.BigTextStyle()
                .bigText(bigText)
        )
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel(notificationManager)

        notificationManager.notify(
            AppConstant.NOTIFICATION_REQUEST,
            notificationBuilder.build()
        )
    }

    /**
     * Method is used to send normal notification.
     * @param title
     * @param message
     * @param screenName
     */
    fun createScreenActionNotification(title: String?, message: String?, screenName: String) {

        val intent = getIntentAsPerScreenRequest(screenName)
        val pendingIntent = PendingIntent.getActivity(
            this, AppConstant.NOTIFICATION_REQUEST,
            intent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        val notificationBuilder = createNotificationBuilder(title, message)
        notificationBuilder.setContentIntent(pendingIntent)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel(notificationManager)

        notificationManager.notify(
            AppConstant.NOTIFICATION_REQUEST,
            notificationBuilder.build()
        )
    }

    /**
     * Method is used to get intent as per screen name to open screen after click notification.
     * @param screenName
     */
    private fun getIntentAsPerScreenRequest(screenName: String): Intent? {

        return when (screenName) {
            AppConstant.HOME_SCREEN -> {
                Intent(this, DashboardActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
            }
            else -> {
                Intent(this, AuthenticationActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
            }
        }
    }

    /**
     * Method is used to create notification channel.
     * @param notificationManager
     */
    private fun createNotificationChannel(notificationManager: NotificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(AppConstant.CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

    /**
     * This method is used to create a notification for friend request
     * @param title
     * @param message
     * @param senderId
     * @param receiverId
     */
    private fun createFriendRequestNotification(
        title: String?,
        message: String,
        senderId: String,
        receiverId: String,
        screenName: String
    ) {

        val acceptIntent = Intent(this, HandleFriendRequestService::class.java)
        acceptIntent.action = getString(R.string.accept)
        acceptIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        acceptIntent.putExtra(BundleArgsConstant.SENDER_USER_ID_KEY, senderId)
        acceptIntent.putExtra(BundleArgsConstant.RECEIVER_USER_ID_KEY, receiverId)

        val rejectIntent = Intent(this, HandleFriendRequestService::class.java)
        rejectIntent.action = getString(R.string.reject)
        rejectIntent.putExtra(BundleArgsConstant.SENDER_USER_ID_KEY, senderId)
        rejectIntent.putExtra(BundleArgsConstant.RECEIVER_USER_ID_KEY, receiverId)
        rejectIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val expendView = RemoteViews(packageName, R.layout.friend_request_notification_view)
        expendView.setTextViewText(R.id.tv_message, message)
        expendView.setOnClickPendingIntent(
            R.id.bt_accept, PendingIntent.getService(
                this, AppConstant.FRIEND_REQUEST_NOTIFICATION_REQUEST,
                acceptIntent, PendingIntent.FLAG_UPDATE_CURRENT
            )
        )
        expendView.setOnClickPendingIntent(
            R.id.bt_reject, PendingIntent.getService(
                this, AppConstant.FRIEND_REQUEST_NOTIFICATION_REQUEST,
                rejectIntent, PendingIntent.FLAG_UPDATE_CURRENT
            )
        )

        val intent = getIntentAsPerScreenRequest(screenName)
        val pendingIntent = PendingIntent.getActivity(
            this, AppConstant.FRIEND_REQUEST_NOTIFICATION_REQUEST,
            intent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notificationBuilder = createNotificationBuilder(title, message)
        notificationBuilder.setContentIntent(pendingIntent)
            .setCustomBigContentView(expendView)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(
            AppConstant.FRIEND_REQUEST_NOTIFICATION_REQUEST,
            notificationBuilder.build()
        )
    }
}
