package com.example.jboy.testingwithsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jboy on 05/05/2017.
 */

public class DateTimeDbHelper {

    private static final int MILLISECONDS_IN_A_SECOND = 1000;

    private static final String DATABASE_NAME = null; //null so that in-memory database will be used (please refer to this:https://attakornw.wordpress.com/2012/02/25/using-in-memory-sqlite-database-in-android-tests/) // "sqliteTestDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Name";
    private static final String DATE_TIME_COLUMN_NAME = "DateTimeColumn";

    private static final String  DATABASE_CREATE_SQL =
            "create table " + TABLE_NAME + " " +
                    "(ID integer primary key autoincrement, " +
                    DATE_TIME_COLUMN_NAME + " text not null);";

    private SQLiteDatabase db;


    public DateTimeDbHelper(Context context) {
        DateTimeDbHelper.MyOpenHelper openHelper = new DateTimeDbHelper.MyOpenHelper(context);
        this.db = openHelper.getWritableDatabase();
    }

    public long insert(Date date) {
        ContentValues values = new ContentValues();
        values.put(DATE_TIME_COLUMN_NAME, DatesHelper.formatISO8601_UTC(date));
        return db.insert(TABLE_NAME, null, values);
    }


    public String[] allDateTime(){
        List<String> results = new ArrayList<String>();
        Cursor query = db.query(TABLE_NAME, null, null, null, null, null, null);
        while (query.moveToNext()) {
            String dateTime = query.getString(query.getColumnIndexOrThrow(DATE_TIME_COLUMN_NAME));
            results.add(dateTime);
        }
        query.close();
        return results.toArray(new String[0]);
    }

    public int testingJuliandayFunctionOfSQLite() {
        //NOTE: from http://sqlite.org/lang_datefunc.html
        // Compute the number of days since the signing of the US Declaration of Independence.
        Cursor query = db.rawQuery("SELECT julianday('now') - julianday('1776-07-04');", new String[] {});
        query.moveToNext();
        int n = query.getInt(0);
        query.close();
        return n;
    }

    public String testingDatetimeFunctionOfSQLiteUsingUnixEpoch(Date date) {
        //NOTE: from http://sqlite.org/lang_datefunc.html
        // Compute the number of days since the signing of the US Declaration of Independence.
        long unixepochtime = date.getTime() / MILLISECONDS_IN_A_SECOND;
        Cursor query = db.rawQuery("SELECT datetime(?, 'unixepoch');", new String[] {String.valueOf(unixepochtime)});
        query.moveToNext();
        String value = query.getString(0);
        query.close();
        return value;
    }

    public String testingDatetimeFunctionOfSQLite(Date date) {
        //NOTE: from http://sqlite.org/lang_datefunc.html
        // The "utc" modifier is the opposite of "localtime". "utc" assumes that the string to its
        // left is in the local timezone and adjusts that string to be in UTC. If the prior string
        // is not in localtime, then the result of "utc" is undefined.
        Cursor query = db.rawQuery("SELECT datetime('" + DatesHelper.formatISO8601_UTC(date) + "');", new String[] {});
        query.moveToNext();
        String value = query.getString(0);
        query.close();
        return value;
    }

    public String testingDatetimeFunctionOfSQLiteUsingLocaltime(Date date) {
        //NOTE: from http://sqlite.org/lang_datefunc.html
        // The "localtime" modifier (12) assumes the time string to its left is in Universal
        // Coordinated Time (UTC) and adjusts the time string so that it displays localtime.
        // If "localtime" follows a time that is not UTC, then the behavior is undefined.
        Cursor query = db.rawQuery("SELECT datetime('" + DatesHelper.formatISO8601_UTC(date) + "', 'localtime');", new String[] {});
        query.moveToNext();
        String value = query.getString(0);
        query.close();
        return value;
    }

    public String testingHardcodedDateString() {
        Cursor query = db.rawQuery("SELECT datetime('2017-05-05T05:54:16+00:00');", new String[] {});
        query.moveToNext();
        String value = query.getString(0);
        query.close();
        return value;
    }

    public String[] getDateTimeAddedAfter(long timeInMilleseconds) {
        final int MILLISECONDS_IN_A_SECOND = 1000;
        long unixepochtime = timeInMilleseconds / MILLISECONDS_IN_A_SECOND;
        List<String> results = new ArrayList<String>();
        Cursor query = db.rawQuery(
                "select * from " + TABLE_NAME +
                        " where datetime(" + DATE_TIME_COLUMN_NAME + ") > datetime(?, 'unixepoch');", new String[] {String.valueOf(unixepochtime)});
        while (query.moveToNext()) {
            results.add(query.getString(query.getColumnIndexOrThrow(DATE_TIME_COLUMN_NAME)));
        }
        query.close();
        return results.toArray(new String[0]);
    }

    public String[] getDateTimeBetween(long timeInMillisecondsAfter, long timeInMillisecondsBefore) {
        long unixepochtimeAfter = timeInMillisecondsAfter / MILLISECONDS_IN_A_SECOND;
        long unixepochtimeBefore = timeInMillisecondsBefore / MILLISECONDS_IN_A_SECOND;
        List<String> results = new ArrayList<String>();
        Cursor query = db.rawQuery(
                "select * from " + TABLE_NAME +
                        " where datetime(" + DATE_TIME_COLUMN_NAME + ") >= datetime(?, 'unixepoch') " +
                        " and datetime(" + DATE_TIME_COLUMN_NAME + ") < datetime(?, 'unixepoch');",
                new String[] {String.valueOf(unixepochtimeAfter), String.valueOf(unixepochtimeBefore)});
        while (query.moveToNext()) {
            results.add(query.getString(query.getColumnIndexOrThrow(DATE_TIME_COLUMN_NAME)));
        }
        query.close();
        return results.toArray(new String[0]);
    }

    private class MyOpenHelper extends SQLiteOpenHelper {

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
