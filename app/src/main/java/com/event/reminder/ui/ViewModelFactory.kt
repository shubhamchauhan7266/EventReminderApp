package com.event.reminder.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.event.reminder.data.repository.HomeRepository
import com.event.reminder.data.repository.LoginRepository
import com.event.reminder.data.repository.SignUpRepository
import com.event.reminder.ui.dashboard.HomeViewModel
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
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(
                homeRepository = HomeRepository
            ) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
