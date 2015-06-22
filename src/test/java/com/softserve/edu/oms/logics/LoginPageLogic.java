package com.softserve.edu.oms.logics;

import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.pages.LoginPage;

public class LoginPageLogic {
	// Elements
	private LoginPage loginPage;

	public LoginPageLogic() {
		this.loginPage = new LoginPage();
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
		return new AdminHomePageLogic();
	}

	public CustomerHomePageLogic successCustomerLogin(IUser customerUser) {
		setLoginData(customerUser);
		return new CustomerHomePageLogic();
	}

	public ValidatorLoginPageLogic unSuccesfulLogin(IUser invalidUser) {
		setLoginData(invalidUser);
		return new ValidatorLoginPageLogic(); // return this;
	}

}
