package com.event.reminder.data.model.request

import com.event.reminder.constant.OTPTypeConstant
import java.io.Serializable

data class ValidateOTPRequest (
    var userId: String?,
    var otpType: Int = OTPTypeConstant.MOBILE_NUMBER,
    var otpValue: String?,
    var otpSendTo: String?
) : Serializable