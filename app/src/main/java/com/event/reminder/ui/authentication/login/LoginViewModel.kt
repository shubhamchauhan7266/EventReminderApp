package com.event.reminder.ui.authentication.login

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.helper.EventLiveData
import com.android.mvvmandroidlib.utills.StringUtils
import com.android.mvvmandroidlib.viewmodel.BaseObservableViewModel
import com.event.reminder.BR
import com.event.reminder.constant.NavigationConstant
import com.event.reminder.data.model.request.LoginRequest
import com.event.reminder.data.model.response.LoggedInUserModel
import com.event.reminder.data.repository.LoginRepository

class LoginViewModel(private val loginRepository: LoginRepository) : BaseObservableViewModel() {

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
            notifyPropertyChanged(BR.password)
        }

    fun login() {
        // can be launched in a separate asynchronous job
        if (isDataValid()) {
            val request = LoginRequest(userName, password)
            loginRepository.login(request, _loginResult)
        }
    }

    fun forgetPassword() {

        navigationEvent.sendEvent(NavigationConstant.FORGET_PASSWORD_SCREEN)
    }

    fun signUp() {

        navigationEvent.sendEvent(NavigationConstant.SIGN_UP_SCREEN)
    }

    private fun isDataValid(): Boolean {
        return StringUtils.isPasswordValid(password) && !StringUtils.isNullOrEmpty(userName)
    }
}
