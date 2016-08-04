package com.liuhepay.cuppayment.util;

/**
 * Created by LEO on 2016/8/3.
 */
public class TradeInfo {

    private String username = "";
    private String userpwd = "";
    private static TradeInfo tradeInfo = null;

    public static TradeInfo getInstance(){
        if(tradeInfo == null){
            tradeInfo = new TradeInfo();
        }
        return tradeInfo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }
}
