package com.event.reminder.ui.dashboard

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import com.android.mvvmandroidlib.utills.DateUtils
import com.android.mvvmandroidlib.utills.StringUtils
import com.event.reminder.EventReminderApplication
import com.squareup.picasso.Picasso

@BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = false)
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

@BindingAdapter("timeStamp")
fun setDate(view: TextView, timeStamp: Long?) {
    view.text = DateUtils.formatDate(timeStamp, DateUtils.HH_MM_AA)
}

