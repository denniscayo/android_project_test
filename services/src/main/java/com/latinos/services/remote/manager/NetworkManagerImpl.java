package com.latinos.services.remote.manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.latinos.services.remote.manager.error.RestNetworkException;

public class NetworkManagerImpl implements NetworkManager {

    /**
     * Checks if there is any active network
     */
    private static boolean isInternetAvailable(Context context) {
        NetworkInfo activeNetwork = getActiveNetwork(context);
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    /**
     * Checks if the active connection is {@link ConnectivityManager#TYPE_WIFI}
     */
    private static boolean isWifiConnected(Context context) {
        NetworkInfo activeNetwork = getActiveNetwork(context);
        return activeNetwork != null && activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * Get the active {@link NetworkInfo}
     */
    @SuppressLint("MissingPermission")
    private static NetworkInfo getActiveNetwork(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm != null ? cm.getActiveNetworkInfo() : null;
    }

    private Context context;

    public NetworkManagerImpl(Context context) {
        this.context = context;
    }

    /**
     * Checks if Internet is available or throws a {@link RestNetworkException}
     *
     * @throws RestNetworkException when internet is not available
     */
    public void checkConnectivity() throws RestNetworkException {
        if (!isInternetAvailable(context)) {
            throw new RestNetworkException("Not Available");
        }
    }

    /**
     * Returns if the active network is {@link ConnectivityManager#TYPE_WIFI}. If there is no internet will throw a {@link RestNetworkException}
     *
     * @throws RestNetworkException when internet is not available
     */
    public boolean checkWifi() throws RestNetworkException {
        checkConnectivity();
        return isWifiConnected(context);
    }
}