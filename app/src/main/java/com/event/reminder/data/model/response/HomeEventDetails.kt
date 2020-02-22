package com.event.reminder.data.model.response

import com.android.mvvmandroidlib.data.BaseResponseModel

/**
 * This data class is used as a response for Event Details API.
 *
 * @author Shubham Chauhan
 */
data class HomeEventDetails(
    val eventId: String = "",
    val title: String = "",
    val description: String,
    val createdDate: Long = 1572002745
) : BaseResponseModel()