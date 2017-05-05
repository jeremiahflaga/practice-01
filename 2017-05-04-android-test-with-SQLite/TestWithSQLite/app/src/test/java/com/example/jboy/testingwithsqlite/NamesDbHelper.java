package com.example.jboy.testingwithsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.common.base.Strings;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Jboy on 04/05/2017.
 */

public class NamesDbHelper {

    private static final String DATABASE_NAME = null; //null so that in-memory database will be used (please refer to this:https://attakornw.wordpress.com/2012/02/25/using-in-memory-sqlite-database-in-android-tests/) // "sqliteTestDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Name";
    private static final String COLUMN_NAME = "Name";

    private static final String  DATABASE_CREATE_SQL =
            "create table " + TABLE_NAME + " " +
                    "(ID integer primary key autoincrement, " +
                    COLUMN_NAME + " text not null);";

    private SQLiteDatabase db;

    public NamesDbHelper(Context context) {
        MyOpenHelper openHelper = new MyOpenHelper(context);
        this.db = openHelper.getWritableDatabase();
    }

    public long insert(String name) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        return db.insert(TABLE_NAME, null, values);
    }

    public String[] allNames(){
        List<String> results = new ArrayList<String>();
        Cursor query = db.query(TABLE_NAME, null, null, null, null, null, null);
        while (query.moveToNext()) {
            results.add(query.getString(query.getColumnIndexOrThrow(COLUMN_NAME)));
        }
        query.close();
        return results.toArray(new String[0]);
    }

    private class MyOpenHelper extends SQLiteOpenHelper{

        public MyOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate (SQLiteDatabase sqLiteDatabase){
            sqLiteDatabase.execSQL(DATABASE_CREATE_SQL);
        }

        @Override
        public void onUpgrade (SQLiteDatabase sqLiteDatabase,int i, int i1){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        }
    }
}
