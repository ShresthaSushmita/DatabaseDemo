package com.example.kanchha.databasedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.BaseAdapter;

/**
 * Created by kanchha on 3/4/2017.
 */
public class DatabaseAdapter {
    public DatabaseHelper helper;
    public SQLiteDatabase db;

    public DatabaseAdapter(Context context) {
        helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public long insertData(ContentValues contentValues) {
        long id = db.insert(DBConstant.TB_NAME, null, contentValues);
        return id;
    }

    public Cursor getAllData() {
        String[] columns = new String[]{DBConstant._ID, DBConstant.ST_NAME, DBConstant.ST_ROLL};
        Cursor cursor = db.query(DBConstant.TB_NAME, columns, null, null, null, null, null);
        return cursor;
    }

    public int deleteRowById(String Id) {
        int ret = db.delete(DBConstant.TB_NAME, DBConstant._ID + "=?", new String[]{Id});
        return ret;
    }

    public int updateRowById(String Id, ContentValues contentValues) {
        int ret = db.update(DBConstant.TB_NAME, contentValues, DBConstant._ID + " = ?", new String[]{Id});
        return ret;
    }
}