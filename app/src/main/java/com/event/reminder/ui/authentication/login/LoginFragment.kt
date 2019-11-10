package com.event.reminder.ui.authentication.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.android.mvvmandroidlib.ui.BaseFragment
import com.android.mvvmandroidlib.utills.LoggerUtils
import com.event.reminder.R
import com.event.reminder.callback.INavigationCallback
import com.event.reminder.constant.NavigationConstant
import com.event.reminder.data.model.response.LoggedInUserModel
import com.event.reminder.databinding.LoginFragmentBinding
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

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        iNavigationCallback = context as INavigationCallback
    }

    override fun setInitialData() {
        super.setInitialData()

        viewModel.loginFormState.observe(this@LoginFragment, Observer {
            val loginState = it ?: return@Observer

            if(loginState.isDataValid){
                binding.ilUsername.isErrorEnabled = false
                binding.ilPassword.isErrorEnabled = false
            }else{
                if (loginState.usernameError != null) {
                    binding.ilUsername.isErrorEnabled = true
                    binding.ilUsername.error = getString(loginState.usernameError)
                }
                if (loginState.passwordError != null) {
                    binding.ilPassword.isErrorEnabled = true
                    binding.ilPassword.error = getString(loginState.passwordError)
                }
            }

        })

        viewModel.loginResult?.observe(this@LoginFragment, Observer {
            val result = it ?: return@Observer

            when {
                result.success != null -> {

                    val loggedInUser = result.success
                    if (loggedInUser?.success == true) {
                        savedDataToLocalStorage(loggedInUser)
                        iNavigationCallback?.navigateTo(NavigationConstant.DASHBOARD_SCREEN)
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
                NavigationConstant.SIGN_UP_SCREEN -> {
                    iNavigationCallback?.navigateTo(NavigationConstant.SIGN_UP_SCREEN)
                }
                NavigationConstant.DASHBOARD_SCREEN -> {
                    iNavigationCallback?.navigateTo(NavigationConstant.DASHBOARD_SCREEN)
                }
                NavigationConstant.FORGET_PASSWORD_SCREEN -> {
                    iNavigationCallback?.navigateTo(NavigationConstant.FORGET_PASSWORD_SCREEN)
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
    }
}