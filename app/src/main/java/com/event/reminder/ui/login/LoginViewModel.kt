package com.event.reminder.ui.login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.Bindable
import android.util.Patterns
import com.android.mvvmandroidlib.common.Result
import com.android.mvvmandroidlib.viewmodel.BaseObservableViewModel
import com.event.reminder.BR
import com.event.reminder.R
import com.event.reminder.data.model.response.LoggedInUser
import com.event.reminder.data.repository.LoginRepository

class LoginViewModel(private val loginRepository: LoginRepository) : BaseObservableViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoggedInUser>()
    val loginResult: LiveData<LoggedInUser> = _loginResult

    var valid: Boolean? = null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.valid)
        }

    var username: String? = null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.username)
        }

    var password: String? = null
        @Bindable get
        set(value) {
            field = value
            valid = false
            loginDataChanged()
            notifyPropertyChanged(BR.password)
        }

    fun login() {
        // can be launched in a separate asynchronous job
        val result = username?.let { password?.let { it1 -> loginRepository.login(it, it1) } }

        if (result is Result.Success) {
            _loginResult.value = result.data
        } else {
            failedEvent.sendEvent(R.string.login_failed)
        }
    }

    private fun loginDataChanged() {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            valid = true
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String?): Boolean {
        return if (username!!.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String?): Boolean {
        return password!!.length > 5
    }
}
