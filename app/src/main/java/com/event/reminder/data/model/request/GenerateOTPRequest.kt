package com.event.reminder.data.model.request

import com.android.mvvmandroidlib.data.BaseRequestModel
import com.event.reminder.constant.OTPType

class GenerateOTPRequest(
    var userId: String?,
    var otpType: Int = OTPType.MOBILE_NUMBER,
    var otpSendTo: String?
) : BaseRequestModel()