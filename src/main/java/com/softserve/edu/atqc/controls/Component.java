package com.softserve.edu.atqc.controls;

import com.softserve.edu.atqc.tools.ControlLocation;
import com.softserve.edu.atqc.tools.ControlWrapper;

public class Component implements IControlWrapper {
	private ControlWrapper control;
	private ControlLocation controlLocation;

	// implements constructor

	Component(ControlWrapper control, ControlLocation controlLocation) {
		this.control = control;
		this.controlLocation = controlLocation;
	}

	// implements static factory

	static IControlWrapper getByControl(ControlWrapper control,
			ControlLocation controlLocation) {
		return new Component(control, controlLocation);
	}

	public static IControlWrapper getById(String id) {
		return get(ControlLocation.getById(id));
	}

	public static IControlWrapper getByName(String name) {
		return get(ControlLocation.getByName(name));
	}

	public static IControlWrapper getByXpath(String xpath) {
		return get(ControlLocation.getByXPath(xpath));
	}

	public static IControlWrapper getByCssSelector(String cssSelector) {
		return get(ControlLocation.getByCssSelector(cssSelector));
	}

	public static IControlWrapper getByTagName(String tagName) {
		return get(ControlLocation.getByTagName(tagName));
	}

	static IControlWrapper get(ControlLocation controlLocation) {
		// TODO Change strategy Visible/Present
		return getByControl(
				ControlWrapper.getVisibleWebElement(controlLocation),
				controlLocation);
	}

	// implements getters

	ControlWrapper getControl() {
		return control;
	}

	ControlLocation getControlLocation() {
		return controlLocation;
	}

	// implements interface

	public String getAttribute(String attribute) {
		return getControl().getAttribute(attribute);
	}

	public String getAttributeName() {
		return getControl().getAttributeName();
	}

	public String getContent() {
		return getControl().getContent();
	}

	public String getTagName() {
		return getControl().getTagName();
	}

	public String getText() {
		return getControl().getText();
	}

	public String getUrl() {
		return getControl().getUrl();
	}

	public void clear() {
		getControl().clear();
	}

	public void click() {
		getControl().click();
	}

	public boolean isDisplayed() {
		return getControl().isDisplayed();
	}

	public boolean isEnabled() {
		return getControl().isEnabled();
	}

	public boolean isSelected() {
		return getControl().isSelected();
	}

	public void sendKeys(String text) {
		getControl().sendKeys(text);
	}

	public void setFocus() {
		getControl().setFocus();
	}

	public void submit() {
		getControl().submit();
	}

}
