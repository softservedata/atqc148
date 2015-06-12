package main.java.edu.atqc.page.order;

import main.java.edu.atqc.helpers.ContextVisible;
import main.java.edu.atqc.helpers.Report;
import main.java.edu.atqc.helpers.webdriver.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderNavigationHelper {

    private OrderNavigationHelper() {
    }

    public static OrderNavigationHelper get() {
        return new OrderNavigationHelper();
    }

    /**
     * Check if button is disabled.
     *
     * @param button WebElement button to check.
     * @throws AssertionError if button is enabled.
     */
    public void isBtnDisabled(WebElement button) throws AssertionError {
        Report.log("Checking if button '" + button.getAttribute("value") + "' is disabled");
        if (button.isEnabled()) {
            Report.log("Check FAILED. Making screenshot.");
            Report.takeScreenshot("'" + button.getAttribute("value") + "' disabledCheck");
            throw new AssertionError("Button '" + button.getAttribute("value") + "' is not disabled.");
        }
    }

}
