package com.event.reminder.utills

import androidx.databinding.InverseMethod
import com.event.reminder.R
import com.event.reminder.constant.EventType

object BindingUtils {

    @InverseMethod("buttonIdToEventType")
    fun eventTypeToButtonId(eventType: Int): Int {

        return when (eventType) {
            EventType.SELF_EVENT -> R.id.rb_self_event
            EventType.FRIEND_EVENT -> R.id.rb_friend_event
            EventType.GROUP_EVENT -> R.id.rb_group_event
            else -> R.id.rb_self_event
        }
    }

    fun buttonIdToEventType(selectedButtonId: Int): Int {
        return when (selectedButtonId) {
            R.id.rb_self_event -> EventType.SELF_EVENT
            R.id.rb_friend_event -> EventType.FRIEND_EVENT
            R.id.rb_group_event -> EventType.GROUP_EVENT
            else -> EventType.SELF_EVENT
        }
    }
}