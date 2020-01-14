package com.event.reminder.data.model.request

import com.android.mvvmandroidlib.data.BaseRequestModel

data class UpdateFriendStatusRequest(
    var firstUserId: String,
    var secondUserId: String,
    var friendStatus: String,
    var actionUserId: String
) : BaseRequestModel()