package com.event.reminder.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.event.reminder.data.repository.*
import com.event.reminder.ui.dashboard.DashboardViewModel
import com.event.reminder.ui.dashboard.friends.FriendDetailsViewModel
import com.event.reminder.ui.dashboard.home.HomeViewModel
import com.event.reminder.ui.dashboard.notification.NotificationViewModel
import com.event.reminder.ui.dashboard.profile.ProfileDetailsViewModel
import com.event.reminder.ui.login.LoginViewModel
import com.event.reminder.ui.login.SignUpViewModel

/**
 * ViewModel provider factory to instantiate ViewModel.
 * Required given ViewModel has a non-empty constructor
 */
class ViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(
                loginRepository = LoginRepository
            ) as T
            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> SignUpViewModel(
                signUpRepository = SignUpRepository
            ) as T
            modelClass.isAssignableFrom(DashboardViewModel::class.java) -> DashboardViewModel(
                dashboardRepository = DashboardRepository
            ) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(
                homeRepository = HomeRepository
            ) as T
            modelClass.isAssignableFrom(FriendDetailsViewModel::class.java) -> FriendDetailsViewModel(
                friendDetailsRepository = FriendDetailsRepository
            ) as T
            modelClass.isAssignableFrom(NotificationViewModel::class.java) -> NotificationViewModel(
                notificationRepository = NotificationRepository
            ) as T
            modelClass.isAssignableFrom(ProfileDetailsViewModel::class.java) -> ProfileDetailsViewModel(
                profileDetailsRepository = ProfileDetailsRepository
            ) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
