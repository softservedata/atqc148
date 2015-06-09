package main.java.edu.atqc.page.order;

import main.java.edu.atqc.helpers.webdriver.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class OrderPageHelper {

    /**
     * Open the order page.
     *
     * @param driver browser driver to work with.
     */
    public static void navigateToOrderPage(/*WebDriver driver*/) {
        //driver.get("http://localhost:8080/OMS/order.htm");
       WebDriver driver = WebDriverUtils.get().getWebDriver();
//        TODO refactor using contextvisible
        driver.findElement(By.linkText("Ordering")).click();
    }

}