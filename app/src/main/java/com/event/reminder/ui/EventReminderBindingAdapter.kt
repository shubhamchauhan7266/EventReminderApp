package com.event.reminder.ui

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import com.android.mvvmandroidlib.exception.DateUtilParseException
import com.android.mvvmandroidlib.utills.ContextUtils
import com.android.mvvmandroidlib.utills.DateUtils
import com.android.mvvmandroidlib.utills.DisplayUtils
import com.android.mvvmandroidlib.utills.StringUtils
import com.squareup.picasso.Picasso

@BindingAdapter(
    value = ["bind:imageUrl", "bind:placeholder", "android:layout_width", "android:layout_height"],
    requireAll = false
)
fun loadImage(
    imageView: ImageView,
    url: String?,
    placeHolder: Drawable?,
    width: Float,
    height: Float
) {
    if (StringUtils.isNullOrEmpty(url)) {
        imageView.setImageDrawable(placeHolder)
    } else {
        try {
            Picasso.with(imageView.context)
                .load(url)
                .placeholder(placeHolder)
                .error(placeHolder)
                .resize(
                    DisplayUtils.convertDpToPixel(width, imageView.context).toInt(),
                    DisplayUtils.convertDpToPixel(height, imageView.context).toInt()
                )
                .centerCrop()
                .onlyScaleDown()
                .into(imageView)
        } catch (e: Exception) {
            imageView.setImageDrawable(placeHolder)
        }
    }
}

@BindingAdapter(value = ["bind:timeStamp", "bind:timeFormat"], requireAll = true)
fun setDate(view: TextView, timeStamp: Long?, timeFormat: String?) {
    try {
        view.text = DateUtils.formatDate(timeStamp, timeFormat)
    } catch (e: DateUtilParseException) {
        view.text = StringUtils.EMPTY
    }
}

