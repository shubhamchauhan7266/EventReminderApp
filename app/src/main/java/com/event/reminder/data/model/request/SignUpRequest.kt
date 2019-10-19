package com.event.reminder.data.model.request

import com.event.reminder.constant.ErrorConstant
import java.io.Serializable

data class SignUpRequest(
    var firstName: String? = null,
    var lastName: String? = "Chauhan",
    var emailAddress: String? = null,
    var phoneNumber: String? = null,
    var password: String? = null,
    var gender: String? = null,
    var dateOfBirth: Long? = 846201600,
    var imageUrl: String? = null,
    var streetAddress: String? = "736",
    var city: String? = "Haridwar",
    var state: String? = "Uttarakhand",
    var country: String? = "India",
    var postalCode: String? = ErrorConstant.INVALID_PIN_CODE
) : Serializable