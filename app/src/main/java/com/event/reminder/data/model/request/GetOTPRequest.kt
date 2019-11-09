package com.event.reminder.data.model.request

import com.event.reminder.enum.OTPType
import java.io.Serializable

class GetOTPRequest (
    var userId: String?,
    var otpType: Int = OTPType.MOBILE_NUMBER.ordinal,
    var otpSendTo: String?
) : Serializable