package com.softserve.edu.page.order;

import com.softserve.edu.helpers.ContextVisible;
import com.softserve.edu.helpers.Report;
import com.softserve.edu.webdriver.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class OrderSearch {

    private WebDriver driver;
    private Select searchCriterion;
    private WebElement searchValue;
    private WebElement applyBtn;

    private OrderSearch(WebDriver driver) {
        this.driver = driver;
        getReferences();
    }

    public static OrderSearch setDriver() {
        return new OrderSearch(WebDriverUtils.get().getWebDriver());
    }

    public void getReferences() {
        this.searchCriterion = new Select(ContextVisible.get().getVisibleWebElement(By.id("search")));
        this.searchValue = ContextVisible.get().getVisibleWebElement(By.id("searchValue"));
        this.applyBtn = ContextVisible.get().getVisibleWebElement(By.name("Apply"));
    }

    /**
     * Set search criterion to 'Status'
     */
    public void setCriterionToStatus() {
        for (WebElement criterion : this.searchCriterion.getOptions()) {
            if (criterion.equals("Status")) {
                Report.log("Click on '" + criterion + "'");
                criterion.click();
            }
        }
    }

    /**
     * Set search criterion to 'Order Name'
     */
    public void setCriterionToOrderName() {
        for (WebElement criterion : this.searchCriterion.getOptions()) {
            if (criterion.equals("Order Name")) {
                Report.log("Click on '" + criterion + "'");
                criterion.click();
            }
        }
    }

    /**
     * Click on 'Apply' button
     */
    public void apply() {
        Report.log("Click on '" + applyBtn.getAttribute("value") + "'");
        this.applyBtn.click();
    }

    /**
     * Type specified text to search field
     *
     * @param text text to type
     */
    public void typeTextToSearch(String text) {
        Report.log("Type '" + text + "' to '" + searchValue.getAttribute("id") + "'");
        this.searchValue.sendKeys(text);
    }
}
