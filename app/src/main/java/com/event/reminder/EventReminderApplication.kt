package com.event.reminder

import com.android.mvvmandroidlib.BaseApplication

class EventReminderApplication : BaseApplication() {

    companion object {

        fun getInstance(): EventReminderApplication {
            return EventReminderApplication()
        }
    }
}