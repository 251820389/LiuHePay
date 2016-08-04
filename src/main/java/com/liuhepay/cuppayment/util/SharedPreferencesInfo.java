package com.liuhepay.cuppayment.util;

import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by LEO on 2016/7/26.
 */
public class SharedPreferencesInfo {

    private static SharedPreferencesInfo info = null;
    private static SharedPreferences shared = null;
    public static SharedPreferencesInfo getContext() {
        if (info == null) {
            info = new SharedPreferencesInfo();
        }
        return info;
    }

    public static SharedPreferences getShared(Context context) {

        shared = context.getSharedPreferences("liuhepay", Service.MODE_PRIVATE);
        return shared;
    }

    public static void initData(){

    }
}
