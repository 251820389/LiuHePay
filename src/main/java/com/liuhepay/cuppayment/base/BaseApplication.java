package com.liuhepay.cuppayment.base;

import android.app.Application;
import android.app.Service;
import android.content.SharedPreferences;
import android.text.format.DateFormat;

import com.liuhepay.cuppayment.R;
import com.liuhepay.cuppayment.crash.Crash;
import com.liuhepay.cuppayment.db.DatabaseHelper;
import com.liuhepay.cuppayment.db.dao.UserDao;
import com.liuhepay.cuppayment.db.table.UserTable;

public class BaseApplication extends Application {

    public static DatabaseHelper mDatabaseHelper = null;
    public static BaseApplication mApplication = null;
    private SharedPreferences sharedPreferences = null;

    @Override
    public void onCreate() {
        super.onCreate();
        Crash crash = Crash.getInstance();
        crash.init(getApplicationContext());
        mApplication = this;
        sharedPreferences = getSharedPreferences("liuhepay", Service.MODE_PRIVATE);
        mDatabaseHelper = DatabaseHelper.getInstall();
        if(sharedPreferences.getBoolean("first",true)){
            initData();
        }
    }

    /**
     * first time excute this method
     */
    private void initData() {
        UserDao dao = new UserDao();
        String time = (String) DateFormat.format(getString(R.string.format_data), System.currentTimeMillis());
        UserTable userTable = new UserTable();
        for (int i = 1; i < 6; i++) {
            userTable.setUserId("0" + i);
            userTable.setPwd("0000");
            userTable.setCreatDate(time);
            dao.insert(userTable);
        }

        userTable.setUserId("00");
        userTable.setPwd("123456");
        userTable.setCreatDate(time);
        dao.insert(userTable);

        userTable.setUserId("99");
        userTable.setPwd("12345678");
        userTable.setCreatDate(time);
        dao.insert(userTable);

        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean("first",false);
        edit.commit();
    }
}
