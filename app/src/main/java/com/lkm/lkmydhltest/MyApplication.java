package com.lkm.lkmydhltest;

import android.app.Application;

import com.lkm.lkmydhltest.entity.DaoMaster;
import com.lkm.lkmydhltest.entity.DaoSession;

import org.greenrobot.greendao.database.Database;

public class MyApplication extends Application {
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
