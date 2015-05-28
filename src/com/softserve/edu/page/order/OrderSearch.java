package com.softserve.edu.page.order;

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
        this.searchCriterion = new Select(driver.findElement(By.id("search")));
        this.searchValue = driver.findElement(By.id("searchValue"));
        this.applyBtn = driver.findElement(By.name("Apply"));
    }

    public static OrderSearch setDriver(WebDriver driver) {
        return new OrderSearch(driver);
    }

    public void setCriterionToStatus() {
        for (WebElement criterion : this.searchCriterion.getOptions()) {
            if (criterion.equals("Status")) {
                criterion.click();
            }
        }
    }

    public void setCriterionToOrderName() {
        for (WebElement criterion : this.searchCriterion.getOptions()) {
            if (criterion.equals("Order Name")) {
                criterion.click();
            }
        }
    }

    public void apply() {
        this.applyBtn.click();
    }

    public void typeTextToSearch(String text) {
        this.searchValue.sendKeys(text);
    }


}
