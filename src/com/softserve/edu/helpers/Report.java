package com.softserve.edu.helpers;

import com.softserve.edu.helpers.webdriver.BrowserRepository;
import com.softserve.edu.helpers.webdriver.WebDriverUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Xdr on 5/28/15.
 */
public final class Report {

    /**
     * Write testNG log with specified message.
     *
     * @param message message to log.
     */
    public static void log(String message) {
        String time = CalendarUtilities.getDateBySpecifiedFormat(CalendarUtilities.getCurrentDate(), "HH:mm:ss.SSS");
        Reporter.log(time + " LOG - " + message+"\n", true);
    }

    /**
     * Takes the screenshot of browser window.
     *
     * @param name Screenshot name.
     */
    public static void takeScreenshot(String name) {
        String time = CalendarUtilities.getDateBySpecifiedFormat(CalendarUtilities.getCurrentDate(), "HH-mm-ss");
        String folder = "test-output/screenshots/";
        String format = ".png";
        File scrFile = ((TakesScreenshot) WebDriverUtils.get(BrowserRepository.getFirefoxTemporary()).getWebDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(folder + time + " " + name + format));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class CalendarUtilities {
    /**
     * Convert date to specified format.
     *
     * @param currentDate date to convert.
     * @param pattern     pattern like "HH:mm:ss".
     * @return converted date.
     */
    static String getDateBySpecifiedFormat(Date currentDate, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String date = format.format(currentDate);
        return date;
    }

    static Date getCurrentDate() {
        return new Date();
    }
}
