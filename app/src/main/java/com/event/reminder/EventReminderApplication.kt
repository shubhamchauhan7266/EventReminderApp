package com.event.reminder

import android.content.Context
import android.support.multidex.MultiDex
import com.android.mvvmandroidlib.BaseApplication

class EventReminderApplication : BaseApplication() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}