package com.softserve.edu.oms.spans;

import com.softserve.edu.atqc.tools.IBrowser;
import com.softserve.edu.atqc.tools.WebDriverUtils;
import com.softserve.edu.oms.logics.LoginPageLogic;
import com.softserve.edu.oms.pages.LoginPage;

public class StartApplication extends LoginPageLogic {

	private StartApplication() {
		super(new LoginPage());
	}

	public static StartApplication load(IBrowser browser, String url) {
		WebDriverUtils.get(browser).load(url);
		return new StartApplication();
	}

}
