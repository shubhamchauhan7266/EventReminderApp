package com.event.reminder.data.model.request

import com.event.reminder.constant.ErrorConstant
import java.io.Serializable

class UpdateUserDetailsRequest(
    var userId: String?,
    var firstName: String?,
    var lastName: String? = "Chauhan",
    var emailAddress: String?,
    var phoneNumber: String?,
    var gender: String?,
    var dateOfBirth: Long? = 846201600,
    var imageUrl: String?,
    var streetAddress: String? = "736",
    var city: String? = "Haridwar",
    var state: String? = "Uttarakhand",
    var country: String? = "India",
    var postalCode: String? = ErrorConstant.INVALID_PIN_CODE
) : Serializable