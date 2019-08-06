package com.event.reminder.data.model.request

import java.io.Serializable

data class SignUpRequest(
    var fullName: String? = null,
    var emailId: String? = null,
    var phoneNumber: String? = null,
    var password: String? = null,
    var gender: String? = null,
    var dateOfBirth: String? = null,
    var image: String? = null
) : Serializable