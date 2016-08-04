package com.liuhepay.cuppayment.db.dao;

import com.liuhepay.cuppayment.alipay.Config;
import com.liuhepay.cuppayment.db.table.UserTable;
import com.liuhepay.cuppayment.util.ConfigUtil;
import com.liuhepay.cuppayment.util.MyLog;
import com.liuhepay.cuppayment.util.TradeInfo;

import java.sql.SQLException;

/**
 * Created by LEO on 2016/7/25.
 */
public class UserDao extends BaseDao {

    /**
     * login: check userId and pwd
     * @param id
     * @param pwd
     * @return
     */
    public boolean userLogin(String id,String pwd) {
        UserTable userTable = (UserTable) queryById(id);
        if(userTable != null && userTable.getPwd().equals(pwd)){
            // save login id and password
            TradeInfo.getInstance().setUsername(userTable.getUserId());
            TradeInfo.getInstance().setUserpwd(userTable.getPwd());
            return true;
        }
        return false;
    }

}
