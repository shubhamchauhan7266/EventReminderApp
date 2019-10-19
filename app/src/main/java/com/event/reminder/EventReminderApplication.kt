package com.event.reminder

import com.android.mvvmandroidlib.BaseApplication

class EventReminderApplication : BaseApplication() {

    companion object {

        private var applicationInstance: EventReminderApplication? = null
        fun getInstance(): EventReminderApplication {
            return applicationInstance ?: EventReminderApplication()
        }
    }

    override fun onCreate() {
        super.onCreate()
        applicationInstance = this
    }
}