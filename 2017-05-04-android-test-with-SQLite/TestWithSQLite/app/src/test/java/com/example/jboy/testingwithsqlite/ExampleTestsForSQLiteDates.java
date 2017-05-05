package com.example.jboy.testingwithsqlite;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Jboy on 05/05/2017.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = LOLLIPOP)
public class ExampleTestsForSQLiteDates {

    DateTimeDbHelper dbHelper;

    @Before
    public void setup() {
        dbHelper = new DateTimeDbHelper(RuntimeEnvironment.application);
    }

    @Test
    public void testStoreOneDateTime() throws Exception {
        dbHelper.insert(new Date());
        assertEquals(1, dbHelper.allDateTime().length);
    }

    @Test
    public void testStoreTwoDateTime() throws Exception {
        dbHelper.insert(new Date());
        TimeUnit.SECONDS.sleep(10);
        dbHelper.insert(new Date());
        assertEquals(2, dbHelper.allDateTime().length);
    }

    @Test
    public void testGetAllDatesAfterSpecifiedDate() throws Exception {
        dbHelper.insert(new Date());
        TimeUnit.SECONDS.sleep(5);
        dbHelper.insert(new Date());
        TimeUnit.SECONDS.sleep(5);

        long timeInMilliseconds = new Date().getTime();

        TimeUnit.SECONDS.sleep(5);
        dbHelper.insert(new Date());
        TimeUnit.SECONDS.sleep(5);
        dbHelper.insert(new Date());
        TimeUnit.SECONDS.sleep(5);

        // assert
        String[] namesAfterTime = dbHelper.getDateTimeAddedAfter(timeInMilliseconds);
        assertEquals(2, namesAfterTime.length);
    }

    @Test
    public void testGetAllDatesBetweenSpecifiedDates() throws Exception {
        dbHelper.insert(new Date());
        TimeUnit.SECONDS.sleep(5);
        dbHelper.insert(new Date());
        TimeUnit.SECONDS.sleep(5);

        long timeInMillisecondsAfter = new Date().getTime();

        TimeUnit.SECONDS.sleep(5);
        dbHelper.insert(new Date());
        TimeUnit.SECONDS.sleep(5);
        dbHelper.insert(new Date());
        TimeUnit.SECONDS.sleep(5);
        dbHelper.insert(new Date());
        TimeUnit.SECONDS.sleep(5);

        long timeInMillisecondsBefore = new Date().getTime();

        TimeUnit.SECONDS.sleep(5);
        dbHelper.insert(new Date());
        TimeUnit.SECONDS.sleep(5);
        dbHelper.insert(new Date());

        // assert
        String[] namesAfterTime = dbHelper.getDateTimeBetween(timeInMillisecondsAfter, timeInMillisecondsBefore);
        assertEquals(3, namesAfterTime.length);
    }

    @Test
    public void testForUsingJuliandayFunctionInSQLite() throws Exception {
        int n = dbHelper.testingJuliandayFunctionOfSQLite();
        assertTrue(n > 0);
    }

    @Test
    public void testForUsingDatetimeFunctionInSQLiteUsingUnixEpoch() throws Exception {
        Date today = new Date();
        String value = dbHelper.testingDatetimeFunctionOfSQLiteUsingUnixEpoch(today);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        assertEquals(dateFormat.format(today), value);
    }

    @Test
    public void testForUsingDatetimeFunctionInSQLite() throws Exception {
        Date today = new Date();
        String value = dbHelper.testingDatetimeFunctionOfSQLite(today);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        assertEquals(dateFormat.format(today), value);
    }

    @Test
    public void testForUsingDatetimeFunctionInSQLiteUsingLocalTimezone() throws Exception {
        Date today = new Date();
        String value = dbHelper.testingDatetimeFunctionOfSQLiteUsingLocaltime(today);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

        assertEquals(dateFormat.format(today), value);
    }

    @Test
    public void testHardcodedDateString() throws Exception {
        String value = dbHelper.testingHardcodedDateString();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        assertEquals("2017-05-05 05:54:16", value);
    }
//
//    @Test
//    public void testForUsingJulianDayInSQL() throws Exception {
//        int n = dbHelper.computeNumberOfDaysSinceTheSIgningOfTheUSDeclarationOfIndependence();
//        assertTrue(n > 0);
//    }

}
