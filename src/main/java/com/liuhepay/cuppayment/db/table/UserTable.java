package com.liuhepay.cuppayment.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by LEO on 2016/7/25.
 */
@DatabaseTable(tableName="operator")
public class UserTable {
    @DatabaseField(id=true)
    private String userId;
    @DatabaseField(defaultValue="0000")
    private String pwd;
    @DatabaseField(canBeNull=false)
    private String creatDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(String creatDate) {
        this.creatDate = creatDate;
    }

    @Override
    public String toString() {
        return "UserTable{" +
                "\n  userId='" + userId + '\'' +
                "\n  pwd='" + pwd + '\'' +
                "\n  creatDate='" + creatDate + '\'' +
                '}';
    }
}
