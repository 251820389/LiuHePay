package com.liuhepay.cuppayment.util;

import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by LEO on 2016/7/26.
 */
public class SharedPreferencesInfo {

    private static SharedPreferencesInfo info = null;
    private static SharedPreferences mSharedPreferences = null;
    private Context mContext;
    public static ArrayList<Object> switch_arrlist	= new ArrayList<Object>();

    private SharedPreferencesInfo(Context mContext){
            this.mContext = mContext;
    }
    public static SharedPreferencesInfo getInstance(Context context) {
        if (info == null) {
            info = new SharedPreferencesInfo(context);
            getShared(context);
        }
        return info;
    }

    public static SharedPreferences getShared(Context context) {

        mSharedPreferences = context.getSharedPreferences("liuhepay", Service.MODE_PRIVATE);
        return mSharedPreferences;
    }


    public void setSwitchInfo(int index, boolean check)
    {
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        edit.putBoolean((String) switch_arrlist.get(index), check);
        edit.commit();
    }

    public boolean getSwitchInfo(int index, boolean default_value)
    {
//        if (index >= 9 && !default_value)
//        {
//            default_value = true;
//        }
        return mSharedPreferences.getBoolean((String) switch_arrlist.get(index), default_value);
    }
}
