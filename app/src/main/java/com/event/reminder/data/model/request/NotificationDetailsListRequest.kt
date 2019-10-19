package com.event.reminder.data.model.request

import java.io.Serializable

class NotificationDetailsListRequest(
    var accessToken: String?,
    var userId: String?
) : Serializable