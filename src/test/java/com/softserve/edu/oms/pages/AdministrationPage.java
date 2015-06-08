package com.softserve.edu.oms.pages;

import main.java.edu.atqc.helpers.ContextVisible;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import test.java.com.softserve.edu.oms.data.user.IUser;
import test.java.com.softserve.edu.oms.pages.LoginPage;

public class AdministrationPage {
    public static enum Fields {
        ALL_COLUMNS("All Columns"),
        FIRST_NAME("First Name"),
        LAST_NAME("Last Name"),
        ROLE("Role"),
        LOGIN_NAME("Login Name");
        private String field;

        private Fields(String field) {
            this.field = field;
        }

        @Override
        public String toString() {
            return this.field;
        }
    }

    public static enum Conditions {
        EQUALS("equals"),
        NOT_EQUALS_TO("not equals to"),
        STARTS_WITH("starts with"),
        CONTAINS("contains"),
        DOES_NOT_CONTAIN("does not contain");
        private String field;

        private Conditions(String field) {
            this.field = field;
        }

        @Override
        public String toString() {
            return this.field;
        }
    }

    private WebElement createNewUser;
    private Select field;
    private Select condition;
    private WebElement searchField;
    private WebElement logout;
    // Refresh
    private WebElement firstName;
    private WebElement lastName;
    private WebElement loginButton;
    //private WebElement searchButton;


    public AdministrationPage() {
        this.createNewUser = ContextVisible.get().getVisibleWebElement(By.partialLinkText("Create New User"));
        this.field = new Select(ContextVisible.get().getVisibleWebElement(By.id("field")));
        this.condition = new Select(ContextVisible.get().getVisibleWebElement(By.id("condition")));
        this.searchField = ContextVisible.get().getVisibleWebElement(By.id("searchField"));
        this.logout = ContextVisible.get().getVisibleWebElement(By.xpath("//a[@href='/OMS/logout.htm']"));
        //
        this.firstName = ContextVisible.get().getVisibleWebElement(By.xpath("//tbody/tr[1]/td[1]"));
        this.lastName = ContextVisible.get().getVisibleWebElement(By.xpath("//tbody/tr[1]/td[2]"));
        this.loginButton = ContextVisible.get().getVisibleWebElement(By.xpath("//tbody/tr[1]/td[3]"));
//		this.searchButton = ContextVisible.get().getVisibleWebElement(By.tagName("search"));
    }

    // - - - - - - - - - -

    public WebElement getCreateNewUser() {
        return createNewUser;
    }

    public Select getField() {
        return field;
    }

    public Select getCondition() {
        return condition;
    }

    public WebElement getSearchField() {
        return searchField;
    }

    public WebElement getFirstName() {
        return firstName;
    }

    public WebElement getLastName() {
        return lastName;
    }

    public WebElement getLogin() {
        return loginButton;
    }

    // - - - - - - - - - -
    public LoginPage logout() {
        this.logout.click();
        return new LoginPage();
    }
    // - - - - - - - - - -

    public void selectColumnFilter(Fields setField) {
        field.selectByVisibleText(setField.toString());
    }

    public void selectMatchFilter(Conditions setCondition) {
        condition.selectByVisibleText(setCondition.toString());
    }

    private void resetTable() {
        this.firstName = ContextVisible.get().getVisibleWebElement(By.xpath("//tbody/tr[1]/td[1]"));
        System.out.println("firstName = " + firstName.getText());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.firstName = ContextVisible.get().getVisibleWebElement(By.xpath("//tbody/tr[1]/td[1]"));
        this.lastName = ContextVisible.get().getVisibleWebElement(By.xpath("//tbody/tr[1]/td[2]"));
        this.loginButton = ContextVisible.get().getVisibleWebElement(By.xpath("//tbody/tr[1]/td[3]"));
        System.out.println("new firstName = " + firstName.getText());
    }

    public void typeSearchField(String text) {
        searchField.click();
        searchField.clear();
        searchField.sendKeys(text);
        //
        resetTable();
    }

//	public void clickSearchButton()
//	{
//		searchButton.click();
//	}

}
