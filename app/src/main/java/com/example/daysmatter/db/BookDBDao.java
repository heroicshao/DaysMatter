package com.example.daysmatter.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.daysmatter.model.BookBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 手机数据表操作类
 * @author  dlong
 * created at 2019/3/13 11:39 AM
 */
public class BookDBDao {

    /** 数据表名称 */
    public static final String TABLE_NAME = "book_info";

    /** 表的字段名 */
    public static String KEY_ID = "id";
    public static String KEY_NAME = "name";
    public static String KEY_ICON = "icon";
    public static String KEY_COVER = "cover";

    private SQLiteDatabase mDatabase;
    /** 上下文 */
    private Context mContext;
    /** 数据库打开帮助类 */
    private DBMaster.DBOpenHelper mDbOpenHelper;

    public BookDBDao(Context context) {
        mContext = context;
    }

    public void setDatabase(SQLiteDatabase db){
        mDatabase = db;
    }

    /**
     * 插入一条数据
     * @param book
     * @return
     */
    public long insertData(BookBean book) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, book.name);
        values.put(KEY_ICON, book.icon);
        values.put(KEY_COVER, book.cover);
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
     * @param book
     * @return
     */
    public long updateData(int id, BookBean book) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, book.name);
        values.put(KEY_ICON, book.icon);
        values.put(KEY_COVER, book.cover);
        return mDatabase.update(TABLE_NAME, values, KEY_ID + "=" + id, null);
    }

    /**
     * 查询一条数据
     * @param id
     * @return
     */
    public List<BookBean> queryData(int id) {
        if (!DBConfig.HaveData(mDatabase,TABLE_NAME)){
            return null;
        }
        Cursor results = mDatabase.query(TABLE_NAME, new String[]{KEY_ID,
                        KEY_NAME,
                        KEY_ICON,
                        KEY_COVER},
                KEY_ID + "=" + id , null, null, null, null);
        return convertUtil(results);
    }

    /**
     * 查询所有数据
     * @return
     */
    public List<BookBean> queryDataList() {
        if (!DBConfig.HaveData(mDatabase,TABLE_NAME)){
            return null;
        }
        Cursor results = mDatabase.query(TABLE_NAME, new String[]{KEY_ID,
                        KEY_NAME,
                        KEY_ICON,
                        KEY_COVER},
                null, null, null, null, null);
        return convertUtil(results);
    }

    /**
     * 查询结果转换
     * @param cursor
     * @return
     */
    private List<BookBean> convertUtil(Cursor cursor) {
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst()) {
            return null;
        }
        List<BookBean> mList = new ArrayList<>();
        for (int i = 0; i < resultCounts; i++) {
            BookBean book = new BookBean();
            book.id = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID));
            book.name = cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME));
            book.icon = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ICON));
            book.cover = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_COVER));
            mList.add(book);
            cursor.moveToNext();
        }
        return mList;
    }
}
