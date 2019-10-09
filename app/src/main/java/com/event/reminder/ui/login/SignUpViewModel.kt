package com.event.reminder.ui.login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.Bindable
import com.android.mvvmandroidlib.data.BaseResponseModel
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.viewmodel.BaseObservableViewModel
import com.event.reminder.BR
import com.event.reminder.data.model.request.SignUpRequest
import com.event.reminder.data.repository.SignUpRepository

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

    fun signUp() {

        if (isDataValid()) {
            // TODO Change Request
            val request = SignUpRequest(
                firstName = fullName, emailAddress = emailId,
                phoneNumber = phoneNumber, password = password, gender = gender, imageUrl = null
            )
            signUpRepository.signUp(request, _signUpResult)
        }
    }

    fun isDataValid(): Boolean {
//        return !StringUtils.isNullOrEmpty(fullName) && StringUtils.isEmailValid(emailId) && StringUtils.isPhoneNumberValid(phoneNumber)
//                && StringUtils.isPasswordValid(password) && !StringUtils.isNullOrEmpty(gender) && !StringUtils.isNullOrEmpty(dateOfBirth)
        return true
    }
}
