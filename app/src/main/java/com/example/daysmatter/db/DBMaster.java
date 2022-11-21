package com.example.daysmatter.db;

import static com.example.daysmatter.db.DBConfig.DB_NAME;
import static com.example.daysmatter.db.DBConfig.DB_VERSION;

import android.app.Activity;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * 数据库总操作类
 * @author  sss
 * created at 2019/3/13 11:29 AM
 */
public class DBMaster {

    /** 上下文 */
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private DBOpenHelper mDbOpenHelper;

    /** 数据表操作类实例化 */
    public BookDBDao mBookDBDao;
    public EventDBDao mEventDBDao;

    public DBMaster(Context context){
        mContext = context;
        mBookDBDao = new BookDBDao(mContext);
        mEventDBDao = new EventDBDao(mContext);
    }

    /**
     * 打开数据库
     */
    public void openDataBase() {
        mDbOpenHelper = new DBOpenHelper(mContext, DB_NAME, null, DB_VERSION);
        try {
            mDatabase = mDbOpenHelper.getWritableDatabase();//获取可写数据库
        } catch (SQLException e) {
            mDatabase = mDbOpenHelper.getReadableDatabase();//获取只读数据库
        }
        // 设置数据库的SQLiteDatabase
        mBookDBDao.setDatabase(mDatabase);
        mEventDBDao.setDatabase(mDatabase);
    }

    /**
     * 关闭数据库
     */
    public void closeDataBase() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    /** 创建该数据库下book表的语句 */
    private static final String mBookSqlStr = "create table if not exists " + BookDBDao.TABLE_NAME + " (" +
            BookDBDao.KEY_ID + " integer primary key autoincrement , " +
            BookDBDao.KEY_NAME + " text not null , " +
            BookDBDao.KEY_ICON + " integer , " +
            BookDBDao.KEY_COVER + " integer );";

    /** 创建该数据库下event表的语句 */
    private static final String mEventSqlStr = "create table if not exists " + EventDBDao.TABLE_NAME + " (" +
            EventDBDao.KEY_ID + " integer primary key autoincrement , " +
            EventDBDao.KEY_NAME + " text not null , " +
            EventDBDao.KEY_DATA + " text not null , " +
            EventDBDao.KEY_BOOK_ID + " integer ," +
            EventDBDao.KEY_HOME_SHOW + " integer ," +
            EventDBDao.KEY_HOME_FIRST + " integer ," +
            EventDBDao.KEY_REPEAT + " text not null," +
            "CONSTRAINT fk_eventId FOREIGN KEY (" + EventDBDao.KEY_BOOK_ID +") REFERENCES " + BookDBDao.TABLE_NAME + " (" + BookDBDao.KEY_ID + ") ON DELETE CASCADE ON UPDATE NO ACTION );";

    /** 删除该数据库下Book表的语句 */
    private static final String mBookDelSql = "DROP TABLE IF EXISTS " + BookDBDao.TABLE_NAME;

    /** 删除该数据库下Event表的语句 */
    private static final String mEventDelSql = "DROP TABLE IF EXISTS " + EventDBDao.TABLE_NAME;

    /**
     * 数据表打开帮助类
     */
    public static class DBOpenHelper extends SQLiteOpenHelper {

        public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(mBookSqlStr);
            db.execSQL(mEventSqlStr);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(mBookDelSql);
            db.execSQL(mEventDelSql);
            onCreate(db);
        }

        @Override
        public void onOpen(SQLiteDatabase db) {
            super.onOpen(db);
            if(!db.isReadOnly()) {
                db.execSQL("PRAGMA foreign_keys = ON;");
            }
        }
    }
}
