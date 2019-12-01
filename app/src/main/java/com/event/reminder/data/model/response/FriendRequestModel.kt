package com.event.reminder.data.model.response

import com.android.mvvmandroidlib.data.BaseResponseModel
import com.event.reminder.enums.FriendStatus
import com.event.reminder.enums.RequestType
import java.io.Serializable

data class FriendRequestDetailsModel(
    var friendDetailsList: ArrayList<FriendRequestDetails>
) : BaseResponseModel()

data class FriendRequestDetails(
    val name: String,
    val age: String,
    val city: String,
    val requestDate: Long?,
    val friendStatus: Int = FriendStatus.PENDING.ordinal,
    val requestType: Int = RequestType.REQUEST_TYPE_RECEIVED.ordinal,
    val imageUrl: String? = null
) : Serializable