package com.softserve.edu.oms.logics;

import com.softserve.edu.oms.pages.ValidatorLoginPage;

public class ValidatorLoginPageLogic extends LoginPageLogic {
	// Elements
	private ValidatorLoginPage validatorLoginPage;

	public ValidatorLoginPageLogic(ValidatorLoginPage validatorLoginPage) {
		super(validatorLoginPage);
		this.validatorLoginPage = validatorLoginPage;
	}

	// getters

	public ValidatorLoginPage getValidatorLoginPage() {
		return validatorLoginPage;
	}

	// business

	public String getUnSuccessValidator() {
		return this.validatorLoginPage.getUnSuccessValidator().getText();
	}

}
