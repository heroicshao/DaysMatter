package com.example.daysmatter.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.daysmatter.model.EventBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 公司数据表操作类
 * @author  dlong
 * created at 2019/3/13 11:46 AM
 */
public class EventDBDao {

    /** 数据表名称 */
    public static final String TABLE_NAME = "event_info";

    /** 表的字段名 */
    public static String KEY_ID = "id";
    public static String KEY_NAME = "name";
    public static String KEY_DATA = "data";
    public static String KEY_BOOK_ID = "book_id";
    public static String KEY_HOME_SHOW = "home_show";
    public static String KEY_HOME_FIRST = "home_first";
    public static String KEY_REPEAT = "repeat";

    private SQLiteDatabase mDatabase;
    /** 上下文 */
    private Context mContext;
    /** 数据库打开帮助类 */
    private DBMaster.DBOpenHelper mDbOpenHelper;

    public EventDBDao(Context context) {
        mContext = context;
    }

    public void setDatabase(SQLiteDatabase db){
        mDatabase = db;
    }

    /**
     * 插入一条数据
     * @param event
     * @return
     */
    public long insertData(EventBean event) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, event.name);
        values.put(KEY_DATA, event.data);
        values.put(KEY_BOOK_ID, event.book_id);
        values.put(KEY_HOME_SHOW, event.home_show);
        values.put(KEY_HOME_FIRST, event.home_first);
        values.put(KEY_REPEAT, event.repeat);
        return mDatabase.insert(TABLE_NAME, null, values);
    }

    /**
     * 删除一条数据
     * @param id
     * @return
     */
    public long deleteData(int id) {
        return mDatabase.delete(TABLE_NAME, KEY_ID + "=" + id, null);
    }

    /**
     * 删除所有数据
     * @return
     */
    public long deleteAllData() {
        return mDatabase.delete(TABLE_NAME, null, null);
    }

    /**
     * 更新一条数据
     * @param id
     * @param event
     * @return
     */
    public long updateData(int id, EventBean event) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, event.name);
        values.put(KEY_DATA, event.data);
        values.put(KEY_BOOK_ID, event.book_id);
        values.put(KEY_HOME_SHOW, event.home_show);
        values.put(KEY_HOME_FIRST, event.home_first);
        values.put(KEY_REPEAT, event.repeat);
        return mDatabase.update(TABLE_NAME, values, KEY_ID + "=" + id, null);
    }

    /**
     * 查询一条数据
     * @param id
     * @return
     */
    public List<EventBean> queryData(int id) {
        if (!DBConfig.HaveData(mDatabase,TABLE_NAME)){
            return null;
        }
        Cursor results = mDatabase.query(TABLE_NAME, new String[]{KEY_ID,
                        KEY_NAME,
                        KEY_DATA,
                        KEY_BOOK_ID,
                        KEY_HOME_SHOW,
                        KEY_HOME_FIRST,
                        KEY_REPEAT},
                KEY_ID + "=" + id , null, null, null, null);
        return convertUtil(results);
    }

    /**
     * 查询所有数据
     * @return
     */
    public List<EventBean> queryDataList() {
        if (!DBConfig.HaveData(mDatabase,TABLE_NAME)){
            return null;
        }
        Cursor results = mDatabase.query(TABLE_NAME, new String[]{KEY_ID,
                        KEY_NAME,
                        KEY_DATA,
                        KEY_BOOK_ID,
                        KEY_HOME_SHOW,
                        KEY_HOME_FIRST,
                        KEY_REPEAT},
                null, null, null, null, null);
        return convertUtil(results);
    }

    /**
     * 查询结果转换
     * @param cursor
     * @return
     */
    private List<EventBean> convertUtil(Cursor cursor) {
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst()) {
            return null;
        }
        List<EventBean> mList = new ArrayList<>();
        for (int i = 0; i < resultCounts; i++) {
            EventBean company = new EventBean();
            company.id = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID));
            company.name = cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME));
            company.data = cursor.getString(cursor.getColumnIndexOrThrow(KEY_DATA));
            company.book_id = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_BOOK_ID));
            company.home_show = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_HOME_SHOW));
            company.home_first = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_HOME_FIRST));
            company.repeat = cursor.getString(cursor.getColumnIndexOrThrow(KEY_REPEAT));
            mList.add(company);
            cursor.moveToNext();
        }
        return mList;
    }
}
