package com.softserve.edu.page.order;

import com.softserve.edu.webdriver.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class OrderPage {

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
