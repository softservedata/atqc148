package com.softserve.edu.helpers;

import com.softserve.edu.webdriver.BrowserRepository;
import com.softserve.edu.webdriver.WebDriverUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

/**
 * Created by Xdr on 5/28/15.
 */
public final class ScreenShot {
    public static  void takeScreenshot(String name){
        File scrFile = ((TakesScreenshot) WebDriverUtils.get(BrowserRepository.getFirefoxTemporary()).getWebDriver()).getScreenshotAs(OutputType.FILE);
// Now you can do whatever you need to do with it, for example copy somewhere
        try {
            FileUtils.copyFile(scrFile, new File("test-output/screenshots/"+name+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
