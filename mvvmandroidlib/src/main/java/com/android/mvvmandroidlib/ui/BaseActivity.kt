package com.android.mvvmandroidlib.ui

import android.arch.lifecycle.Observer
import android.content.Context
import android.databinding.ViewDataBinding
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.android.mvvmandroidlib.viewmodel.BaseObservableViewModel

abstract class BaseActivity<T : ViewDataBinding, V : BaseObservableViewModel> : AppCompatActivity() {

    protected lateinit var binding : T
    protected lateinit var viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.viewModel = getObservableViewModel()
        this.binding = getViewDataBinding()
        onCreateBinding()
        setupToolbar()
        initMembers()
    }

    protected abstract fun onCreateBinding()

    protected abstract fun getObservableViewModel(): V

    protected abstract fun getViewDataBinding(): T

    protected open fun setupToolbar() {

    }

    protected open fun onNetworkAvailable(network: Network) {

    }

    protected open fun onNetworkLost(network: Network) {

    }

    protected open fun initMembers(){
        viewModel.startProgressEvent.observe(this, Observer {
//            DialogUtils.showProgressDialog(this, it!!)
        })

        viewModel.stopProgressEvent.observe(this, Observer {
//            DialogUtils.hideProgressDialog()
        })

        viewModel.failedEvent.observe(this, Observer {
//            DialogUtils.hideProgressDialog()
        })
    }

    override fun onResume() {
        super.onResume()

        registerNetworkConnectivityCallback()
    }

    override fun onPause() {
        super.onPause()

        unRegisterNetworkConnectivityCallback()
    }

    private fun registerNetworkConnectivityCallback() {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val networkRequestBuilder = NetworkRequest.Builder()
        networkRequestBuilder.addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            networkRequestBuilder.addCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        }
        networkRequestBuilder.removeCapability(NetworkCapabilities.NET_CAPABILITY_NOT_VPN)
        val networkRequest = networkRequestBuilder.build()
        connectivityManager?.registerNetworkCallback(networkRequest, networkConnectivityCallback)
    }

    private fun unRegisterNetworkConnectivityCallback() {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        connectivityManager?.unregisterNetworkCallback(networkConnectivityCallback)
    }

    /**
     * Callback used to listen for data connectivity changes.
     */
    private val networkConnectivityCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            onNetworkAvailable(network)
        }

        override fun onLost(network: Network) {
            onNetworkLost(network)
        }
    }
}