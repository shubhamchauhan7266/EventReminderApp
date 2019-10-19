package com.event.reminder.ui.login

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
        if (StringUtils.isNullOrEmpty(userName)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!StringUtils.isPasswordValid(
                password,
                type = StringUtils.PasswordType.ONLY_DIGIT
            )
        ) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            valid = true
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }
}
