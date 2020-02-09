package com.event.reminder.ui.dashboard.home

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.mvvmandroidlib.ui.BaseFragment
import com.event.reminder.R
import com.event.reminder.adapter.HomeEventListAdapter
import com.event.reminder.callback.INavigationCallback
import com.event.reminder.constant.EventsType
import com.event.reminder.data.model.response.HomeEventDetails
import com.event.reminder.databinding.HomeFragmentBinding
import com.event.reminder.enums.NavigationScreen
import com.event.reminder.ui.ViewModelFactory

/**
 * This Fragment Class is used to provide layout and lifecycle callback to handle UI for home screen.
 *
 * @author Shubham Chauhan
 */
class HomeFragment : BaseFragment<HomeFragmentBinding, HomeViewModel>() {

    private var iNavigationCallback: INavigationCallback? = null

    override fun onCreateViewBinding(savedInstanceState: Bundle?) {
        binding.viewModel = viewModel

        initializeAdapter()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        iNavigationCallback = context as INavigationCallback
    }

    override fun setupToolbar() {
        super.setupToolbar()
        setHasOptionsMenu(true)
        val activity = activity as AppCompatActivity?
        activity?.setSupportActionBar(binding.toolbar)
        activity?.supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    /**
     * Method is used to initialize adapter list
     */
    private fun initializeAdapter() {
        val homeEventList = ArrayList<HomeEventDetails>()
        homeEventList.add(
            HomeEventDetails(
                description = "You have a journey for Jaipur",
                eventType = EventsType.NORMAL
            )
        )
        homeEventList.add(
            HomeEventDetails(
                description = "Take a tablet",
                eventType = EventsType.NORMAL
            )
        )
        homeEventList.add(
            HomeEventDetails(
                description = "Please send screenshot in office group",
                eventType = EventsType.NORMAL
            )
        )

        binding.rvHomeEventList.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(activity)
        binding.rvHomeEventList.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        binding.rvHomeEventList.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                activity,
                androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
            )
        )
        binding.rvHomeEventList.adapter = HomeEventListAdapter(homeEventList)
    }

    override fun setInitialData() {
        super.setInitialData()
        viewModel.navigationEvent.observe(this@HomeFragment, Observer { navigation ->

            when (navigation) {
                NavigationScreen.PROFILE_SCREEN -> {
                    iNavigationCallback?.navigateTo(NavigationScreen.PROFILE_SCREEN, null)
                }
                else -> {

                }
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu_option, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getObservableViewModel(): HomeViewModel {
        return ViewModelProviders.of(this, ViewModelFactory())
            .get(HomeViewModel::class.java)
    }

    override fun getViewDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): HomeFragmentBinding {
        return DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )
    }
}
