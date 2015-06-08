package main.java.edu.atqc.helpers.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxBrowser implements IBrowser {


	@Override
	public WebDriver getWebDriver() {
		return new FirefoxDriver();
	}

	@Override
	public void quit() {
	}

}
