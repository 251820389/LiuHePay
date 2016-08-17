package com.liuhepay.cuppayment.bean;

/**
 * Created by LEO on 2016/8/10.
 */
public class MerInfoBean {
    private String mid;
    private String tid;
    private String firmid;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getFirmid() {
        return firmid;
    }

    public void setFirmid(String firmid) {
        this.firmid = firmid;
    }

    @Override
    public String toString() {
        return "MerInfoBean{" +
                "\n  mid='" + mid +
                "\n  tid='" + tid +
                "\n  firmid='" + firmid +
                '}';
    }
}
