package com.event.reminder.data.model.response

import com.android.mvvmandroidlib.data.BaseResponseModel
import com.event.reminder.constant.AppConstant
import java.io.Serializable

data class FriendRequestDetailsModel(
    var friendDetailsList: ArrayList<FriendRequestDetails>
) : BaseResponseModel()

data class FriendRequestDetails(
    val name: String,
    val age: String,
    val city: String = "Haridwar",
    val requestDate: Long? = 1570406400,
    val requestStatus: String = "Pending",
    val requestType: Int = AppConstant.REQUEST_TYPE_RECEIVED,
    val imageUrl: String? = null
) : Serializable