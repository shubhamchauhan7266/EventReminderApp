package com.event.reminder.ui.authentication.login

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.helper.EventLiveData
import com.android.mvvmandroidlib.utills.StringUtils
import com.android.mvvmandroidlib.viewmodel.BaseObservableViewModel
import com.event.reminder.BR
import com.event.reminder.data.model.request.LoginRequest
import com.event.reminder.data.model.response.LoggedInUserModel
import com.event.reminder.data.repository.LoginRepository
import com.event.reminder.enums.NavigationScreen

/**
 * This ViewModel class is worked as a Observable ViewModel that is responsible for preparing and managing
 * the data for an [android.app.Activity] or a [androidx.fragment.app.Fragment].
 * It also handles the communication of the Activity / Fragment with the rest of the application
 * (e.g. calling the business logic classes).
 *
 * @author Shubham Chauhan
 */
class LoginViewModel(private val loginRepository: LoginRepository) : BaseObservableViewModel() {

    private val _loginResult: MutableLiveData<ApiResult<LoggedInUserModel>> = MutableLiveData()
    val loginResult: LiveData<ApiResult<LoggedInUserModel>>? = _loginResult

    val navigationEvent = EventLiveData<NavigationScreen>()

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

    /**
     * Method is used to login through credentials.
     */
    fun login() {
        // can be launched in a separate asynchronous job
        if (isDataValid()) {
            val request = LoginRequest(userName, password)
            loginRepository.login(request, _loginResult)
        }
    }

    /**
     * Method is used to open forget password screen.
     */
    fun forgetPassword() {

        navigationEvent.sendEvent(NavigationScreen.FORGET_PASSWORD_SCREEN)
    }

    /**
     * Method is used to open sign up screen.
     */
    fun signUp() {

        navigationEvent.sendEvent(NavigationScreen.SIGN_UP_SCREEN)
    }

    /**
     * Method is used to validate login credentials.
     *
     * @return true if valid otherwise false.
     */
    private fun isDataValid(): Boolean {
        return StringUtils.isPasswordValid(password) && !StringUtils.isNullOrEmpty(userName)
    }
}
