package com.softserve.edu.page.login;


import com.softserve.edu.helpers.ContextVisible;
import com.softserve.edu.helpers.webdriver.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.thoughtworks.selenium.SeleneseTestNgHelper.assertEquals;

public class LoginPage {

	private WebDriver driver;
	private WebElement loginFld;
	private WebElement passwordFld;
	private WebElement submitBtn;

	private LoginPage(WebDriver driver) {
		this.driver= driver;
		driver.get("http://localhost:8080/OMS/");
	}

	/**
	 * Finds login,password,submit elements on page.
	 * @return LoginPage
	 */
	public LoginPage getLoginFields(){
		loginFld = ContextVisible.get().getVisibleWebElement(By.name("j_username"));
		passwordFld = ContextVisible.get().getVisibleWebElement(By.name("j_password"));
		submitBtn = ContextVisible.get().getVisibleWebElement(By.name("submit"));
		return this;
	}

	/**
	 * Set the driver to work with.
	 * @param driver web browser driver.
	 * @return
	 */
	public static LoginPage setDriver() {
		return new LoginPage(WebDriverUtils.get().getWebDriver());
	}

	/**
	 * Login field getter.
	 * @return login field web element.
	 */
	public WebElement getLoginFld() {
		return loginFld;
	}

	/**
	 * Password field getter.
	 * @return password field web element.
	 */
	public WebElement getPasswordFld() {
		return passwordFld;
	}

	/**
	 * Submit button getter.
	 * @return submit button element.
	 */
	public WebElement getSubmitBtn() {
		return submitBtn;
	}

	/**
	 * Log in under specified login password and role. Check if valid user logged.
	 * @param login login name.
	 * @param password password.
	 * @param role user role(Administrator, Customer, Merchandiser, Supervisor).
	 */
	public void logIn(String login, String password, String role) {
		loginFld.clear();
		loginFld.sendKeys(login);
		passwordFld.clear();
		passwordFld.sendKeys(password);
		submitBtn.click();
		assertEquals(
				role,
				driver.findElement(
						By.xpath("//div[@id='content']/div/fieldset/table/tbody/tr[4]/td[2]"))
						.getText());
	}

	/**
	 * Login under specified user. Check if valid user is logged.
	 * @param user user that implements IUser interface.
	 */
	public void logIn(IUser user) {
		loginFld.clear();
		loginFld.sendKeys(user.getLogin());
		passwordFld.clear();
		passwordFld.sendKeys(user.getPassword());
		submitBtn.click();
		assertEquals(
				user.getRole(),
				driver.findElement(
						By.xpath("//div[@id='content']/div/fieldset/table/tbody/tr[4]/td[2]"))
						.getText());
	}

	/**
	 * Log out from page.
	 */
	public void logOut() {
		WebElement logout = driver.findElement(By.xpath("//a[@href='/OMS/logout.htm']"));
		logout.click();
	}
}
