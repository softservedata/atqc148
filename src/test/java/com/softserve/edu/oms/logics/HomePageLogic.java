package com.softserve.edu.oms.logics;

import com.softserve.edu.oms.pages.HomePage;
import com.softserve.edu.oms.pages.LoginPage;

public abstract class HomePageLogic {
	// Elements
	private HomePage homePage;

	protected HomePageLogic(HomePage homePage) {
		this.homePage = homePage;
	}

	// getters

	public HomePage getHomePage() {
		return homePage;
	}

	// business

	public String getFirstName() {
		return this.homePage.getFirstName().getText();
	}

	public String getLastName() {
		return this.homePage.getLastName().getText();
	}

	public String getRole() {
		return this.homePage.getRole().getText();
	}

	public LoginPageLogic logout() {
		this.homePage.getLogout().click();
		return new LoginPageLogic(new LoginPage());
	}

}
