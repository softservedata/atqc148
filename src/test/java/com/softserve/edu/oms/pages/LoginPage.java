package com.softserve.edu.oms.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.softserve.edu.atqc.controls.Label;
import com.softserve.edu.atqc.tools.ContextVisible;
import com.softserve.edu.oms.data.IUser;

public class LoginPage {
	/*
	private ILabel oms = Label.getByXpath("");
	//Calendar
	private WebElement loginName;
	private WebElement password;
	private WebElement send;

	public LoginPage() {
		this.loginName = ContextVisible.get().getVisibleWebElement(By.name("j_username"));
		this.password = ContextVisible.get().getVisibleWebElement(By.name("j_password"));
		this.send = ContextVisible.get().getVisibleWebElement(By.xpath("//input[@type='submit']"));
	}

	private void setLoginData(IUser user) {
		oms.
		this.loginName.click();
		this.loginName.clear();
		this.loginName.sendKeys(user.getLoginName());
		this.password.click();
		this.password.clear();
		this.password.sendKeys(user.getPassword());
		// this.password.submit();
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

	public ValidatorLoginPage unSuccesfulLogin(IUser invalidUser) {
		setLoginData(invalidUser);
		return new ValidatorLoginPage(); // return this;
	}
*/
}
