package com.event.reminder.data.model.request

import java.io.Serializable

data class GetFriendStatusRequest(
    var userId: String,
    var friendId: String
) : Serializable