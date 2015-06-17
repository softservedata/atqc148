package com.softserve.edu.atqc.tools;

import org.openqa.selenium.WebElement;

public final class ControlWrapper {
	private final String ATTRIBUTE_HREF = "href";
	private final String ATTRIBUTE_NAME = "name";
	private WebElement webElement;

	private ControlWrapper(WebElement webElement) {
		this.webElement = webElement;
	}

	static ControlWrapper get(WebElement webElement) {
		return new ControlWrapper(webElement);
	}

	public static ControlWrapper getVisibleWebElement(
			ControlLocation controlLocation) {
		return new ControlWrapper(ContextVisible.get().getVisibleWebElement(
				controlLocation));
	}

	public static ControlWrapper getPresentWebElement(
			ControlLocation controlLocation) {
		return new ControlWrapper(ContextVisible.get().getPresentWebElement(
				controlLocation));
	}

	WebElement getWebElement() {
		return webElement;
	}

	public boolean isSelected() {
		return getWebElement().isSelected();
	}

	public boolean isDisplayed() {
		return getWebElement().isDisplayed();
	}

	public boolean isEnabled() {
		return getWebElement().isEnabled();
	}

	public String getContent() {
		return getWebElement().getText();
	}

	public String getText() {
		return getWebElement().getText();
	}

	public void sendKeys(String text) {
		getWebElement().sendKeys(text);
	}

	public void submit() {
		getWebElement().submit();
	}

	public String getTagName() {
		return getWebElement().getTagName();
	}

	public String getUrl() {
		return getWebElement().getAttribute(ATTRIBUTE_HREF);
	}

	public String getName() {
		return getWebElement().getAttribute(ATTRIBUTE_NAME);
	}

	public String getAttribute(String attribute) {
		return getWebElement().getAttribute(attribute);
	}

	public void clear() {
		getWebElement().clear();
	}

	public void click() {
		getWebElement().click();
	}

}
