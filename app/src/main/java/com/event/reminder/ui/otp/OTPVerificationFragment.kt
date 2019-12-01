package com.event.reminder.ui.otp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.mvvmandroidlib.ui.BaseFragment
import com.android.mvvmandroidlib.utills.LoggerUtils
import com.event.reminder.R
import com.event.reminder.callback.INavigationCallback
import com.event.reminder.constant.BroadcastReceiverAction
import com.event.reminder.constant.BundleArgsConstant
import com.event.reminder.databinding.OTPVerificationFragmentBinding
import com.event.reminder.enums.NavigationScreen
import com.event.reminder.enums.OTPType
import com.event.reminder.ui.ViewModelFactory
import com.google.android.gms.auth.api.phone.SmsRetriever


class OTPVerificationFragment :
    BaseFragment<OTPVerificationFragmentBinding, OTPVerificationViewModel>() {

    private val TAG = OTPVerificationFragment::class.java.simpleName
    private var iNavigationCallback: INavigationCallback? = null
    private var emailId: String? = null
    private var mobileNumber: String? = null

    override fun onCreateViewBinding() {
        binding.viewModel = viewModel
        emailId = arguments?.getString(BundleArgsConstant.EMAIL_ID)
        mobileNumber = arguments?.getString(BundleArgsConstant.MOBILE_NUMBER)

        viewModel.generateOTP(emailId, OTPType.EMAIL_ID.ordinal)
    }

    override fun getObservableViewModel(): OTPVerificationViewModel {
        return ViewModelProviders.of(this, ViewModelFactory())
            .get(OTPVerificationViewModel::class.java)
    }

    override fun getViewDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): OTPVerificationFragmentBinding {
        return DataBindingUtil.inflate(
            inflater, R.layout.fragment_otpverification, container, false
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        iNavigationCallback = context as INavigationCallback
    }

    override fun setInitialData() {
        super.setInitialData()

        binding.tvResendOtp.setOnClickListener {
            viewModel.generateOTP(emailId, OTPType.EMAIL_ID.ordinal)
        }
        binding.btSubmit.setOnClickListener {
            viewModel.validateOTP(emailId, OTPType.EMAIL_ID.ordinal)
        }

        viewModel.validateOTPResult!!.observe(this@OTPVerificationFragment, Observer {
            val result = it ?: return@Observer

            when {
                result.success != null -> {

                    if (result.success!!.success) {
                        iNavigationCallback?.navigateTo(NavigationScreen.LOGIN_SCREEN, null)
                    } else {
                        result.success!!.errorMessage?.let { error ->
                            viewModel.failedEventErrorMessage.sendEvent(
                                error
                            )
                        }
                    }
                }
                result.errorMessage != null -> {
                    result.errorMessage?.let { error ->
                        viewModel.failedEventErrorMessage.sendEvent(
                            error
                        )
                    }
                }
            }
        })

        viewModel.generateOTPResult!!.observe(this@OTPVerificationFragment, Observer {
            val result = it ?: return@Observer

            when {
                result.success != null -> {

                    if (result.success!!.success) {
                        startSMSRetrieverAPI()
                    } else {
                        result.success!!.errorMessage?.let { error ->
                            viewModel.failedEventErrorMessage.sendEvent(
                                error
                            )
                        }
                    }
                }
                result.errorMessage != null -> {
                    result.errorMessage?.let { error ->
                        viewModel.failedEventErrorMessage.sendEvent(
                            error
                        )
                    }
                }
            }
        })
    }

    /**
     * Method is used to start SMS Retriever API.
     */
    private fun startSMSRetrieverAPI() {
        val client = SmsRetriever.getClient(activity!!)
        val task = client.startSmsRetriever()

        task.addOnSuccessListener {
            // Successfully started retriever, expect broadcast intent
            registerReceiver()
        }

        task.addOnFailureListener {
            // Failed to start retriever, inspect Exception for more details
            unRegisterReceiver()
        }
    }

    private val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

            val bundle = intent.extras
            when (intent.action) {
                BroadcastReceiverAction.OTP_READ_SUCCESS -> {
                    val otpMessage = bundle?.getString(BundleArgsConstant.OTP_MESSAGE)
                    viewModel.otpValue = getOTPFromMessage(otpMessage)
                    viewModel.validateOTP(mobileNumber, OTPType.MOBILE_NUMBER.ordinal)
                }
                BroadcastReceiverAction.OTP_READ_TIMEOUT -> {
                    val errorMessage = bundle?.getString(BundleArgsConstant.OTP_ERROR_MESSAGE)
                    viewModel.failedEventErrorMessage.sendEvent(
                        errorMessage ?: getString(R.string.unknown_error)
                    )
                }
            }
        }
    }

    /**
     * Method is used to extract OTP from SMS.
     *
     * @param message a SMS read by phone
     */
    private fun getOTPFromMessage(message: String?): String {
        return "899789"  // TODO Change the logic to extract the otp from message.
    }

    override fun onPause() {
        super.onPause()
        unRegisterReceiver()
    }

    /**
     * Method is used to register receiver to read SMS data.
     */
    private fun registerReceiver() {
        try {
            val intentFilter = IntentFilter()
            intentFilter.addAction(BroadcastReceiverAction.OTP_READ_SUCCESS)
            intentFilter.addAction(BroadcastReceiverAction.OTP_READ_TIMEOUT)
            activity?.registerReceiver(
                broadcastReceiver, intentFilter
            )
        } catch (e: Exception) {
            LoggerUtils.error(TAG, e.localizedMessage)
        }
    }

    /**
     * Method is used to un register receiver.
     */
    private fun unRegisterReceiver() {
        try {
            activity?.unregisterReceiver(broadcastReceiver)
        } catch (e: Exception) {
            LoggerUtils.error(TAG, e.localizedMessage)
        }
    }
}
