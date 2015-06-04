package com.softserve.edu.helpers.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxBrowser implements IBrowser {

	WebDriver driver;

	@Override
	public WebDriver getWebDriver() {
		return new FirefoxDriver();
	}

	@Override
	public String getWebDriverName() {
		return "FirefoxBrowser";
	}

	@Override
	public void quit() {
		this.driver.quit();
	}

}
