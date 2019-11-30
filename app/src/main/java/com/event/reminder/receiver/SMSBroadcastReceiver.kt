package com.event.reminder.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.event.reminder.constant.BroadcastReceiverAction
import com.event.reminder.constant.BundleArgsConstant
import com.event.reminder.constant.ErrorConstant
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status


class SMSBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (SmsRetriever.SMS_RETRIEVED_ACTION == intent?.action) {
            val extras = intent.extras
            val status = extras?.get(SmsRetriever.EXTRA_STATUS) as Status

            when (status.statusCode) {
                CommonStatusCodes.SUCCESS -> {
                    // Get SMS message contents
                    val message = extras.get(SmsRetriever.EXTRA_SMS_MESSAGE) as String

                    val otpIntent = Intent(BroadcastReceiverAction.OTP_READ_SUCCESS)
                    otpIntent.putExtra(BundleArgsConstant.OTP_MESSAGE, message)
                    context?.sendBroadcast(otpIntent)
                }
                CommonStatusCodes.TIMEOUT -> {
                    // Waiting for SMS timed out (5 minutes)
                    val otpIntent = Intent(BroadcastReceiverAction.OTP_READ_TIMEOUT)
                    otpIntent.putExtra(
                        BundleArgsConstant.OTP_ERROR_MESSAGE,
                        ErrorConstant.SMS_READ_ERROR
                    )
                    context?.sendBroadcast(otpIntent)
                }
            }
        }
    }
}