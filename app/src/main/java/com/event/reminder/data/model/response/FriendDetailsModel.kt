package com.event.reminder.data.model.response

import com.android.mvvmandroidlib.data.BaseResponseModel
import com.event.reminder.constant.AppConstant

data class FriendDetailsModel(
    val name: String,
    val age: String,
    val city: String = "Haridwar",
    val date: String = "",
    val requestStatus: String = "Pending",
    val requestType: Int = AppConstant.REQUEST_TYPE_RECEIVED,
    val imageUrl: String? = null
) : BaseResponseModel()