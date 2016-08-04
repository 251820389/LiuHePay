package com.liuhepay.cuppayment.db.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.StatementBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.liuhepay.cuppayment.R;
import com.liuhepay.cuppayment.base.BaseApplication;
import com.liuhepay.cuppayment.db.table.UserTable;
import com.liuhepay.cuppayment.util.MyLog;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by LEO on 2016/7/25.
 */
public class BaseDao {

    protected Dao mDao = null;
    protected int ret = 0;

    public BaseDao() {
        try {
            mDao = BaseApplication.mDatabaseHelper.getDao(UserTable.class);
        } catch (SQLException e) {
            e.printStackTrace();
            MyLog.e(MyLog.TAG, BaseApplication.mApplication.getString(R.string.error_get_dao));
        }
    }

    public int insert(Object bean) {
        try {
            ret = mDao.create(bean);
        } catch (SQLException e) {
            e.printStackTrace();
            ret = 0;
            MyLog.e(MyLog.TAG, BaseApplication.mApplication.getString(R.string.error_insert));
        }
        return ret;
    }

    public Object queryAll() {
        Object result = null;
        try {
            result = mDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            MyLog.e(MyLog.TAG, BaseApplication.mApplication.getString(R.string.error_query_all));
        }
        return result;
    }

    public Object queryById(String id) {
        Object result = null;
        try {
            result = mDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            MyLog.e(MyLog.TAG, BaseApplication.mApplication.getString(R.string.error_querybyid));
        }
        return result;
    }

    public Object queryByFiled(String filedName, String filedValue) {
        Object bean = null;
        try {
            bean = mDao.queryForEq(filedName, filedValue);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public int updata(Object bean) {
        try {
            ret = mDao.update(bean);
        } catch (SQLException e) {
            e.printStackTrace();
            MyLog.e(MyLog.TAG, BaseApplication.mApplication.getString(R.string.error_updata));
        }
        return ret;
    }
    public int updata(String filedName,String filedVaule) {
        try {
            UpdateBuilder updateBuilder = mDao.updateBuilder();
            updateBuilder.updateColumnValue(filedName, filedVaule);
            updateBuilder.update();
        } catch (SQLException e) {
            e.printStackTrace();
            MyLog.e(MyLog.TAG, BaseApplication.mApplication.getString(R.string.error_updata));
        }
        return ret;
    }

    public int deleteById(String id) {
        try {
            ret = mDao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            ret = 0;
            MyLog.e(MyLog.TAG, BaseApplication.mApplication.getString(R.string.error_delete));
        }
        return ret;
    }

    public int delete(Object bean) {
        try {
            ret = mDao.delete(bean);
        } catch (SQLException e) {
            e.printStackTrace();
            ret = 0;
            MyLog.e(MyLog.TAG, BaseApplication.mApplication.getString(R.string.error_delete));
        }
        return ret;
    }

}