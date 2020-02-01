package com.event.reminder.data.model.request

import com.android.mvvmandroidlib.data.BaseRequestModel

/**
 * This data class is used as a request for Friend List API.
 *
 * @author Shubham Chauhan
 */
data class FriendListRequest(
    var userId: String
) : BaseRequestModel()