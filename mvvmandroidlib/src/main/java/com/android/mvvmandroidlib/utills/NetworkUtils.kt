package com.android.mvvmandroidlib.utills

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.support.annotation.RequiresPermission


object NetworkUtils {

    private val TAG: String = NetworkUtils::class.java.simpleName
    private const val ENABLED: String = "Enabled"
    private const val DISABLED: String = "Disabled"
    private const val NOT_SUPPORTED: String = "Not supported"

    @RequiresPermission(value = Manifest.permission.ACCESS_NETWORK_STATE)
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager?
        val activeNetworkInfo = connectivityManager?.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    /**
     * Checks if the device has Wifi connection.
     */
    @RequiresPermission(value = Manifest.permission.ACCESS_NETWORK_STATE)
    fun isWifiNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager?

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val wifiNetwork = connectivityManager?.activeNetwork
            wifiNetwork != null && connectivityManager?.getNetworkCapabilities(wifiNetwork)?.hasTransport(
                NetworkCapabilities.TRANSPORT_WIFI
            )!!
        } else {
            val wifiNetwork = connectivityManager?.activeNetworkInfo
            wifiNetwork != null && wifiNetwork.type == ConnectivityManager.TYPE_WIFI && wifiNetwork.isConnected
        }
    }

    /**
     * Checks if the device has 3G or other mobile data connection.
     */
    @RequiresPermission(value = Manifest.permission.ACCESS_NETWORK_STATE)
    fun isMobileNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager?

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val mobileNetwork = connectivityManager?.activeNetwork
            mobileNetwork != null && connectivityManager?.getNetworkCapabilities(mobileNetwork)?.hasTransport(
                NetworkCapabilities.TRANSPORT_CELLULAR
            )!!
        } else {
            val mobileNetwork = connectivityManager?.activeNetworkInfo
            mobileNetwork != null && mobileNetwork.type == ConnectivityManager.TYPE_MOBILE && mobileNetwork.isConnected
        }
    }

    /**
     * Checks if the device has bluetooth connection.
     */
    @RequiresPermission(allOf = [Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.BLUETOOTH])
    fun isBluetoothConnected(context: Context): Boolean {

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val connectivityManager =
                context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager?
            val bluetoothNetwork = connectivityManager?.activeNetwork
            bluetoothNetwork != null && connectivityManager?.getNetworkCapabilities(
                bluetoothNetwork
            )?.hasTransport(
                NetworkCapabilities.TRANSPORT_BLUETOOTH
            )!!
        } else {
            val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
            return bluetoothAdapter?.isEnabled ?: false
        }
    }

    /**
     * Checks if the device has bluetooth connection.
     */
    @RequiresPermission(value = Manifest.permission.ACCESS_NETWORK_STATE)
    fun isEthernetConnected(context: Context): Boolean {

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val connectivityManager =
                context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager?
            val ethernetNetwork = connectivityManager?.activeNetwork
            ethernetNetwork != null && connectivityManager?.getNetworkCapabilities(
                ethernetNetwork
            )?.hasTransport(
                NetworkCapabilities.TRANSPORT_ETHERNET
            )!!
        } else {
            return false
        }
    }

    @RequiresPermission(value = Manifest.permission.BLUETOOTH)
    private fun getBluetoothConnectionStatus(): String {
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        return if (bluetoothAdapter == null) {
            NOT_SUPPORTED
        } else {
            if (bluetoothAdapter.isEnabled) {
                ENABLED
            } else {
                DISABLED
            }
        }
    }
}
