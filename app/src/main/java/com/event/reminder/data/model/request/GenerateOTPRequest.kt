package com.event.reminder.data.model.request

import com.android.mvvmandroidlib.data.BaseRequestModel
import com.event.reminder.constant.OTPType

/**
 * This data class is used as a request for Generate OTP API.
 *
 * @author Shubham Chauhan
 */
data class GenerateOTPRequest(
    var userId: String?,
    var otpType: Int = OTPType.MOBILE_NUMBER,
    var otpSendTo: String?
) : BaseRequestModel()