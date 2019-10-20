package com.event.reminder.data.model.response

import com.android.mvvmandroidlib.data.BaseResponseModel
import java.io.Serializable

data class NotificationDetailsModel(
    var notificationDetailsList: ArrayList<NotificationDetails>
) : BaseResponseModel()

data class NotificationDetails(
    val title: String = "",
    val description: String = "",
    val dateCreated: Long? = 1570406400
) : Serializable