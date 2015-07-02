package com.softserve.edu.atqc.span;

public final class TextCriteria {
	private final String DOES_NOT_MATCH = "Values do not match. Expected Result: %s ActualResult: %s";
	private String text;
	private AssertWrapper assertWrapper;

	private TextCriteria(String text,  AssertWrapper assertWrapper) {
		this.text = text;
		this.assertWrapper = assertWrapper;
	}

	public static TextCriteria get(String text,AssertWrapper assertWrapper) {
		return new TextCriteria(text, assertWrapper);
	}

	public TextCriteria valueMatch(String expectedResult) {
		this.assertWrapper.verify(text.equals(expectedResult),
				String.format(DOES_NOT_MATCH, expectedResult, text));
		return this;
	}

	public TextCriteria valueMatch(Integer expectedResult) {
		this.assertWrapper.verify(text.equals(expectedResult.toString()),
				String.format(DOES_NOT_MATCH, expectedResult, text));
		return this;
	}

	public TextCriteria valueStartsWith(String expectedResult) {
		this.assertWrapper.verify(text.startsWith(expectedResult),
				String.format(DOES_NOT_MATCH, expectedResult, text));
		return this;
	}

	public TextCriteria valueByPartialText(String expectedResult) {
		this.assertWrapper.verify(text.contains(expectedResult),
				String.format(DOES_NOT_MATCH, expectedResult, text));
		return this;
	}

	public AssertWrapper next() {
		return assertWrapper;
	}

}
