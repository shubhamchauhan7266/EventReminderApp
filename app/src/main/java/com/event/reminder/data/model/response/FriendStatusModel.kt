package com.event.reminder.data.model.response

import com.android.mvvmandroidlib.data.BaseResponseModel

data class FriendStatusModel(
    val userId: String,
    val friendId: String,
    val friendStatus: Int
) : BaseResponseModel()