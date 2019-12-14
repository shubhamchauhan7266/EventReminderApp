package com.event.reminder.utills

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.android.mvvmandroidlib.utills.LoggerUtils
import com.event.reminder.R
import com.event.reminder.constant.AppConstant
import com.event.reminder.constant.BundleArgsConstant
import com.event.reminder.service.HandleFriendRequestService
import com.event.reminder.ui.authentication.AuthenticationActivity
import com.event.reminder.ui.dashboard.DashboardActivity

/**
 * This utils class is used to create notification.
 *
 * @author Shubham Chauhan
 */
object NotificationUtils {
    private val TAG = NotificationUtils::class.java.simpleName

    /**
     * This method is used to create a notification builder.
     * @param context
     * @param title
     * @param message
     */
    fun createNotificationBuilder(
        context: Context,
        title: String?,
        message: String?
    ): NotificationCompat.Builder {

        LoggerUtils.debug(
            TAG,
            "createNotificationBuilder [Title : $title, Message : $message]"
        )
        return NotificationCompat.Builder(context, AppConstant.CHANNEL_ID)
            .setSmallIcon(R.drawable.profile)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
    }

    /**
     * Method is used to get intent as per screen name to open screen after click notification.
     * @param screenName
     */
    private fun getIntentAsPerScreenRequest(context: Context, screenName: String): Intent? {
        LoggerUtils.debug(
            TAG,
            "getIntentAsPerScreenRequest [ScreenName : $screenName]"
        )
        return when (screenName) {
            AppConstant.HOME_SCREEN -> {
                Intent(context, DashboardActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
            }
            else -> {
                Intent(context, AuthenticationActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
            }
        }
    }

    /**
     * Method is used to create notification channel.
     * @param notificationManager
     */
    private fun createNotificationChannel(
        context: Context,
        notificationManager: NotificationManager
    ) {
        LoggerUtils.info(
            TAG,
            "createNotificationChannel"
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.channel_name)
            val descriptionText = context.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(AppConstant.CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

    /**
     * Method is used to send normal notification.
     * @param title
     * @param message
     */
    fun createNormalNotification(context: Context, title: String?, message: String?) {

        LoggerUtils.debug(
            TAG,
            "createNormalNotification [Title : $title, Message : $message]"
        )
        val notificationBuilder = createNotificationBuilder(context, title, message)
        notify(context, AppConstant.NOTIFICATION_REQUEST, notificationBuilder)
    }

    /**
     * Method is used to send big notification.
     * @param title
     * @param message
     * @param bigText
     */
    fun createBigNotification(context: Context, title: String?, message: String?, bigText: String) {
        LoggerUtils.debug(
            TAG,
            "createBigNotification [Title : $title, Message : $message, BigText : $bigText]"
        )
        val notificationBuilder = createNotificationBuilder(context, title, message)
        notificationBuilder.setStyle(
            NotificationCompat.BigTextStyle()
                .bigText(bigText)
        )
        notify(context, AppConstant.NOTIFICATION_REQUEST, notificationBuilder)
    }

    /**
     * Method is used to send image notification.
     * @param title
     * @param message
     * @param bitmap
     */
    fun createImageNotification(
        context: Context,
        title: String?,
        message: String?,
        bitmap: Bitmap
    ) {
        LoggerUtils.debug(
            TAG,
            "createImageNotification [Title : $title, Message : $message]"
        )
        val notificationBuilder = createNotificationBuilder(context, title, message)
        notificationBuilder.setLargeIcon(bitmap)
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(bitmap)
                    .bigLargeIcon(null)
            )
        notify(context, AppConstant.NOTIFICATION_REQUEST, notificationBuilder)
    }

    /**
     * Method is used to send normal notification.
     * @param title
     * @param message
     * @param screenName
     */
    fun createScreenActionNotification(
        context: Context,
        title: String?,
        message: String?,
        screenName: String
    ) {
        LoggerUtils.debug(
            TAG,
            "createScreenActionNotification [Title : $title, Message : $message, ScreenName : $screenName]"
        )
        val intent = getIntentAsPerScreenRequest(context, screenName)
        val pendingIntent = PendingIntent.getActivity(
            context, AppConstant.NOTIFICATION_REQUEST,
            intent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        val notificationBuilder = createNotificationBuilder(context, title, message)
        notificationBuilder.setContentIntent(pendingIntent)

        notify(context, AppConstant.NOTIFICATION_REQUEST, notificationBuilder)
    }

    /**
     * This method is used to create a notification for friend request
     * @param title
     * @param message
     * @param senderId
     * @param receiverId
     */
    fun createFriendRequestNotification(
        context: Context,
        title: String?,
        message: String,
        senderId: String,
        receiverId: String,
        screenName: String
    ) {

        LoggerUtils.debug(
            TAG,
            "createFriendRequestNotification [Title : $title, Message : $message, SenderID : $senderId, ReceiverID : $receiverId, ScreenName : $screenName]"
        )
        val acceptIntent = Intent(context, HandleFriendRequestService::class.java)
        acceptIntent.action = context.getString(R.string.accept)
        acceptIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        acceptIntent.putExtra(BundleArgsConstant.SENDER_USER_ID_KEY, senderId)
        acceptIntent.putExtra(BundleArgsConstant.RECEIVER_USER_ID_KEY, receiverId)

        val rejectIntent = Intent(context, HandleFriendRequestService::class.java)
        rejectIntent.action = context.getString(R.string.reject)
        rejectIntent.putExtra(BundleArgsConstant.SENDER_USER_ID_KEY, senderId)
        rejectIntent.putExtra(BundleArgsConstant.RECEIVER_USER_ID_KEY, receiverId)
        rejectIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val expendView = RemoteViews(context.packageName, R.layout.friend_request_notification_view)
        expendView.setTextViewText(R.id.tv_title, title)
        expendView.setTextViewText(R.id.tv_message, message)
        expendView.setOnClickPendingIntent(
            R.id.bt_accept, PendingIntent.getService(
                context, AppConstant.FRIEND_REQUEST_NOTIFICATION_REQUEST,
                acceptIntent, PendingIntent.FLAG_UPDATE_CURRENT
            )
        )
        expendView.setOnClickPendingIntent(
            R.id.bt_reject, PendingIntent.getService(
                context, AppConstant.FRIEND_REQUEST_NOTIFICATION_REQUEST,
                rejectIntent, PendingIntent.FLAG_UPDATE_CURRENT
            )
        )

        val intent = getIntentAsPerScreenRequest(context, screenName)
        val pendingIntent = PendingIntent.getActivity(
            context, AppConstant.FRIEND_REQUEST_NOTIFICATION_REQUEST,
            intent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notificationBuilder = createNotificationBuilder(context, title, message)
        notificationBuilder.setContentIntent(pendingIntent)
            .setCustomBigContentView(expendView)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())

        notify(context, AppConstant.FRIEND_REQUEST_NOTIFICATION_REQUEST, notificationBuilder)
    }

    /**
     * Method is used to notify notification.
     * @param context
     * @param notificationRequest
     * @param notificationBuilder
     */
    private fun notify(
        context: Context,
        notificationRequest: Int,
        notificationBuilder: NotificationCompat.Builder
    ) {
        LoggerUtils.debug(
            TAG,
            "notify [NotificationRequest : $notificationRequest]"
        )
        val notificationManager = getNotificationManager(context)

        notificationManager.notify(
            notificationRequest,
            notificationBuilder.build()
        )
    }

    /**
     * Method is used to get Notification Manager.
     * @param context
     */
    private fun getNotificationManager(context: Context): NotificationManager {
        LoggerUtils.info(
            TAG,
            "getNotificationManager"
        )
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel(context, notificationManager)
        return notificationManager
    }
}