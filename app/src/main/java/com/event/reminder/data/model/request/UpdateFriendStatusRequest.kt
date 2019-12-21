package com.event.reminder.data.model.request

import java.io.Serializable

data class UpdateFriendStatusRequest(
    var firstUserId: String,
    var secondUserId: String,
    var friendStatus: String,
    var actionUserId: String
) : Serializable