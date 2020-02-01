package com.event.reminder.ui.authentication.signup

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.mvvmandroidlib.data.BaseResponseModel
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.utills.StringUtils
import com.android.mvvmandroidlib.viewmodel.BaseObservableViewModel
import com.event.reminder.BR
import com.event.reminder.data.model.request.SignUpRequest
import com.event.reminder.data.repository.SignUpRepository

/**
 * This ViewModel class is worked as a Observable ViewModel that is responsible for preparing and managing
 * the data for an [android.app.Activity] or a [androidx.fragment.app.Fragment].
 * It also handles the communication of the Activity / Fragment with the rest of the application
 * (e.g. calling the business logic classes).
 *
 * @author Shubham Chauhan
 */
class SignUpViewModel(private val signUpRepository: SignUpRepository) : BaseObservableViewModel() {

    private val _signUpResult: MutableLiveData<ApiResult<BaseResponseModel>> = MutableLiveData()
    val signUpResult: LiveData<ApiResult<BaseResponseModel>>? = _signUpResult

    var fullName: String? = null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.fullName)
        }

    var emailId: String? = null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.emailId)
        }

    var dateOfBirth: String? = null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.dateOfBirth)
        }

    var gender: String? = null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.gender)
        }

    var phoneNumber: String? = null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.phoneNumber)
        }

    var password: String? = null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.password)
        }

    var confirmPassword: String? = null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.confirmPassword)
        }

    /**
     * Method is used to sign up or Registration to create a user.
     */
    fun signUp() {

        if (isDataValid()) {
            // TODO Change Request
            val request = SignUpRequest(
                firstName = fullName, emailAddress = emailId,
                phoneNumber = phoneNumber, password = password, gender = gender
            )
            signUpRepository.signUp(request, _signUpResult)
        }
    }

    /**
     * Method is used to validate user details.
     *
     * @return true if valid otherwise false.
     */
    private fun isDataValid(): Boolean {
        return StringUtils.isPhoneNumberValid(phoneNumber)
                && !StringUtils.isNullOrEmpty(fullName)
                && !StringUtils.isNullOrEmpty(dateOfBirth)
                && !StringUtils.isNullOrEmpty(gender)
                && StringUtils.isEmailIdValid(emailId)
                && StringUtils.isPasswordValid(password)
                && StringUtils.isPasswordValid(confirmPassword)
    }
}
