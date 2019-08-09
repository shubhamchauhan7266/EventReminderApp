package com.event.reminder.data.model.request

import com.event.reminder.constant.ErrorConstant
import java.io.Serializable

data class SignUpRequest(
    var fullName: String? = null,
    var emailId: String? = null,
    var phoneNumber: String? = null,
    var password: String? = null,
    var gender: String? = null,
    var dateOfBirth: String? = null,
    var image: String? = null,
    var streetAddress: String? = null,
    var city: String? = null,
    var state: String? = null,
    var country: String? = null,
    var pinCode: Int? = ErrorConstant.INVALID_NUMBER
) : Serializable