package com.event.reminder.ui.otp

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.Bindable
import com.android.mvvmandroidlib.data.BaseResponseModel
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.viewmodel.BaseObservableViewModel
import com.event.reminder.BR
import com.event.reminder.data.model.request.GetOTPRequest
import com.event.reminder.data.model.request.ValidateOTPRequest
import com.event.reminder.data.repository.OTPVerificationRepository
import com.event.reminder.utills.EventReminderSharedPrefUtils

class OTPVerificationViewModel(private val otpVerificationRepository: OTPVerificationRepository) :
    BaseObservableViewModel() {

    private val _getOTPResult: MutableLiveData<ApiResult<BaseResponseModel>> = MutableLiveData()
    val getOTPResult: LiveData<ApiResult<BaseResponseModel>>? = _getOTPResult
    private val _validateOTPResult: MutableLiveData<ApiResult<BaseResponseModel>> = MutableLiveData()
    val validateOTPResult: LiveData<ApiResult<BaseResponseModel>>? = _validateOTPResult

    var otpValue: String? = null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.otpValue)
        }

    fun onSubmitClick(){
        val request = ValidateOTPRequest(
            userId = EventReminderSharedPrefUtils.getUserId(),
            otpValue = otpValue,
            otpSendTo = ""
        )
        otpVerificationRepository.validateOTP(request, _getOTPResult);
    }

    fun onResendOTPClick(){
        val request = GetOTPRequest(userId = EventReminderSharedPrefUtils.getUserId(), otpSendTo = "shubham@gmail.com")
        otpVerificationRepository.getOTP(request, _validateOTPResult);
    }
}