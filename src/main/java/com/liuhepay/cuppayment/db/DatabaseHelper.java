package com.liuhepay.cuppayment.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.liuhepay.cuppayment.base.BaseApplication;
import com.liuhepay.cuppayment.db.table.UserTable;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static String DB_NAME = "liuhepay.db";
    private static int DB_VERSION = 1;
    private Map<String,Dao> daos = new HashMap<String,Dao>();
    private static DatabaseHelper mDatabaseHelper = null;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static DatabaseHelper getInstall()
    {
        if (mDatabaseHelper == null)
        {
            synchronized (DatabaseHelper.class)
            {
                if (mDatabaseHelper == null) {
                    mDatabaseHelper = new DatabaseHelper(BaseApplication.mApplication);
                }
            }
        }
        return mDatabaseHelper;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, UserTable.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            //TODO backup old table data(creat temp table,copy data to temp table,drop old table)
            TableUtils.dropTable(connectionSource, UserTable.class, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        super.close();
        for (String key : daos.keySet())
        {
            Dao dao = daos.get(key);
            dao = null;
        }
    }

    @Override
    public synchronized Dao getDao(Class clazz) throws SQLException
    {
        Dao dao = null;
        String className = clazz.getSimpleName();
        if (daos.containsKey(className))
        {
            dao = daos.get(className);
        }
        if (dao == null)
        {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }
}
