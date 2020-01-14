package com.event.reminder.data.model.request

import com.android.mvvmandroidlib.data.BaseRequestModel

data class GetFriendStatusRequest(
    var userId: String,
    var friendId: String
) : BaseRequestModel()