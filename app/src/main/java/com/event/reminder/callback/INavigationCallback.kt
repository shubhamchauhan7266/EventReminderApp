package com.event.reminder.callback

import com.event.reminder.constant.NavigationConstant

interface INavigationCallback {
    fun navigateTo(navigationConstant: NavigationConstant)
}