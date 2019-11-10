package com.event.reminder.ui.authentication

import com.android.mvvmandroidlib.viewmodel.BaseObservableViewModel
import com.event.reminder.data.repository.AuthenticationRepository

class AuthenticationViewModel(private val authenticationRepository: AuthenticationRepository) :
    BaseObservableViewModel() {
}