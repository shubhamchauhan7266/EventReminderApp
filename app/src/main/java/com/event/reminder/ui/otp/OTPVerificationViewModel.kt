package com.event.reminder.ui.otp

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.mvvmandroidlib.data.BaseResponseModel
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.utills.StringUtils
import com.android.mvvmandroidlib.viewmodel.BaseObservableViewModel
import com.event.reminder.BR
import com.event.reminder.data.model.request.GenerateOTPRequest
import com.event.reminder.data.model.request.ValidateOTPRequest
import com.event.reminder.data.repository.OTPVerificationRepository
import com.event.reminder.utills.EventReminderSharedPrefUtils

/**
 * This ViewModel class is worked as a Observable ViewModel that is responsible for preparing and managing
 * the data for an [android.app.Activity] or a [androidx.fragment.app.Fragment].
 * It also handles the communication of the Activity / Fragment with the rest of the application
 * (e.g. calling the business logic classes).
 *
 * @author Shubham Chauhan
 */
class OTPVerificationViewModel(private val otpVerificationRepository: OTPVerificationRepository) :
    BaseObservableViewModel() {

    private val _generateOTPResult: MutableLiveData<ApiResult<BaseResponseModel>> =
        MutableLiveData()
    val generateOTPResult: LiveData<ApiResult<BaseResponseModel>>? = _generateOTPResult
    private val _validateOTPResult: MutableLiveData<ApiResult<BaseResponseModel>> = MutableLiveData()
    val validateOTPResult: LiveData<ApiResult<BaseResponseModel>>? = _validateOTPResult

    var otpValue: String? = null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.otpValue)
        }

    fun validateOTP(otpSendTo: String?, otpType: Int) {
        if (!StringUtils.isNullOrEmpty(otpSendTo)) {
            val request = ValidateOTPRequest(
                userId = EventReminderSharedPrefUtils.getUserId(),
                otpValue = otpValue,
                otpSendTo = otpSendTo,
                otpType = otpType
            )
            otpVerificationRepository.validateOTP(request, _generateOTPResult)
        } else {
            failedEventErrorMessage.sendEvent("Email Id or Phone Number is empty!!")
        }
    }

    fun generateOTP(otpSendTo: String?, otpType: Int) {

        if (!StringUtils.isNullOrEmpty(otpSendTo)) {
            val request = GenerateOTPRequest(
                userId = EventReminderSharedPrefUtils.getUserId(),
                otpSendTo = otpSendTo,
                otpType = otpType
            )
            otpVerificationRepository.generateOTP(request, _validateOTPResult)
        } else {
            failedEventErrorMessage.sendEvent("Email Id or Phone Number is empty!!")
        }
    }
}