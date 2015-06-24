package com.softserve.edu.oms.logics;

import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.pages.AdministrationPage;
import com.softserve.edu.oms.pages.LoginPage;
import com.softserve.edu.oms.pages.AdministrationPage.AdministrationPageConditions;
import com.softserve.edu.oms.pages.AdministrationPage.AdministrationPageFields;

public class AdministrationPageLogic {
	// Elements
	private AdministrationPage administrationPage;

	public AdministrationPageLogic(AdministrationPage administrationPage) {
		this.administrationPage = administrationPage;
	}

	// getters

	public AdministrationPage getAdministrationPage() {
		return administrationPage;
	}

	// business

	public CreateNewUserPageLogic gotoCreateNewUser() {
		this.administrationPage.getCreateNewUser().click();
		// TODO CreateNewUserPageLogic(new CreateNewUserPage());
		return new CreateNewUserPageLogic();
	}

	public void searchByLoginName(AdministrationPageFields field,
			AdministrationPageConditions condition, IUser user) {
		this.administrationPage.selectColumnFields(field);
		this.administrationPage.selectMatchConditions(condition);
		this.administrationPage.searchFieldSendKeys(user.getLoginName());
		// Initialize AJAX Elements
		this.administrationPage.resetTable(user.getLoginName());
	}

	public void deleteByLoginName(IUser user) {
		searchByLoginName(AdministrationPageFields.LOGIN_NAME,
				AdministrationPageConditions.STARTS_WITH, user);
		this.administrationPage.deleteClick();
		this.administrationPage.alertAccept();
	}
	
	public LoginPageLogic logout() {
		this.administrationPage.getLogout().click();
		return new LoginPageLogic(new LoginPage());
	}

}
