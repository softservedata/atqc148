package com.softserve.edu.atqc.span;

import com.softserve.edu.atqc.controls.ITextField;

public final class TextFieldCriteria {
	private final String DOES_NOT_MATCH = "Values do not match. Expected Result: %s ActualResult: %s";
	private final String IT_IS_NOT_VISIBLE = "TextInput is not visible";
	private ITextField textField;
	private AssertWrapper assertWrapper;

	private TextFieldCriteria(ITextField textField, AssertWrapper assertWrapper) {
		this.textField = textField;
		this.assertWrapper = assertWrapper;
	}

	public static TextFieldCriteria get(ITextField textField,
			AssertWrapper assertWrapper) {
		return new TextFieldCriteria(textField, assertWrapper);
	}

	public TextFieldCriteria attributeMatch(String attribute,
			String expectedResult) {
		this.assertWrapper.verify(textField.getAttribute(attribute).equals(expectedResult),
				String.format(DOES_NOT_MATCH, expectedResult,
						textField.getAttribute(attribute)));
		return this;
	}

	public TextFieldCriteria valueMatch(String expectedResult) {
		this.assertWrapper.verify(textField.getText().equals(expectedResult),
				String.format(DOES_NOT_MATCH, expectedResult,
						textField.getText()));
		return this;
	}

	public TextFieldCriteria valueStartsWith(String expectedResult) {
		this.assertWrapper.verify(textField.getText().startsWith(expectedResult),
				String.format(DOES_NOT_MATCH, expectedResult,
						textField.getText()));
		return this;
	}

	public TextFieldCriteria valueByPartialText(String expectedResult) {
		this.assertWrapper.verify(textField.getText().contains(expectedResult),
				String.format(DOES_NOT_MATCH, expectedResult,
						textField.getText()));
		return this;
	}

	public TextFieldCriteria isVisible() {
		this.assertWrapper.verify(textField.isDisplayed(), IT_IS_NOT_VISIBLE);
		return this;
	}

	public AssertWrapper next() {
		return assertWrapper;
	}

}
