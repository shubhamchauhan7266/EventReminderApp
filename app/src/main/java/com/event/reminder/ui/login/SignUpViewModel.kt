package com.event.reminder.ui.login

import android.arch.lifecycle.LiveData
import android.databinding.Bindable
import com.android.mvvmandroidlib.common.ApiResult
import com.android.mvvmandroidlib.data.BaseResponseModel
import com.android.mvvmandroidlib.viewmodel.BaseObservableViewModel
import com.event.reminder.BR
import com.event.reminder.data.model.request.SignUpRequest
import com.event.reminder.data.repository.SignUpRepository

class SignUpViewModel(private val signUpRepository: SignUpRepository) : BaseObservableViewModel() {

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

    fun signUp(): LiveData<ApiResult<BaseResponseModel>>? {

        val request = SignUpRequest(fullName, emailId, phoneNumber, password, gender, dateOfBirth, image = null)

        return signUpRepository.signUp(request)
    }
}
