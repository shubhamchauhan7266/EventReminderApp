package com.event.reminder.data.model.request

import com.event.reminder.constant.OTPType
import java.io.Serializable

data class ValidateOTPRequest (
    var userId: String?,
    var otpType: Int = OTPType.MOBILE_NUMBER,
    var otpValue: String?,
    var otpSendTo: String?
) : Serializable