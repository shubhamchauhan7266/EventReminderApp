package com.event.reminder.data.model.response

import com.android.mvvmandroidlib.data.BaseResponseModel
import java.io.Serializable

/**
 * This data class is used as a response for Notification Details API.
 *
 * @author Shubham Chauhan
 */
data class NotificationDetailsModel(
    var notificationDetailsList: ArrayList<NotificationDetails>
) : BaseResponseModel()

data class NotificationDetails(
    val actionUserId: String,
    val notificationId: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val notificationImage: String,
    val notificationTypeId: String,
    val dateModified: Long?,
    val isImportant: Boolean?,
    val screenName: String?,
    val isRead: Boolean?
) : Serializable