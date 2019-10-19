package com.event.reminder.data.model.response

import com.android.mvvmandroidlib.data.BaseResponseModel
import java.io.Serializable

data class UserDetailsModel(
    var userDetails: UserDetails
) : BaseResponseModel()

data class UserDetails(
    val firstName: String,
    val lastName: String,
    val age: String,
    val gender: String,
    val phoneNumber: String?,
    val emailAddress: String,
    val dateOfBirth: Long? = 1570406400,
    val imageUrl: String?,
    val streetAddress: String?,
    val city: String?,
    val state: String?,
    val country: String?,
    val postalCode: String?,
    val isEmailAddressVerified: Boolean,
    val isPhoneNumberVerified: Boolean,
    val IsLinkedToGooglePlus: Boolean,
    val facebookKey: String,
    val googlePlusKey: String
) : Serializable
