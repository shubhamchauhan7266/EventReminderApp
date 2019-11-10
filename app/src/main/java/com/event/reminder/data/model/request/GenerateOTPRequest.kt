package com.event.reminder.data.model.request

import com.event.reminder.constant.OTPTypeConstant
import java.io.Serializable

class GenerateOTPRequest(
    var userId: String?,
    var otpType: Int = OTPTypeConstant.MOBILE_NUMBER,
    var otpSendTo: String?
) : Serializable