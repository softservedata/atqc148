package test.java.com.softserve.edu.oms.pages;

import main.java.com.softserve.edu.atqc.helpers.ContextVisible;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import test.java.com.softserve.edu.oms.data.IUser;

/**
 * Created by Xdr on 6/4/15.
 */
public class LoginPage {
    private WebElement loginName;
    private WebElement password;
    private WebElement send;

    public LoginPage() {
        this.loginName = ContextVisible.get().getVisibleWebElement(By.name("j_username"));
        this.password = ContextVisible.get().getVisibleWebElement(By.name("j_password"));
        this.send = ContextVisible.get().getVisibleWebElement(By.xpath("//input[@type='submit']"));
    }

    private void setLoginData(IUser user) {
        this.loginName.click();
        this.loginName.clear();
        this.loginName.sendKeys(user.getLogin());
        this.password.click();
        this.password.clear();
        this.password.sendKeys(user.getPassword());
        this.send.click();
    }

    // - - - - - - - - - -

    public WebElement getLoginName() {
        return this.loginName;
    }

    public WebElement getPassword() {
        return this.password;
    }

    public WebElement getSend() {
        return this.send;
    }

    // - - - - - - - - - -

    public AdminHomePage successAdminLogin(IUser adminUser) {
        //public void successAdminLogin(User adminUser) {
        setLoginData(adminUser);
        // Return a new page object representing the destination.
        return new AdminHomePage();
    }

    public CustomerHomePage successCustomerLogin(IUser customerUser) {
        //public void successCustomerLogin(User customerUser) {
        setLoginData(customerUser);
        // Return a new page object representing the destination.
        return new CustomerHomePage();
    }

    public MerchandiserHomePage successMerchandiserLogin(IUser merchandiserUser) {
        //public void successCustomerLogin(User customerUser) {
        setLoginData(merchandiserUser);
        // Return a new page object representing the destination.
        return new MerchandiserHomePage();
    }

}

