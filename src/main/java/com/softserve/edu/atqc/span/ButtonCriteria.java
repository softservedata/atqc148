package com.softserve.edu.atqc.span;

import com.softserve.edu.atqc.controls.IButton;

public final class ButtonCriteria {
	private final String DOES_NOT_MATCH = "Values do not match. Expected Result: [%s] ActualResult: [%s]";
	private final String IT_IS_NOT_VISIBLE = "Button is not visible";
	private final String IT_IS_NOT_ENABLED = "Button is not enabled";
	private final String IT_IS_ENABLED = "Button is enabled, but expected Disabled";
	private IButton button;
	private AssertWrapper assertWrapper;

	private ButtonCriteria(IButton button, AssertWrapper assertWrapper) {
		this.button = button;
		this.assertWrapper = assertWrapper;
	}

	public static ButtonCriteria get(IButton button, AssertWrapper assertWrapper) {
		return new ButtonCriteria(button, assertWrapper);
	}

	public ButtonCriteria valueMatch(String expectedResult) {
		this.assertWrapper.verify(button.getText().equals(expectedResult),
				String.format(DOES_NOT_MATCH, expectedResult, button.getText()));
		return this;
	}

	public ButtonCriteria valueStartsWith(String expectedResult) {
		this.assertWrapper.verify(button.getText().startsWith(expectedResult),
				String.format(DOES_NOT_MATCH, expectedResult, button.getText()));
		return this;
	}

	public ButtonCriteria valueByPartialText(String expectedResult) {
		this.assertWrapper.verify(button.getText().contains(expectedResult),
				String.format(DOES_NOT_MATCH, expectedResult, button.getText()));
		return this;
	}
	
	public ButtonCriteria isVisible() {
		this.assertWrapper.verify(button.isDisplayed(), IT_IS_NOT_VISIBLE);
		return this;
	}

	public ButtonCriteria isDisabled() {
		this.assertWrapper.verify(!button.isEnabled(), IT_IS_ENABLED);
		return this;
	}

	public ButtonCriteria isEnabled() {
		this.assertWrapper.verify(button.isEnabled(), IT_IS_NOT_ENABLED);
		return this;
	}

	public AssertWrapper next() {
		return assertWrapper;
	}

}
