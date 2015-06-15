package test.java.com.softserve.edu.oms.pages;

import main.java.com.softserve.edu.atqc.helpers.webdriver.WebDriverUtils;
import main.java.com.softserve.edu.atqc.helpers.webdriver.IBrowser;

/**
 * Created by Xdr on 6/4/15.
 */
public class StartLoginPage extends LoginPage {

    private StartLoginPage() {
        super();
    }

    public static StartLoginPage load(IBrowser browser, String url) {
        //driver = new FirefoxDriver();
        //driver.get(url);
        WebDriverUtils.get(browser).load(url);
        return new StartLoginPage();
    }
}
