package com.example.daysmatter.app;

import android.app.Application;

import com.example.daysmatter.db.DBMaster;

public class MyApplication extends Application {

    /** 声明数据库操作实例 */
    public static DBMaster mDBMaster;

    @Override
    public void onCreate() {
        super.onCreate();
        //启动数据库
        mDBMaster = new DBMaster(getApplicationContext());
        mDBMaster.openDataBase();
    }
}
