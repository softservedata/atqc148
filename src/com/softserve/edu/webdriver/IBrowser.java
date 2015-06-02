package com.softserve.edu.webdriver;

import org.openqa.selenium.WebDriver;

 interface IBrowser {

	 WebDriver getWebDriver();

	 String getWebDriverName();

	 void quit();
	
	
}
