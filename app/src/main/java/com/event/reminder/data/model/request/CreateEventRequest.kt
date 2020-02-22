package com.event.reminder.data.model.request

import com.android.mvvmandroidlib.data.BaseRequestModel
import com.event.reminder.constant.EventType

/**
 * This data class is used as a request for Create Event API.
 *
 * @author Shubham Chauhan
 */
data class CreateEventRequest(
    var createdBy: String,
    var eventDate: Long,
    var isAlarmUsed: Boolean,
    var isImportant: Boolean,
    var eventTitle: String,
    var eventDescription: String,
    var isTaskListPresent: Boolean,
    var location: String,
    var eventType: Int = EventType.SELF_EVENT,
    var createdFor: ArrayList<String>?
) : BaseRequestModel()