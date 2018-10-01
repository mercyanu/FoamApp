package com.amolexis.foamo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import static android.content.Context.CONNECTIVITY_SERVICE;

public class Network {

    public static boolean checkInternet(Context context) {
        int[] connTypes = {ConnectivityManager.TYPE_WIFI, ConnectivityManager.TYPE_MOBILE};

        try {
            ConnectivityManager connectManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);

            NetworkInfo ntwrkInfo = connectManager.getActiveNetworkInfo();

            for (int connType : connTypes) {
                if (ntwrkInfo != null && ntwrkInfo.getType() == connType) {
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
