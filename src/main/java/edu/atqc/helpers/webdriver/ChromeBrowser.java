package main.java.edu.atqc.helpers.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
    public void quit() {
        this.driver.quit();
    }

}
