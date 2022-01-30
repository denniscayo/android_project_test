package com.latinos.services.remote.manager

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.latinos.services.remote.manager.error.RestNetworkException

class NetworkManagerImpl(private val context: Context) : NetworkManager {
    /**
     * Checks if Internet is available or throws a [RestNetworkException]
     *
     * @throws RestNetworkException when internet is not available
     */
    @Throws(RestNetworkException::class)
    override fun checkConnectivity() {
        if (!isInternetAvailable(context)) {
            throw RestNetworkException("Not Available")
        }
    }

    /**
     * Returns if the active network is [ConnectivityManager.TYPE_WIFI]. If there is no internet will throw a [RestNetworkException]
     *
     * @throws RestNetworkException when internet is not available
     */
    @Throws(RestNetworkException::class)
    override fun checkWifi(): Boolean {
        checkConnectivity()
        return isWifiConnected(context)
    }

    companion object {
        /**
         * Checks if there is any active network
         */
        private fun isInternetAvailable(context: Context): Boolean {
            val activeNetwork = getActiveNetwork(context)
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }

        /**
         * Checks if the active connection is [ConnectivityManager.TYPE_WIFI]
         */
        private fun isWifiConnected(context: Context): Boolean {
            val activeNetwork = getActiveNetwork(context)
            return activeNetwork != null && activeNetwork.type == ConnectivityManager.TYPE_WIFI
        }

        /**
         * Get the active [NetworkInfo]
         */
        @SuppressLint("MissingPermission")
        private fun getActiveNetwork(context: Context): NetworkInfo? {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm?.activeNetworkInfo
        }
    }
}