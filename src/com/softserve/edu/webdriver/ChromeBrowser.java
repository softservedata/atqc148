package com.softserve.edu.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Xdr on 6/1/15.
 */
public class ChromeBrowser implements IBrowser {

    WebDriver driver;

    @Override
    public WebDriver getWebDriver() {
        return new ChromeDriver();
    }

    @Override
    public String getWebDriverName() {
        return "ChromeBrowser";
    }

    @Override
    public void quit() {
        this.driver.quit();
    }

}
