package com.event.reminder.ui

import android.graphics.drawable.Drawable
import android.os.Build
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.android.mvvmandroidlib.exception.DateUtilParseException
import com.android.mvvmandroidlib.utills.DateUtils
import com.android.mvvmandroidlib.utills.LoggerUtils
import com.android.mvvmandroidlib.utills.StringUtils
import com.event.reminder.R
import com.event.reminder.constant.FriendStatus
import com.event.reminder.constant.ValidationTypeConstant
import com.event.reminder.utills.FriendStatusUtils
import com.google.android.material.textfield.TextInputLayout
import com.squareup.picasso.Picasso

/**
 * This file is used to provide some custom methods which is used by data binding in UI.
 *
 * @author Shubham Chauhan
 */
@BindingAdapter(
    value = ["bind:imageUrl", "bind:placeholder"],
    requireAll = false
)
fun loadImage(
    imageView: ImageView,
    url: String?,
    placeHolder: Drawable?
) {
    LoggerUtils.debug("EventReminderBindingAdapter", "loadImage [url : $url]")
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
    LoggerUtils.debug(
        "EventReminderBindingAdapter",
        "setDate [timeStamp : $timeStamp , timeFormat : $timeFormat]"
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

@BindingAdapter(value = ["bind:dateOfBirth"], requireAll = true)
fun extractAgeFromDOB(view: TextView, dateOfBirth: Long?) {
    LoggerUtils.debug(
        "EventReminderBindingAdapter",
        "extractAgeFromDOB [dateOfBirth : $dateOfBirth]"
    )
    try {
        view.text = String.format(
            view.context.getString(R.string.age_year),
            DateUtils.getAgeFromDateOfBirth(dateOfBirth)
        )
    } catch (e: DateUtilParseException) {
        LoggerUtils.error(
            "EventReminderBindingAdapter",
            "extractAgeFromDOB : " + LoggerUtils.getStackTraceString(e)
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
    LoggerUtils.debug(
        "EventReminderBindingAdapter",
        "checkValidation [validatorValue : $validatorValue , validationType : $validationType]"
    )

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

@BindingAdapter(value = ["bind:friendStatus", "bind:actionUserId"], requireAll = true)
fun updateFriendStatus(view: Button, friendStatus: Int, actionUserId: String) {
    LoggerUtils.debug(
        "EventReminderBindingAdapter",
        "updateFriendStatus [friendStatus : $friendStatus]"
    )
    FriendStatusUtils.updateStatus(view, friendStatus, actionUserId, false)
}

@BindingAdapter(value = ["bind:blockStatus", "bind:actionUserId"], requireAll = true)
fun updateBlockStatus(view: Button, blockStatus: Int, actionUserId: String) {
    LoggerUtils.debug(
        "EventReminderBindingAdapter",
        "updateBlockStatus [blockStatus : $blockStatus]"
    )
    FriendStatusUtils.updateStatus(view, blockStatus, actionUserId, true)
}

@BindingAdapter(value = ["bind:friendRequestStatus"], requireAll = true)
fun updateFriendRequestStatus(view: TextView, friendRequestStatus: Int) {
    LoggerUtils.debug(
        "EventReminderBindingAdapter",
        "updateFriendRequestStatus [friendRequestStatus : $friendRequestStatus]"
    )

    when (friendRequestStatus) {
        FriendStatus.PENDING -> {
            view.text = view.context.getString(R.string.pending)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                view.setTextColor(view.context.getColor(R.color.fontYellow))
            } else {
                view.setTextColor(view.context.resources.getColor(R.color.fontYellow))
            }
        }
        FriendStatus.REJECTED -> {
            view.text = view.context.getString(R.string.rejected)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                view.setTextColor(view.context.getColor(R.color.fontRed))
            } else {
                view.setTextColor(view.context.resources.getColor(R.color.fontRed))
            }
        }
        else -> {
            view.text = view.context.getString(R.string.unknown)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                view.setTextColor(view.context.getColor(R.color.fontBlack40))
            } else {
                view.setTextColor(view.context.resources.getColor(R.color.fontBlack40))
            }
        }
    }
}

