package com.event.reminder.data.model.response

import com.android.mvvmandroidlib.data.BaseResponseModel

/**
 * This data class is used as a response for Friend Status API.
 *
 * @author Shubham Chauhan
 */
data class FriendStatusModel(
    val userId: String,
    val friendId: String,
    val actionUserId: String,
    val friendStatus: Int
) : BaseResponseModel()