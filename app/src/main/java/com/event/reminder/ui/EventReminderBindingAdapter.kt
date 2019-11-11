package com.event.reminder.ui

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.android.mvvmandroidlib.exception.DateUtilParseException
import com.android.mvvmandroidlib.utills.DateUtils
import com.android.mvvmandroidlib.utills.LoggerUtils
import com.android.mvvmandroidlib.utills.StringUtils
import com.event.reminder.R
import com.event.reminder.constant.ValidationTypeConstant
import com.google.android.material.textfield.TextInputLayout
import com.squareup.picasso.Picasso

@BindingAdapter(
    value = ["bind:imageUrl", "bind:placeholder"],
    requireAll = false
)
fun loadImage(
    imageView: ImageView,
    url: String?,
    placeHolder: Drawable?
) {
    LoggerUtils.info("EventReminderBindingAdapter", "url : $url")
    if (StringUtils.isNullOrEmpty(url)) {
        imageView.setImageDrawable(placeHolder)
    } else {
        try {
            Picasso.with(imageView.context)
                .load(url)
                .placeholder(placeHolder)
                .error(placeHolder)
                .resize(
                    320, 320
                )
                .centerCrop()
                .onlyScaleDown()
                .into(imageView)
        } catch (e: Exception) {
            LoggerUtils.error(
                "EventReminderBindingAdapter",
                "loadImage : " + LoggerUtils.getStackTraceString(e)
            )
            imageView.setImageDrawable(placeHolder)
        }
    }
}

@BindingAdapter(value = ["bind:timeStamp", "bind:timeFormat"], requireAll = true)
fun setDate(view: TextView, timeStamp: Long?, timeFormat: String?) {
    LoggerUtils.info(
        "EventReminderBindingAdapter",
        "timeStamp : $timeStamp , timeFormat : $timeFormat"
    )
    try {
        view.text = DateUtils.formatDate(timeStamp, timeFormat)
    } catch (e: DateUtilParseException) {
        LoggerUtils.error(
            "EventReminderBindingAdapter",
            "setDate : " + LoggerUtils.getStackTraceString(e)
        )
        view.text = StringUtils.EMPTY
    }
}

@BindingAdapter(value = ["bind:validatorValue", "bind:validationType"], requireAll = true)
fun checkValidation(
    textInputLayout: TextInputLayout,
    validatorValue: String?,
    validationType: Int?
) {
    /*LoggerUtils.info(
        "EventReminderBindingAdapter",
        "validatorValue : $validatorValue , validationType"
    )*/

    if (textInputLayout.hasFocus()) {
        try {
            val errorMessage: Int
            val isValid: Boolean = when (validationType) {
                ValidationTypeConstant.NORMAL_EMPTY -> {
                    errorMessage = R.string.invalid_field
                    !StringUtils.isNullOrEmpty(validatorValue)
                }
                ValidationTypeConstant.USER_NAME -> {
                    errorMessage = R.string.invalid_username
                    !StringUtils.isNullOrEmpty(validatorValue)
                }
                ValidationTypeConstant.PASSWORD -> {
                    errorMessage = R.string.invalid_password
                    StringUtils.isPasswordValid(validatorValue)
                }
                ValidationTypeConstant.EMAIL_ADDRESS -> {
                    errorMessage = R.string.invalid_email_id
                    StringUtils.isEmailIdValid(validatorValue)
                }
                ValidationTypeConstant.MOBILE_NUMBER -> {
                    errorMessage = R.string.invalid_phone_number
                    StringUtils.isPhoneNumberValid(validatorValue)
                }
                ValidationTypeConstant.DATE_OF_BIRTH -> {
                    errorMessage = R.string.invalid_date_of_birth
                    !StringUtils.isNullOrEmpty(validatorValue)
                }
                ValidationTypeConstant.FULL_NAME -> {
                    errorMessage = R.string.invalid_name
                    !StringUtils.isNullOrEmpty(validatorValue)
                }
                ValidationTypeConstant.GENDER -> {
                    errorMessage = R.string.invalid_gender
                    !StringUtils.isNullOrEmpty(validatorValue)
                }
                else -> {
                    errorMessage = R.string.invalid_field
                    !StringUtils.isNullOrEmpty(validatorValue)
                }
            }

            // Check is valid or not
            if (isValid) {
                textInputLayout.isErrorEnabled = false
            } else {
                textInputLayout.isErrorEnabled = true
                textInputLayout.error = textInputLayout.context.getString(errorMessage)
            }

        } catch (e: Exception) {
            LoggerUtils.error(
                "EventReminderBindingAdapter",
                "checkValidation : " + LoggerUtils.getStackTraceString(e)
            )
            textInputLayout.isErrorEnabled = true
            textInputLayout.error = textInputLayout.context.getString(R.string.unknown_error)
        }
    }
}

