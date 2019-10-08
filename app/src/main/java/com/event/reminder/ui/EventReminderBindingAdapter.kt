package com.event.reminder.ui

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import com.android.mvvmandroidlib.exception.DateUtilParseException
import com.android.mvvmandroidlib.utills.DateUtils
import com.android.mvvmandroidlib.utills.StringUtils
import com.event.reminder.EventReminderApplication
import com.squareup.picasso.Picasso

@BindingAdapter(value = ["bind:imageUrl", "bind:placeholder"], requireAll = false)
fun loadImage(imageView: ImageView, url: String?, placeHolder: Drawable?) {
    if (StringUtils.isNullOrEmpty(url)) {
        imageView.setImageDrawable(placeHolder)
    } else {
        Picasso.with(EventReminderApplication.getInstance().applicationContext)
            .load(url)
            .error(placeHolder)
            .into(imageView)
    }
}

@BindingAdapter(value = ["bind:timeStamp", "bind:timeFormat"], requireAll = true)
fun setDate(view: TextView, timeStamp: Long?, timeFormat: String?) {
    try {
        view.text = DateUtils.formatDate(timeStamp, timeFormat)
    }catch (e: DateUtilParseException){
        view.text = ""
    }
}

