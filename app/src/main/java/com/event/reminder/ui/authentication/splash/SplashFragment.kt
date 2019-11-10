package com.event.reminder.ui.authentication.splash

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.mvvmandroidlib.ui.BaseFragment
import com.event.reminder.R
import com.event.reminder.callback.INavigationCallback
import com.event.reminder.constant.NavigationConstant
import com.event.reminder.databinding.SplashFragmentBinding
import com.event.reminder.ui.ViewModelFactory
import com.event.reminder.utills.EventReminderSharedPrefUtils

class SplashFragment : BaseFragment<SplashFragmentBinding, SplashViewModel>() {

    private var iNavigationCallback: INavigationCallback? = null

    override fun onCreateViewBinding() {
        binding.viewModel = viewModel
    }

    override fun getObservableViewModel(): SplashViewModel {
        return ViewModelProviders.of(this, ViewModelFactory())
            .get(SplashViewModel::class.java)
    }

    override fun getViewDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): SplashFragmentBinding {
        return DataBindingUtil.inflate(
            inflater, R.layout.fragment_splash, container, false
        )
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        iNavigationCallback = context as INavigationCallback
    }

    override fun setInitialData() {
        super.setInitialData()

        if (EventReminderSharedPrefUtils.isUserLoggedIn()/*true*/) {
            iNavigationCallback?.navigateTo(NavigationConstant.SPLASH_TO_DASHBOARD_SCREEN)
        }else{
            iNavigationCallback?.navigateTo(NavigationConstant.SPLASH_TO_LOGIN_SCREEN)
        }
    }
}
