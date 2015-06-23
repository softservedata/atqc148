package com.softserve.edu.oms.pages;

import com.softserve.edu.atqc.controls.ILabel;
import com.softserve.edu.atqc.controls.Label;

public class ValidatorLoginPage extends LoginPage {
	public static enum LoginPageValidators {
		UNSUCCESS_VALIDATOR("Your login attempt was not successful, try again.");
		private String field;

		private LoginPageValidators(String field) {
			this.field = field;
		}

		@Override
		public String toString() {
			return this.field;
		}
	}

	private class ValidatorLoginPageUIMap {
		public final ILabel unSuccessValidator;

		public ValidatorLoginPageUIMap() {
			this.unSuccessValidator = Label
					.getByXpath("//div[@id='edit']//font");
		}
	}

	// Elements
	private ValidatorLoginPageUIMap controls;

	public ValidatorLoginPage() {
		this.controls = new ValidatorLoginPageUIMap();
	}

	// getters

	public ILabel getUnSuccessValidator() {
		return controls.unSuccessValidator;
	}

}
