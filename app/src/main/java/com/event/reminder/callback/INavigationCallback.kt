package com.event.reminder.callback

import android.os.Bundle
import com.event.reminder.constant.NavigationConstant

interface INavigationCallback {
    fun navigateTo(navigationConstant: NavigationConstant, bundle: Bundle?)
}