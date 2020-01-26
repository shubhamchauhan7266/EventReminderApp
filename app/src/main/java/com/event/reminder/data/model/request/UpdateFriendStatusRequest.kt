package com.event.reminder.data.model.request

import com.android.mvvmandroidlib.data.BaseRequestModel
import com.event.reminder.constant.FriendStatus

data class UpdateFriendStatusRequest(
    var userId: String,
    var friendId: String,
    var friendStatus: Int = FriendStatus.NOT_A_FRIEND,
    var actionUserId: String
) : BaseRequestModel()