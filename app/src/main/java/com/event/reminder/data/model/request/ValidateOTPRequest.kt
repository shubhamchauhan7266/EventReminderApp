package com.event.reminder.data.model.request

import com.android.mvvmandroidlib.data.BaseRequestModel
import com.event.reminder.constant.OTPType

/**
 * This data class is used as a request for Validate OTP API.
 *
 * @author Shubham Chauhan
 */
data class ValidateOTPRequest (
    var userId: String?,
    var otpType: Int = OTPType.MOBILE_NUMBER,
    var otpValue: String?,
    var otpSendTo: String?
) : BaseRequestModel()