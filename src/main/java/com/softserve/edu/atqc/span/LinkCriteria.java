package com.softserve.edu.atqc.span;

import com.softserve.edu.atqc.controls.ILink;

public final class LinkCriteria {
	private final String DOES_NOT_MATCH = "Values do not match. Expected Result: [%s] ActualResult: [%s]";
	private final String IT_IS_NOT_VISIBLE = "Link is not visible";
	private ILink link;
	private AssertWrapper assertWrapper;

	private LinkCriteria(ILink link, AssertWrapper assertWrapper) {
		this.link = link;
		this.assertWrapper = assertWrapper;
	}

	public static LinkCriteria get(ILink link, AssertWrapper assertWrapper) {
		return new LinkCriteria(link, assertWrapper);
	}

	public LinkCriteria urlMatch(String expectedResult) {
		this.assertWrapper.verify(link.getUrl().equals(expectedResult),
				String.format(DOES_NOT_MATCH, expectedResult, link.getUrl()));
		return this;
	}

	public LinkCriteria valueMatch(String expectedResult) {
		this.assertWrapper.verify(link.getText().equals(expectedResult),
				String.format(DOES_NOT_MATCH, expectedResult, link.getText()));
		return this;
	}

	public LinkCriteria valueStartsWith(String expectedResult) {
		this.assertWrapper.verify(link.getText().startsWith(expectedResult),
				String.format(DOES_NOT_MATCH, expectedResult, link.getText()));
		return this;
	}

	public LinkCriteria valueByPartialText(String expectedResult) {
		this.assertWrapper.verify(link.getText().contains(expectedResult),
				String.format(DOES_NOT_MATCH, expectedResult, link.getText()));
		return this;
	}

	public LinkCriteria isVisible() {
		this.assertWrapper.verify(link.isDisplayed(), IT_IS_NOT_VISIBLE);
		return this;
	}
	
	public AssertWrapper next() {
		return assertWrapper;
	}

}
