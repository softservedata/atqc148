package com.softserve.edu.atqc.tools;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public final class SelectWrapper {
	private final String ELEMENT_NOT_FOUND = "WebElement by partial text was not found %s";
	private Select selectWebElement;

	private SelectWrapper(Select selectWebElement) {
		this.selectWebElement = selectWebElement;
	}

	static SelectWrapper get(Select selectWebElement) {
		return new SelectWrapper(selectWebElement);
	}

	public static SelectWrapper getVisibleSelectWebElement(
			ControlLocation controlLocation) {
		return new SelectWrapper(ContextVisible.get().getVisibleSelectWebElement(
				controlLocation));
	}

	public static SelectWrapper getVisibleSelectWebElement(
			ControlWrapper controlWrapper) {
		return new SelectWrapper(ContextVisible.get().getVisibleSelectWebElement(
				controlWrapper));
	}

	public static SelectWrapper getPresentSelectWebElement(
			ControlLocation controlLocation) {
		return new SelectWrapper(ContextVisible.get().getPresentSelectWebElement(
				controlLocation));
	}

	public static SelectWrapper getPresentSelectWebElement(
			ControlWrapper controlWrapper) {
		return new SelectWrapper(ContextVisible.get().getVisibleSelectWebElement(
				controlWrapper));
	}

	Select getSelectWebElement() {
		return selectWebElement;
	}

	// for implements interface

	public List<ControlWrapper> getSelectWebElements() {
		List<ControlWrapper> selectWebElements = new ArrayList<ControlWrapper>();
		for (WebElement webElement : getSelectWebElement().getOptions()) {
			selectWebElements.add(ControlWrapper.get(webElement));
		}
		return selectWebElements;
	}

	public ControlWrapper getFirstSelectedOption() {
		return ControlWrapper.get(getSelectWebElement().getFirstSelectedOption());
	}
	
	public void selectByIndex(int index) {
		getSelectWebElement().selectByIndex(index);
	}

	public void selectByValue(String value) {
		getSelectWebElement().selectByValue(value);
	}

	public void selectByVisibleText(String text) {
		getSelectWebElement().selectByVisibleText(text);
	}

	public void selectByPartialText(String partialText) {
		boolean isTextNotFound = true;
		for (ControlWrapper controlWrapper : getSelectWebElements()) {
			if (controlWrapper.getText().indexOf(partialText) >= 0) {
				isTextNotFound = false;
				controlWrapper.click();
				break;
			}
		}
		if (isTextNotFound) {
			// TODO Develop My Exception
			throw new RuntimeException(String.format(ELEMENT_NOT_FOUND,
					partialText));
		}
	}

}
