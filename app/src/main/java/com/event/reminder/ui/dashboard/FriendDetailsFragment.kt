package com.event.reminder.ui.dashboard

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.mvvmandroidlib.ui.BaseFragment
import com.event.reminder.R
import com.event.reminder.databinding.FriendDetailsFragmentBinding
import com.event.reminder.ui.ViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FriendDetailsFragment : BaseFragment<FriendDetailsFragmentBinding, FriendDetailsViewModel>() {
    override fun onCreateViewBinding() {
        binding.viewModel = viewModel
    }

    override fun getObservableViewModel(): FriendDetailsViewModel {
        return ViewModelProviders.of(this, ViewModelFactory())
            .get(FriendDetailsViewModel::class.java)
    }

    override fun getViewDataBinding(inflater: LayoutInflater, container: ViewGroup?): FriendDetailsFragmentBinding {
        return DataBindingUtil.inflate(
            inflater, R.layout.fragment_friend_details, container, false
        )
    }

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FriendDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FriendDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
