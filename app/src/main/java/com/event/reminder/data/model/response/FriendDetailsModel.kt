package com.event.reminder.data.model.response

import com.android.mvvmandroidlib.data.BaseResponseModel
import com.event.reminder.constant.FriendStatus
import com.event.reminder.constant.RequestType
import java.io.Serializable

/**
 * This data class is used as a response for Friend Details API.
 *
 * @author Shubham Chauhan
 */
data class FriendDetailsModel(
    var friendDetailsList: ArrayList<FriendDetails>
) : BaseResponseModel()

data class FriendDetails(
    val name: String,
    val age: String,
    val city: String,
    val timeStamp: Long?,
    val friendStatus: Int = FriendStatus.PENDING,
    val requestType: Int = RequestType.REQUEST_TYPE_RECEIVED,
    val imageUrl: String? = null
) : Serializable