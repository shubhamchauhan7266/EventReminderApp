package com.event.reminder.callback

import android.os.Bundle
import com.event.reminder.enums.NavigationScreen

/**
 * This callback is used for navigation.
 *
 * @author Shubham Chauhan
 */
interface INavigationCallback {
    fun navigateTo(navigationScreen: NavigationScreen, bundle: Bundle?)
}