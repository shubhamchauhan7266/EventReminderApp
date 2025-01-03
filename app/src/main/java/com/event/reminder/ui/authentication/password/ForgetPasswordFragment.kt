package com.event.reminder.ui.authentication.password


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.android.mvvmandroidlib.ui.BaseFragment
import com.event.reminder.R
import com.event.reminder.callback.INavigationCallback
import com.event.reminder.databinding.ForgetPasswordFragmentBinding
import com.event.reminder.ui.ViewModelFactory

/**
 * This Fragment Class is used to provide layout and lifecycle callback to handle UI for forget password
 * screen.
 *
 * @author Shubham Chauhan
 */
class ForgetPasswordFragment :
    BaseFragment<ForgetPasswordFragmentBinding, ForgetPasswordViewModel>() {

    private var iNavigationCallback: INavigationCallback? = null

    override fun onCreateViewBinding(savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
    }

    override fun getObservableViewModel(): ForgetPasswordViewModel {
        return ViewModelProviders.of(this, ViewModelFactory())
            .get(ForgetPasswordViewModel::class.java)
    }

    override fun getViewDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ForgetPasswordFragmentBinding {
        return DataBindingUtil.inflate(
            inflater, R.layout.fragment_forget_password, container, false
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        iNavigationCallback = context as INavigationCallback
    }
}
