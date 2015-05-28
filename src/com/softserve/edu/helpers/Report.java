package com.softserve.edu.helpers;

import org.testng.Reporter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Xdr on 5/28/15.
 */
public final class Report {

    public static void log(String message) {
        String time = CalendarUtilities.getDateBySpecifiedFormat(CalendarUtilities.getCurrentDate(), "HH:mm:ss.SSS");
        Reporter.log(time + " LOG - " + message, true);
    }
}


class CalendarUtilities {
    static String getDateBySpecifiedFormat(Date currentDate, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String date = format.format(currentDate);
        return date;
    }

    static Date getCurrentDate() {
        return new Date();
    }
}
