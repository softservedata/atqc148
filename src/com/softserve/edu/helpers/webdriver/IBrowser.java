package com.softserve.edu.helpers.webdriver;

import org.openqa.selenium.WebDriver;

 interface IBrowser {

	 WebDriver getWebDriver();

	 String getWebDriverName();

	 void quit();
	
	
}
