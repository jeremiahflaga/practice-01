package com.example.jboy.testingwithsqlite;

import android.os.Build;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Jboy on 05/05/2017.
 */

public class DatesHelper {
//    public static SimpleDateFormat ISODateFormatterInUTC() {
//        //                                                     2017-05-01T20:00:39+00:00
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
//        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
//
//        return dateFormat;
//    }

//    public static SimpleDateFormat ISODateFormatterInLocalTimezone() {
//        //                                                     2017-05-01T20:00:39+08:00
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
//
//        return dateFormat;
//    }

    //from http://stackoverflow.com/questions/25636917/using-simpledateformats-zzzzz-0300-for-timezone-before-android-4-3/30504604
    // Using SimpleDateFormat's “ZZZZZ” (+03:00) for timezone before Android 4.3
    public static String formatISO8601_UTC(Date date) {
        SimpleDateFormat DATE_FORMAT_ISO8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.US);
        DATE_FORMAT_ISO8601.setTimeZone(TimeZone.getTimeZone("UTC"));
        return putColonInTimezone(DATE_FORMAT_ISO8601, date);
    }

    public static String formatISO8601_LocalTimezone(Date date) {
        SimpleDateFormat DATE_FORMAT_ISO8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.US);
        return putColonInTimezone(DATE_FORMAT_ISO8601, date);
    }

    private static String putColonInTimezone(SimpleDateFormat dateFormat, Date date) {
        String result = dateFormat.format(date);
        //fix up for older Android where ZZZZZ does not include colon
        if (!result.substring(result.length() - 3).startsWith(":")) {
            result = result.substring(0, result.length() - 2) + ":" + result.substring(result.length() - 2);
        }
        return result;
    }
}
