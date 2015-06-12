package main.java.edu.atqc.helpers;

import main.java.edu.atqc.helpers.webdriver.BrowserRepository;
import main.java.edu.atqc.helpers.webdriver.WebDriverUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
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
        Reporter.log(time + " LOG - " + message + "\n", true);
    }


    public static void logWithColor(String message, String color) {
        String time = CalendarUtilities.getDateBySpecifiedFormat(CalendarUtilities.getCurrentDate(), "HH:mm:ss.SSS");
        Reporter.log("<p style=\"color:"+color+"\">"+time + " LOG - " + message + "</p>\n", false);
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
//        wait till elements opacity is 1
        ContextVisible.get().getVisibleWebElement(By.xpath("//body/div[@style='']"));
        File scrFile = ((TakesScreenshot) WebDriverUtils.get(BrowserRepository.getFirefoxTemporary()).getWebDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(folder + time + " " + name + format));
            log("Screenshot has been made. "+"<img src=\"../" + "screenshots/" + time + " " + name + format + "\" alt=\"\"/><br />");
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
