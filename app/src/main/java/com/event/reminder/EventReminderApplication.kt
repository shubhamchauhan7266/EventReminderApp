package com.event.reminder

import com.android.mvvmandroidlib.BaseApplication

/**
 * This application class is instantiated before any other class when the process for your
 * application/package is created.
 * This class also for maintaining global application state.
 *
 * @author Shubham Chauhan
 */
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