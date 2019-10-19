package com.event.reminder.data.model.response

import com.android.mvvmandroidlib.data.BaseResponseModel
import com.event.reminder.constant.AppConstant
import java.io.Serializable

data class FriendDetailsModel(
    var friendDetailsList: ArrayList<FriendDetails>
) : BaseResponseModel()

data class FriendDetails(
    val name: String,
    val age: String,
    val city: String = "Haridwar",
    val timeStamp: Long? = 1570406400,
    val requestStatus: String = "Pending",
    val requestType: Int = AppConstant.REQUEST_TYPE_RECEIVED,
    val imageUrl: String? = null
) : Serializable