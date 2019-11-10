package com.event.reminder.data.model.request

import com.event.reminder.enum.OTPType
import java.io.Serializable

data class ValidateOTPRequest (
    var userId: String?,
    var otpType: Int = OTPType.MOBILE_NUMBER.ordinal,
    var otpValue: String?,
    var otpSendTo: String?
) : Serializable