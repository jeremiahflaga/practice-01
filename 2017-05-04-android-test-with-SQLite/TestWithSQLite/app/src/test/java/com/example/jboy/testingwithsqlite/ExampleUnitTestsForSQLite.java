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
import static org.junit.Assert.*;

/**
 * Created by Jboy on 04/05/2017.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = LOLLIPOP)
//@Config(constants = BuildConfig.class, sdk = intArrayOf(LOLLIPOP), packageName = "com.example.jboy.testingwithsqlite")
public class ExampleUnitTestsForSQLite {

    NamesDbHelper dbHelper;

    @Before
    public void setup() {
        dbHelper = new NamesDbHelper(RuntimeEnvironment.application);
    }

    @Test
    public void testStoreOneName() throws Exception {
        dbHelper.insert("MyName");
        assertEquals(1, dbHelper.allNames().length);
    }

    @Test
    public void testStoreTwoName() throws Exception {
        dbHelper.insert("MyName");
        dbHelper.insert("YourName");
        assertEquals(2, dbHelper.allNames().length);
    }
}
