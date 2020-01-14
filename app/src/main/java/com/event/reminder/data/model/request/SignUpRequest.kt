package com.event.reminder.data.model.request

import com.android.mvvmandroidlib.data.BaseRequestModel
import com.event.reminder.constant.ErrorConstant

data class SignUpRequest(
    var firstName: String? = null,
    var lastName: String? = "Chauhan",
    var emailAddress: String?,
    var phoneNumber: String?,
    var password: String?,
    var gender: String?,
    var dateOfBirth: Long? = 846201600,
    var imageUrl: String? = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/df/Hrithik_Rado.jpg/250px-Hrithik_Rado.jpg",
    var streetAddress: String? = "736",
    var city: String? = "Haridwar",
    var state: String? = "Uttarakhand",
    var country: String? = "India",
    var postalCode: String? = ErrorConstant.INVALID_PIN_CODE
) : BaseRequestModel()