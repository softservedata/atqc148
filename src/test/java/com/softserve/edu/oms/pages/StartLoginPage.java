package com.softserve.edu.oms.pages;

import com.softserve.edu.atqc.tools.IBrowser;
import com.softserve.edu.atqc.tools.WebDriverUtils;

public class StartLoginPage extends LoginPage {

	private StartLoginPage() {
		super();
	}

	public static StartLoginPage load(IBrowser browser, String url) {
		//driver = new FirefoxDriver();
		//driver.get(url);
		WebDriverUtils.get(browser).load(url);
		return new StartLoginPage();
	}
}
