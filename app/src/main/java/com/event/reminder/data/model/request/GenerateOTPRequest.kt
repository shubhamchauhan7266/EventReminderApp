package com.event.reminder.data.model.request

import com.event.reminder.constant.OTPType
import java.io.Serializable

class GenerateOTPRequest(
    var userId: String?,
    var otpType: Int = OTPType.MOBILE_NUMBER,
    var otpSendTo: String?
) : Serializable