package com.event.reminder.ui.authentication.login

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.mvvmandroidlib.ui.BaseFragment
import com.android.mvvmandroidlib.utills.LoggerUtils
import com.event.reminder.R
import com.event.reminder.callback.INavigationCallback
import com.event.reminder.data.model.response.LoggedInUserModel
import com.event.reminder.databinding.LoginFragmentBinding
import com.event.reminder.enums.NavigationScreen
import com.event.reminder.ui.ViewModelFactory
import com.event.reminder.utills.EventReminderSharedPrefUtils

class LoginFragment : BaseFragment<LoginFragmentBinding, LoginViewModel>() {

    private val TAG: String = LoginFragment::class.java.simpleName
    private var iNavigationCallback: INavigationCallback? = null

    override fun getViewDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): LoginFragmentBinding {
        return DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )
    }

    override fun getObservableViewModel(): LoginViewModel {
        return ViewModelProviders.of(this, ViewModelFactory())
            .get(LoginViewModel::class.java)
    }

    override fun onCreateViewBinding() {
        binding.viewModel = viewModel
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        iNavigationCallback = context as INavigationCallback
    }

    override fun setInitialData() {
        super.setInitialData()

        viewModel.loginResult?.observe(this@LoginFragment, Observer {
            val result = it ?: return@Observer

            when {
                result.success != null -> {

                    val loggedInUser = result.success
                    if (loggedInUser?.success == true) {
                        savedDataToLocalStorage(loggedInUser)
                        iNavigationCallback?.navigateTo(NavigationScreen.DASHBOARD_SCREEN, null)
                    } else {
                        result.success!!.errorMessage?.let { error -> viewModel.failedEventErrorMessage.sendEvent(error) }
                    }
                }
                result.errorMessage != null -> {
                    result.errorMessage?.let { error -> viewModel.failedEventErrorMessage.sendEvent(error) }
                }
            }
        })

        viewModel.navigationEvent.observe(this@LoginFragment, Observer {

            when (it ?: return@Observer) {
                NavigationScreen.SIGN_UP_SCREEN -> {
                    iNavigationCallback?.navigateTo(NavigationScreen.SIGN_UP_SCREEN, null)
                }
                NavigationScreen.DASHBOARD_SCREEN -> {
                    iNavigationCallback?.navigateTo(NavigationScreen.DASHBOARD_SCREEN, null)
                }
                NavigationScreen.FORGET_PASSWORD_SCREEN -> {
                    iNavigationCallback?.navigateTo(NavigationScreen.FORGET_PASSWORD_SCREEN, null)
                }
                else -> {
                    LoggerUtils.error(TAG, getString(R.string.unknown_navigation))
                }
            }
        })

        binding.etPassword.apply {

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        viewModel.login()
                }
                false
            }
        }
    }

    /**
     * Method is used to set data to local storage.
     *
     *@param loggedInUser: logged in user details
     */
    private fun savedDataToLocalStorage(loggedInUser: LoggedInUserModel) {
        EventReminderSharedPrefUtils.setUserLoggedIn(true)
        loggedInUser.accessToken?.let { accessToken ->
            EventReminderSharedPrefUtils.setAccessToken(accessToken)
        }
        loggedInUser.userDetails?.userId?.let { userId ->
            EventReminderSharedPrefUtils.setUserId(userId)
        }
        loggedInUser.userDetails?.phoneNumber?.let { phoneNumber ->
            EventReminderSharedPrefUtils.setMobileNumber(phoneNumber)
        }
    }
}
