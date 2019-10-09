package com.event.reminder.data.model.response

import com.android.mvvmandroidlib.data.BaseResponseModel

data class UserDetailsModel(
    val name: String,
    val age: String,
    val city: String = "Haridwar",
    val gender: String,
    val phoneNumber: String? = null,
    val emailAddress: String,
    val dateOfBirth: String,
    val timeStamp: Long? = 1570406400,
    val imageUrl: String? = null
) : BaseResponseModel()