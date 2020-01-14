package com.event.reminder.data.model.request

import com.android.mvvmandroidlib.data.BaseRequestModel
import com.event.reminder.constant.OTPType

data class ValidateOTPRequest (
    var userId: String?,
    var otpType: Int = OTPType.MOBILE_NUMBER,
    var otpValue: String?,
    var otpSendTo: String?
) : BaseRequestModel()