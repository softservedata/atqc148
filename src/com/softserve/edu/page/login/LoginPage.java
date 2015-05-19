package com.softserve.edu.page.login;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void logIn(String login, String password, String role) {
		driver.get("http://localhost:8080/OMS/");
		driver.findElement(By.name("j_username")).clear();
		driver.findElement(By.name("j_username")).sendKeys(login);
		driver.findElement(By.name("j_password")).clear();
		driver.findElement(By.name("j_password")).sendKeys(password);
		driver.findElement(By.name("submit")).click();
		assertEquals(
				role,
				driver.findElement(
						By.xpath("//div[@id='content']/div/fieldset/table/tbody/tr[4]/td[2]"))
						.getText());
		// return new userpage(driver); or orderpage(driver); can be good idea

	}

	public void logOut() {
		driver.findElement(By.cssSelector("img[alt=\"logout\"]")).click();
	}

}
