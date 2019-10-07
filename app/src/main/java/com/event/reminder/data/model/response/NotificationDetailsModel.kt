package com.event.reminder.data.model.response

import com.android.mvvmandroidlib.data.BaseResponseModel

data class NotificationDetailsModel(
    val title: String = "",
    val description: String = "",
    val timeStamp: Long? = 1570406400
) : BaseResponseModel()