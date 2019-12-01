package com.event.reminder.callback

import android.os.Bundle
import com.event.reminder.enums.NavigationScreen

interface INavigationCallback {
    fun navigateTo(navigationScreen: NavigationScreen, bundle: Bundle?)
}