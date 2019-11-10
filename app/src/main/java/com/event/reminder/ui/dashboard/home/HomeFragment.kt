package com.event.reminder.ui.dashboard.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.android.mvvmandroidlib.ui.BaseFragment
import com.event.reminder.R
import com.event.reminder.adapter.HomeEventListAdapter
import com.event.reminder.callback.INavigationCallback
import com.event.reminder.constant.NavigationConstant
import com.event.reminder.data.model.response.HomeEventDetails
import com.event.reminder.databinding.HomeFragmentBinding
import com.event.reminder.enum.EventsType
import com.event.reminder.ui.ViewModelFactory


class HomeFragment : BaseFragment<HomeFragmentBinding, HomeViewModel>() {

    private var iNavigationCallback: INavigationCallback? = null

    override fun onCreateViewBinding() {
        binding.viewModel = viewModel

        initializeAdapter()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        iNavigationCallback = context as INavigationCallback
    }

    override fun setupToolbar() {
        super.setupToolbar()
        setHasOptionsMenu(true)
        val activity = activity as AppCompatActivity?
        activity?.setSupportActionBar(binding.toolbar)
        activity?.supportActionBar!!.setDisplayShowTitleEnabled(false)
    }

    /**
     * Method is used to initialize adapter list
     */
    private fun initializeAdapter() {
        val homeEventList = ArrayList<HomeEventDetails>()
        homeEventList.add(
            HomeEventDetails(
                description = "You have a journey for Jaipur",
                eventType = EventsType.NORMAL.ordinal
            )
        )
        homeEventList.add(
            HomeEventDetails(
                description = "Take a tablet",
                eventType = EventsType.NORMAL.ordinal
            )
        )
        homeEventList.add(
            HomeEventDetails(
                description = "Please send screenshot in office group",
                eventType = EventsType.NORMAL.ordinal
            )
        )

        binding.rvHomeEventList.layoutManager = LinearLayoutManager(activity)
        binding.rvHomeEventList.itemAnimator = DefaultItemAnimator()
        binding.rvHomeEventList.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.rvHomeEventList.adapter = HomeEventListAdapter(homeEventList)
    }

    override fun setInitialData() {
        super.setInitialData()
        viewModel.navigationEvent.observe(this@HomeFragment, Observer { navigation ->

            when (navigation) {
                NavigationConstant.PROFILE_SCREEN -> {
                    iNavigationCallback?.navigateTo(NavigationConstant.PROFILE_SCREEN, null)
                }
                else -> {

                }
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.home_menu_option, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
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
