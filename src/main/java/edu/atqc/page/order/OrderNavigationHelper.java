package main.java.edu.atqc.page.order;

import main.java.edu.atqc.helpers.ContextVisible;
import main.java.edu.atqc.helpers.Report;
import main.java.edu.atqc.helpers.webdriver.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderNavigationHelper {
    private WebDriver driver;
    private WebElement nextBtn;
    private WebElement prevBtn;
    private WebElement firstBtn;
    private WebElement lastBtn;

    private OrderNavigationHelper(WebDriver driver) {
        this.driver = driver;
        getReferences();
    }

    /**
     * Finds navigation buttons on page.
     */
    public void getReferences() {
        nextBtn = ContextVisible.get().getVisibleWebElement(By.name("nextPage"));
        prevBtn = ContextVisible.get().getVisibleWebElement(By.name("previousPage"));
        firstBtn = ContextVisible.get().getVisibleWebElement(By.name("firstPage"));
        lastBtn = ContextVisible.get().getVisibleWebElement(By.name("lastPage"));
    }

    public static OrderNavigationHelper setDriver() {
        return new OrderNavigationHelper(WebDriverUtils.get().getWebDriver());
    }

    /**
     * Click on 'next' button
     */
    public void navigateToNextPage() {
        Report.log("Click on '" + nextBtn.getAttribute("value") + "'");
        nextBtn.click();
    }

    /**
     * Click on 'previous' button
     */
    public void navigateToPrevPage() {
        Report.log("Click on '" + prevBtn.getAttribute("value") + "'");
        prevBtn.click();
    }

    /**
     * Click on 'first' button
     */
    public void navigateToFirstPage() {
        Report.log("Click on '" + firstBtn.getAttribute("value") + "'");
        firstBtn.click();
    }

    /**
     * Click on 'last' button
     */
    public void navigateToLastPage() {
        Report.log("Click on '" + lastBtn.getAttribute("value") + "'");
        lastBtn.click();
    }

    /**
     * Check if 'next' button is disabled.
     *
     * @return true if is disabled, fasle if is enabled.
     */
    public boolean isNextBtnDisabled() {
        return !nextBtn.isEnabled();
    }

    /**
     * Check if 'previous' button is disabled.
     *
     * @return true if is disabled, fasle if is enabled.
     */
    public boolean isPrevBtnDisabled() {
        return !prevBtn.isEnabled();
    }

    /**
     * Check if 'first' button is disabled.
     *
     * @return true if is disabled, fasle if is enabled.
     */
    public boolean isFirstBtnDisabled() {
        return !firstBtn.isEnabled();
    }

    /**
     * Check if 'last' button is disabled.
     *
     * @return true if is disabled, fasle if is enabled.
     */
    public boolean isLastBtnDisabled() {
        return !lastBtn.isEnabled();
    }
}