package com.liuhepay.cuppayment.bean;

/**
 * Created by LEO on 2016/8/11.
 */
public class OthersInfoBean {

    private boolean others_manager_pwd;
    private boolean allow_input_cardnum;
    private boolean default_swipe_card_type;
    private String refund_max_quota;

    public boolean isOthers_manager_pwd() {
        return others_manager_pwd;
    }

    public void setOthers_manager_pwd(boolean others_manager_pwd) {
        this.others_manager_pwd = others_manager_pwd;
    }

    public boolean isAllow_input_cardnum() {
        return allow_input_cardnum;
    }

    public void setAllow_input_cardnum(boolean allow_input_cardnum) {
        this.allow_input_cardnum = allow_input_cardnum;
    }

    public boolean isDefault_swipe_card_type() {
        return default_swipe_card_type;
    }

    public void setDefault_swipe_card_type(boolean default_swipe_card_type) {
        this.default_swipe_card_type = default_swipe_card_type;
    }

    public String getRefund_max_quota() {
        return refund_max_quota;
    }

    public void setRefund_max_quota(String refund_max_quota) {
        this.refund_max_quota = refund_max_quota;
    }

    @Override
    public String toString() {
        return "OthersInfoBean{" +
                "\n  others_manager_pwd=" + others_manager_pwd +
                "\n  allow_input_cardnum=" + allow_input_cardnum +
                "\n  default_swipe_card_type=" + default_swipe_card_type +
                "\n  refund_max_quota='" + refund_max_quota +
                '}';
    }
}
