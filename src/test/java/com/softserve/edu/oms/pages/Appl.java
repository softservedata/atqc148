package com.softserve.edu.oms.pages;

import com.softserve.edu.oms.pages.ValidatorLoginPage.LoginPageValidators;

public class Appl {
	public static void main(String[] args) {
		// System.out.println("Validators = "+Validators.UNSUCCESS_VALIDATOR);
		for (LoginPageValidators v : LoginPageValidators.values()) {
			System.out.println("number of v = " + v.ordinal());
			System.out.println("number of v = " + v);
		}
	}
}
