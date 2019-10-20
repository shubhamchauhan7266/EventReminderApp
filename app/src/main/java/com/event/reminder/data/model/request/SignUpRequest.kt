package com.event.reminder.data.model.request

import com.event.reminder.constant.ErrorConstant
import java.io.Serializable

data class SignUpRequest(
    var firstName: String? = null,
    var lastName: String? = "Chauhan",
    var emailAddress: String?,
    var phoneNumber: String?,
    var password: String?,
    var gender: String?,
    var dateOfBirth: Long? = 846201600,
    var imageUrl: String?,
    var streetAddress: String? = "736",
    var city: String? = "Haridwar",
    var state: String? = "Uttarakhand",
    var country: String? = "India",
    var postalCode: String? = ErrorConstant.INVALID_PIN_CODE
) : Serializable