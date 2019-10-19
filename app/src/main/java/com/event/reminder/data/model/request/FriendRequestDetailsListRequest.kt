package com.event.reminder.data.model.request

import java.io.Serializable

class FriendRequestDetailsListRequest (
    var accessToken: String?,
    var userId: String?,
    var requestType: Int = 0
) : Serializable