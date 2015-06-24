package com.softserve.edu.oms.logics;

import com.softserve.edu.oms.pages.AdminHomePage;
import com.softserve.edu.oms.pages.AdministrationPage;

public class AdminHomePageLogic extends HomePageLogic {
	// Elements
	private AdminHomePage adminHomePage;

	public AdminHomePageLogic(AdminHomePage adminHomePage) {
		super(adminHomePage);
		this.adminHomePage = adminHomePage;
	}

	// getters

	public AdminHomePage getAdminHomePage() {
		return adminHomePage;
	}

	// business

	public AdministrationPageLogic gotoAdministration() {
		this.adminHomePage.getAdministrationTab().click();
		return new AdministrationPageLogic(new AdministrationPage());
	}

}
