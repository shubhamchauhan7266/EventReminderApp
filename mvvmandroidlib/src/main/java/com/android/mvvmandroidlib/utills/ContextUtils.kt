package com.android.mvvmandroidlib.utills

import android.app.Activity
import android.content.Context

object ContextUtils {

    fun isActivityDestroyed(activity: Activity?): Boolean {
        return activity == null || activity.isDestroyed || activity.isFinishing
    }

    fun isContextNull(context: Context?): Boolean {
        return context == null
    }

    fun isObjectNull(context: Any?): Boolean {
        return context == null
    }
}