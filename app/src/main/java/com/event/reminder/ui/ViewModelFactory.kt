package com.event.reminder.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.event.reminder.data.repository.*
import com.event.reminder.ui.authentication.AuthenticationViewModel
import com.event.reminder.ui.authentication.login.LoginViewModel
import com.event.reminder.ui.authentication.password.ForgetPasswordViewModel
import com.event.reminder.ui.authentication.signup.SignUpViewModel
import com.event.reminder.ui.authentication.splash.SplashViewModel
import com.event.reminder.ui.dashboard.DashboardViewModel
import com.event.reminder.ui.dashboard.friends.FriendsViewModel
import com.event.reminder.ui.dashboard.friends.friendrequest.RequestListViewModel
import com.event.reminder.ui.dashboard.friends.friends.FriendListViewModel
import com.event.reminder.ui.dashboard.home.HomeViewModel
import com.event.reminder.ui.dashboard.notification.NotificationViewModel
import com.event.reminder.ui.dashboard.profile.ProfileDetailsViewModel
import com.event.reminder.ui.otp.OTPVerificationViewModel

/**
 * ViewModel provider factory to instantiate ViewModel.
 * Required given ViewModel has a non-empty constructor
 *
 * @author Shubham Chauhan
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
            modelClass.isAssignableFrom(FriendsViewModel::class.java) -> FriendsViewModel(
                friendsRepository = FriendsRepository
            ) as T
            modelClass.isAssignableFrom(NotificationViewModel::class.java) -> NotificationViewModel(
                notificationRepository = NotificationRepository
            ) as T
            modelClass.isAssignableFrom(ProfileDetailsViewModel::class.java) -> ProfileDetailsViewModel(
                profileDetailsRepository = ProfileDetailsRepository
            ) as T
            modelClass.isAssignableFrom(RequestListViewModel::class.java) -> RequestListViewModel(
                requestListRepository = RequestListRepository
            ) as T
            modelClass.isAssignableFrom(FriendListViewModel::class.java) -> FriendListViewModel(
                friendListRepository = FriendListRepository
            ) as T
            modelClass.isAssignableFrom(OTPVerificationViewModel::class.java) -> OTPVerificationViewModel(
                otpVerificationRepository = OTPVerificationRepository
            ) as T
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> SplashViewModel(
                splashRepository = SplashRepository
            ) as T
            modelClass.isAssignableFrom(ForgetPasswordViewModel::class.java) -> ForgetPasswordViewModel(
                forgetPasswordRepository = ForgetPasswordRepository
            ) as T
            modelClass.isAssignableFrom(AuthenticationViewModel::class.java) -> AuthenticationViewModel(
                authenticationRepository = AuthenticationRepository
            ) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
