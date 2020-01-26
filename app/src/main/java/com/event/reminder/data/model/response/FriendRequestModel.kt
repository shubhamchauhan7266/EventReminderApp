package com.event.reminder.data.model.response

import com.android.mvvmandroidlib.data.BaseResponseModel
import com.event.reminder.constant.FriendStatus
import com.event.reminder.constant.RequestType
import java.io.Serializable

data class FriendRequestDetailsModel(
    var friendRequestDetailsList: ArrayList<FriendRequestDetails>
) : BaseResponseModel()

data class FriendRequestDetails(
    val userId: String,
    val name: String,
    val dateOfBirth: Long?,
    val city: String,
    val requestDate: Long?,
    val friendStatus: Int = FriendStatus.PENDING,
    var requestType: Int = RequestType.REQUEST_TYPE_RECEIVED,
    val imageUrl: String? = null
) : Serializable