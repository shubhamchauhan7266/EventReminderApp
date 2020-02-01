package com.event.reminder.data.model.request

import com.android.mvvmandroidlib.data.BaseRequestModel

/**
 * This data class is used as a request for Get Friend Status API.
 *
 * @author Shubham Chauhan
 */
data class GetFriendStatusRequest(
    var userId: String,
    var friendId: String
) : BaseRequestModel()