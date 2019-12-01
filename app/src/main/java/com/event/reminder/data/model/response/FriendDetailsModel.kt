package com.event.reminder.data.model.response

import com.android.mvvmandroidlib.data.BaseResponseModel
import com.event.reminder.enums.FriendStatus
import com.event.reminder.enums.RequestType
import java.io.Serializable

data class FriendDetailsModel(
    var friendDetailsList: ArrayList<FriendDetails>
) : BaseResponseModel()

data class FriendDetails(
    val name: String,
    val age: String,
    val city: String,
    val timeStamp: Long?,
    val friendStatus: Int = FriendStatus.PENDING.ordinal,
    val requestType: Int = RequestType.REQUEST_TYPE_RECEIVED.ordinal,
    val imageUrl: String? = null
) : Serializable