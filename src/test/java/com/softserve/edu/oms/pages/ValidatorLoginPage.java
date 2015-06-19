package com.softserve.edu.oms.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.softserve.edu.atqc.tools.ContextVisible;

public class ValidatorLoginPage extends LoginPage {
	public static enum Validators {
		UNSUCCESS_VALIDATOR("Your login attempt was not successful, try again.");
		private String field;

		private Validators(String field) {
			this.field = field;
		}

		@Override
		public String toString() {
			return this.field;
		}
	}

	/*
	private WebElement unSuccessValidator;

	public ValidatorLoginPage() {
		super();
		this.unSuccessValidator = ContextVisible.get().getVisibleWebElement(By.xpath("//div[@id='edit']//font") );
	}

	public WebElement getUnSuccessValidator() {
		return this.unSuccessValidator;
	}
*/
}
