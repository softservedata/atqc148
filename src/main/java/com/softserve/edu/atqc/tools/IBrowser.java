package com.softserve.edu.atqc.tools;

import org.openqa.selenium.WebDriver;

public interface IBrowser {

	WebDriver getWebDriver();

	String getWebDriverName();

	void quit();

}
