package com.liuhepay.cuppayment.bean;

/**
 * Created by LEO on 2016/8/10.
 */
public class OfflineInfoBean {
    private boolean offlineSendWay;
    private String offlineSendCount;
    private String offlineAutoSendCount;

    public boolean getOfflineSendWay() {
        return offlineSendWay;
    }

    public void setOfflineSendWay(boolean offlineSendWay) {
        this.offlineSendWay = offlineSendWay;
    }

    public String getOfflineSendCount() {
        return offlineSendCount;
    }

    public void setOfflineSendCount(String offlineSendCount) {
        this.offlineSendCount = offlineSendCount;
    }

    public String getOfflineAutoSendCount() {
        return offlineAutoSendCount;
    }

    public void setOfflineAutoSendCount(String offlineAutoSendCount) {
        this.offlineAutoSendCount = offlineAutoSendCount;
    }

    @Override
    public String toString() {
        return "OfflineInfoBean{" +
                "\n  offlineWendWay='" + offlineSendWay +
                "\n  offlineSendCount='" + offlineSendCount +
                "\n  offlineAutoSendCount='" + offlineAutoSendCount +
                '}';
    }
}
