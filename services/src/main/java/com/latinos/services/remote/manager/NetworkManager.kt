package com.latinos.services.remote.manager

import com.latinos.services.remote.manager.error.RestNetworkException

interface NetworkManager {
    @Throws(RestNetworkException::class)
    fun checkConnectivity()

    /**
     * Returns if the active network is TYPE_WIFI. If there is no internet will throw a [ ]
     */
    @Throws(RestNetworkException::class)
    fun checkWifi(): Boolean
}