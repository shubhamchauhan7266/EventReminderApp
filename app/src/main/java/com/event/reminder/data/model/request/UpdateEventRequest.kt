package com.event.reminder.data.model.request

import com.android.mvvmandroidlib.data.BaseRequestModel

/**
 * This data class is used as a request for Update Event API.
 *
 * @author Shubham Chauhan
 */
data class UpdateEventRequest(
    var eventId: String,
    var createdBy: String,
    var eventDate: Long,
    var isAlarmUsed: Boolean,
    var isImportant: Boolean,
    var eventTitle: String,
    var eventDescription: String,
    var isTaskListPresent: Boolean,
    var location: String,
    var eventType: Int,
    var createdFor: ArrayList<String>?
) : BaseRequestModel()