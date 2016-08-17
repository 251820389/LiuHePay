package com.liuhepay.cuppayment.util;

import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;

import com.liuhepay.cuppayment.bean.MerInfoBean;
import com.liuhepay.cuppayment.bean.OfflineInfoBean;
import com.liuhepay.cuppayment.bean.OthersInfoBean;

import java.util.ArrayList;

/**
 * Created by LEO on 2016/7/26.
 */
public class SharedPreferencesInfo {

    private static SharedPreferencesInfo info = null;
    private static SharedPreferences mSharedPreferences = null;
    private static SharedPreferences.Editor edit = null;
    public static ArrayList<Object> switch_arrlist	= new ArrayList<Object>();

    public static SharedPreferencesInfo getInstance(Context context) {
        if (info == null) {
            info = new SharedPreferencesInfo();
            getShared(context);
        }
        return info;
    }

    public static SharedPreferences getShared(Context context) {
        mSharedPreferences = context.getSharedPreferences("liuhepay", Service.MODE_PRIVATE);
        return mSharedPreferences;
    }

    public void setMerInfo(MerInfoBean merInfo){
        edit = mSharedPreferences.edit();
        edit.putString("MID",merInfo.getMid());
        edit.putString("TID",merInfo.getTid());
        edit.putString("firmId",merInfo.getFirmid());
        edit.commit();
    }

    public MerInfoBean getMerInfo(){
        MerInfoBean merInfo = new MerInfoBean();
        merInfo.setMid(mSharedPreferences.getString("MID", ""));
        merInfo.setTid(mSharedPreferences.getString("TID", ""));
        merInfo.setFirmid(mSharedPreferences.getString("firmId",""));
        return merInfo;
    }
    public void setOfflineInfo(OfflineInfoBean offlineInfo){
        edit = mSharedPreferences.edit();
        edit.putBoolean("offlineSendWay", offlineInfo.getOfflineSendWay());
        edit.putString("offlineSendCount",offlineInfo.getOfflineSendCount());
        edit.putString("offlineAutoSendCount",offlineInfo.getOfflineAutoSendCount());
        edit.commit();
    }

    public OfflineInfoBean getOfflineInfo(){
        OfflineInfoBean offlineInfo = new OfflineInfoBean();
        offlineInfo.setOfflineSendWay(mSharedPreferences.getBoolean("offlineSendWay", true));
        offlineInfo.setOfflineSendCount(mSharedPreferences.getString("offlineSendCount", "2"));
        offlineInfo.setOfflineAutoSendCount(mSharedPreferences.getString("offlineAutoSendCount", "5"));
        return offlineInfo;
    }
    public void setOthersInfo(OthersInfoBean othersInfo){
        edit = mSharedPreferences.edit();
        edit.putBoolean("isInputManagerPwd",othersInfo.isOthers_manager_pwd());
        edit.putBoolean("isAllowInputCardnum", othersInfo.isAllow_input_cardnum());
        edit.putBoolean("isDefaultSwipeCardType", othersInfo.isDefault_swipe_card_type());
        edit.putString("refundMaxQuota", othersInfo.getRefund_max_quota());
        edit.commit();
    }

    public OthersInfoBean getOthersInfo(){
        OthersInfoBean othersInfo = new OthersInfoBean();
        othersInfo.setOthers_manager_pwd(mSharedPreferences.getBoolean("isInputManagerPwd", true));
        othersInfo.setAllow_input_cardnum(mSharedPreferences.getBoolean("isAllowInputCardnum", true));
        othersInfo.setDefault_swipe_card_type(mSharedPreferences.getBoolean("isDefaultSwipeCardType", true));
        othersInfo.setRefund_max_quota(mSharedPreferences.getString("refundMaxQuota", "5000"));
        return othersInfo;
    }
    public void setSwitchInfo(int index, boolean check)
    {
        edit = mSharedPreferences.edit();
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
