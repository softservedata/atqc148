package com.softserve.edu.atqc.tools;

import org.openqa.selenium.By;

public final class ControlLocation {
	private By by;
	private String value;

	private ControlLocation(By by, String value) {
		this.by = by;
		this.value = value;
	}

	public static ControlLocation getById(String id) {
		return new ControlLocation(new By.ById(id), id);
	}

	public static ControlLocation getByName(String name) {
		return new ControlLocation(new By.ByName(name), name);
	}

	public static ControlLocation getByXPath(String xpath) {
		return new ControlLocation(new By.ByXPath(xpath), xpath);
	}

	public static ControlLocation getByPartialLinkText(String partialLinkText) {
		return new ControlLocation(new By.ByPartialLinkText(partialLinkText),
				partialLinkText);
	}

	public static ControlLocation getByCssSelector(String cssSelector) {
		return new ControlLocation(new By.ByCssSelector(cssSelector),
				cssSelector);
	}

	public static ControlLocation getByTagName(String tagName) {
		return new ControlLocation(new By.ByTagName(tagName), tagName);
	}

	By getBy() {
		return by;
	}

	public String getValue() {
		return value;
	}

}
