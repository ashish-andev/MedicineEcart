package com.test.app.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.test.app.MyApplication;

public final class NetworkUtils {

    private NetworkUtils() {
        // This class is not publicly instantiable
    }

    public static boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) MyApplication.getInstance()
                .getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnected();
        }
        return false;
    }
}
