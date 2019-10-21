package com.event.reminder.ui

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import com.android.mvvmandroidlib.exception.DateUtilParseException
import com.android.mvvmandroidlib.utills.DateUtils
import com.android.mvvmandroidlib.utills.LoggerUtils
import com.android.mvvmandroidlib.utills.StringUtils
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

