package com.softserve.edu.oms.logics;

import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.pages.AdminHomePage;
import com.softserve.edu.oms.pages.CustomerHomePage;
import com.softserve.edu.oms.pages.LoginPage;
import com.softserve.edu.oms.pages.ValidatorLoginPage;

public class LoginPageLogic {
	// Elements
	private LoginPage loginPage;

	// public LoginPageLogic() {
	// this.loginPage = new LoginPage();
	// }

	public LoginPageLogic(LoginPage loginPage) {
		this.loginPage = loginPage;
	}

	// getters

	public LoginPage getLoginPage() {
		return loginPage;
	}

	// business

	private void setLoginData(IUser user) {
		this.loginPage.getLoginName().clear();
		this.loginPage.setLoginName(user.getLoginName());
		this.loginPage.getPassword().clear();
		this.loginPage.setPassword(user.getPassword());
		this.loginPage.submitClick();
	}

	public AdminHomePageLogic successAdminLogin(IUser adminUser) {
		setLoginData(adminUser);
		return new AdminHomePageLogic(new AdminHomePage());
	}

	public CustomerHomePageLogic successCustomerLogin(IUser customerUser) {
		setLoginData(customerUser);
		return new CustomerHomePageLogic(new CustomerHomePage());
	}

	public ValidatorLoginPageLogic unSuccesfulLogin(IUser invalidUser) {
		setLoginData(invalidUser);
		return new ValidatorLoginPageLogic(new ValidatorLoginPage());
	}

}
