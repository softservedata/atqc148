package com.softserve.edu.page.login;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	private WebDriver driver;
	private WebElement loginFld;
	private WebElement passwordFld;
	private WebElement submitBtn;

	private LoginPage(WebDriver driver) {
		this.driver = driver;
		driver.get("http://localhost:8080/OMS/");
	}
	
	public LoginPage getLoginFields(){
		loginFld = driver.findElement(By.name("j_username"));
		passwordFld = driver.findElement(By.name("j_password"));
		submitBtn = driver.findElement(By.name("submit"));
		return this;
	}

	public static LoginPage setDriver(WebDriver driver) {
		return new LoginPage(driver);
	}

	public WebElement getLoginFld() {
		return loginFld;
	}

	public WebElement getPasswordFld() {
		return passwordFld;
	}

	public WebElement getSubmitBtn() {
		return submitBtn;
	}

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

//	made it static coz 
	public void logOut() {
		WebElement logout = driver.findElement(By.xpath("//a[@href='/OMS/logout.htm']"));
		logout.click();
	}

}
