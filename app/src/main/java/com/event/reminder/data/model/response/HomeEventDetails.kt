package com.event.reminder.data.model.response

import java.io.Serializable

data class HomeEventDetails(
    val eventId: String = "",
    val eventType: Int,
    val title: String = "",
    val description: String,
    val createdDate: Long = 1572002745
) : Serializable