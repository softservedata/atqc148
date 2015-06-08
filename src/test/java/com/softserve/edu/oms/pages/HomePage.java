package test.java.com.softserve.edu.oms.pages;

import main.java.edu.atqc.helpers.ContextVisible;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Xdr on 6/4/15.
 */
public abstract class HomePage {
    private WebElement firstName;
    private WebElement lastName;
    private WebElement role;
    private WebElement logout;

    public HomePage() {
        this.firstName = ContextVisible.get().getVisibleWebElement(
                By.xpath("//tbody/tr/td[text( )='First name']/following-sibling::td"));
        this.lastName = ContextVisible.get().getVisibleWebElement(
                By.xpath("//tbody/tr/td[text( )='Last name']/following-sibling::td"));
        this.role = ContextVisible.get().getVisibleWebElement(
                By.xpath("//tbody/tr/td[text( )='Role']/following-sibling::td"));
        this.logout = ContextVisible.get().getVisibleWebElement(By.xpath("//a[@href='/OMS/logout.htm']"));
    }

    // - - - - - - - - - -

    public WebElement getFirstName() {
        return this.firstName;
    }

    public WebElement getLastName() {
        return this.lastName;
    }

    public WebElement getRole() {
        return this.role;
    }

    // - - - - - - - - - -

    public LoginPage logout() {
        this.logout.click();
        return new LoginPage();
    }

}
