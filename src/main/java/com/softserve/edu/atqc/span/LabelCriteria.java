package com.softserve.edu.atqc.span;

import java.util.List;

import com.softserve.edu.atqc.controls.ILabel;

public class LabelCriteria {
	private final String DOES_NOT_MATCH = "Values do not match. Expected Result: [%s] ActualResult: [%s]";
	private final String IT_IS_NOT_VISIBLE = "Wrong page? Label is not visible.";
	private ILabel label;
	private AssertWrapper assertWrapper;

	private LabelCriteria(ILabel label, AssertWrapper assertWrapper) {
		this.label = label;
		this.assertWrapper = assertWrapper;
	}

	public static LabelCriteria get(ILabel label,
			AssertWrapper assertWrapper) {
		return new LabelCriteria(label, assertWrapper);
	}

	public LabelCriteria valueMatch(String expectedResult) {
		this.assertWrapper.verify(label.getText().equals(expectedResult),
				String.format(DOES_NOT_MATCH, expectedResult, label.getText()));
		return this;
	}
	
	public LabelCriteria valueMatch(String expectedResult, String message) {
		this.assertWrapper.verify(label.getText().equals(expectedResult),
				String.format(DOES_NOT_MATCH, expectedResult, label.getText() + ". " + message));
		return this;
	}
	
	public LabelCriteria valueMatch(Integer expectedResult) {
		this.assertWrapper.verify(label.getText().equals(expectedResult.toString()),
				String.format(DOES_NOT_MATCH, expectedResult, label.getText()));
		return this;
	}

	public LabelCriteria valueStartsWith(String expectedResult) {
		this.assertWrapper.verify(label.getText().startsWith(expectedResult),
				String.format(DOES_NOT_MATCH, expectedResult, label.getText()));
		return this;
	}

	public LabelCriteria valueByPartialText(String expectedResult) {
		this.assertWrapper.verify(label.getText().contains(expectedResult),
				String.format(DOES_NOT_MATCH, expectedResult, label.getText()));
		return this;
	}

	public LabelCriteria isVisible() {
		this.assertWrapper.verify(label.isDisplayed(), IT_IS_NOT_VISIBLE);
		return this;
	}
	
	public LabelCriteria valueMatchIgnoreCase(String expectedResult) {
		this.assertWrapper.verify(label.getText().toLowerCase().trim().equals(expectedResult.toLowerCase().trim()),
				String.format(DOES_NOT_MATCH, expectedResult.toLowerCase().trim(), label.getText().toLowerCase().trim()));
		return this;
	}
	
	public LabelCriteria valueByPartialTextList(List<String> expectedResult) {
		boolean result = false;
		for (String current : expectedResult) {
			result = result ||
					label.getText().contains(current);
			
			if (result) {
				break;
			}
		}
		this.assertWrapper.verify(
				result,
				String.format(DOES_NOT_MATCH, expectedResult.toString(),
						label.getText()));
		return this;
	}

	public AssertWrapper next() {
		return assertWrapper;
	}

}
