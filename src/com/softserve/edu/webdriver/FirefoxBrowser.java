package com.softserve.edu.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxBrowser implements IBrowser {

	WebDriver driver;

	@Override
	public WebDriver getWebDriver() {
		return new FirefoxDriver();
	}

	@Override
	public void quit() {
		this.driver.quit();
	}

}
