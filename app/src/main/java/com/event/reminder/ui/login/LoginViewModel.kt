package com.event.reminder.ui.login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.Bindable
import android.util.Patterns
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.helper.EventLiveData
import com.android.mvvmandroidlib.viewmodel.BaseObservableViewModel
import com.event.reminder.BR
import com.event.reminder.R
import com.event.reminder.constant.AppConstant
import com.event.reminder.constant.NavigationConstant
import com.event.reminder.data.model.request.LoginRequest
import com.event.reminder.data.model.response.LoggedInUser
import com.event.reminder.data.repository.LoginRepository

class LoginViewModel(private val loginRepository: LoginRepository) : BaseObservableViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult: MutableLiveData<ApiResult<LoggedInUser>> = MutableLiveData()
    val loginResult: LiveData<ApiResult<LoggedInUser>>? = _loginResult

    val navigationEvent = EventLiveData<NavigationConstant>()

    var valid: Boolean? = null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.valid)
        }

    var userName: String? = null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.userName)
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
        val request = LoginRequest(userName, password)
        loginRepository.login(request, _loginResult)
    }

    fun forgetPassword() {

        navigationEvent.sendEvent(NavigationConstant.FORGET_PASSWORD_SCREEN)
    }

    fun signUp() {

        navigationEvent.sendEvent(NavigationConstant.SIGN_UP_SCREEN)
    }

    private fun loginDataChanged() {
        if (!isUserNameValid(userName)) {
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
        return if (username!!.contains(AppConstant.EMAIL_SYMBOL)) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            Patterns.PHONE.matcher(username).matches()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String?): Boolean {
        return password!!.length > 5
    }
}
