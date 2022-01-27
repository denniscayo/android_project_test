package com.latinos.services.remote.manager

import com.latinos.services.remote.manager.error.BSANNetworkException

interface NetworkManager {
    @Throws(BSANNetworkException::class)
    fun checkConnectivity()

    /**
     * Returns if the active network is TYPE_WIFI. If there is no internet will throw a [ ]
     */
    @Throws(BSANNetworkException::class)
    fun checkWifi(): Boolean
}