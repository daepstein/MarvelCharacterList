package com.daepstein.marvel.marvelcharacters.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by daepstein on 1/30/2017.
 */

public class NetworkUtility {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    Context context;

    public NetworkUtility(Context c) {
        context = c;
    }

    public boolean connectionError() {
        if (!NetworkUtility.isNetworkAvailable(context)) {
            return true;
        }

        return false;
    }



}
