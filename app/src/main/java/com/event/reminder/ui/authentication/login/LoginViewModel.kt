package com.event.reminder.ui.authentication.login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.Bindable
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.helper.EventLiveData
import com.android.mvvmandroidlib.utills.StringUtils
import com.android.mvvmandroidlib.viewmodel.BaseObservableViewModel
import com.event.reminder.BR
import com.event.reminder.R
import com.event.reminder.constant.NavigationConstant
import com.event.reminder.data.model.request.LoginRequest
import com.event.reminder.data.model.response.LoggedInUserModel
import com.event.reminder.data.repository.LoginRepository

class LoginViewModel(private val loginRepository: LoginRepository) : BaseObservableViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult: MutableLiveData<ApiResult<LoggedInUserModel>> = MutableLiveData()
    val loginResult: LiveData<ApiResult<LoggedInUserModel>>? = _loginResult

    val navigationEvent = EventLiveData<NavigationConstant>()

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
        if (StringUtils.isNullOrEmpty(userName)) {
            _loginForm.value =
                LoginFormState(usernameError = R.string.invalid_username)
        } else if (!StringUtils.isPasswordValid(
                password,
                length = 4
            )
        ) {
            _loginForm.value =
                LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value =
                LoginFormState(isDataValid = true)
        }
    }
}
