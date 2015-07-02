package com.softserve.edu.atqc.controls;

import java.util.ArrayList;
import java.util.List;

import com.softserve.edu.atqc.tools.ContextVisible;
import com.softserve.edu.atqc.tools.ControlLocation;
import com.softserve.edu.atqc.tools.ControlWrapper;
import com.softserve.edu.atqc.tools.SelectWrapper;

public class Component implements IControlWrapper {
	private final String SELECT_TAG = "select";
	private ControlWrapper control;
	private SelectWrapper select;
	private ControlLocation controlLocation;

	// implements constructor

	Component(ControlWrapper control, ControlLocation controlLocation) {
		this.control = control;
		this.controlLocation = controlLocation;
		if (this.control.getTagName().toLowerCase().contains(SELECT_TAG)) {
			this.select = SelectWrapper.getVisibleSelectWebElement(control);
		}
	}

	Component(ControlWrapper control, SelectWrapper select,
			ControlLocation controlLocation) {
		this.control = control;
		this.controlLocation = controlLocation;
		// this.select = SelectWrapper.getVisibleSelectWebElement(control);
		this.select = select;
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

	SelectWrapper getSelect() {
		return select;
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

	// public boolean isInvisible();

	public boolean isSelected() {
		return getControl().isSelected();
	}

	public boolean isStalenessOf() {
		return ContextVisible.get().isStalenessOfWebElement(getControl());
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

	// ISelect

	public List<ILabel> getAllOptions() {
		List<ILabel> selectLabels = new ArrayList<ILabel>();
		for (ControlWrapper controlWrapper : getSelect().getSelectWebElements()) {
			selectLabels.add(Label.getByControl(
					controlWrapper,
					ControlLocation.getByXPath("//option[text()='"
							+ controlWrapper.getText() + "']")));
		}
		return selectLabels;
	}

	public ILabel getFirstSelectedOption() {
		return Label.getByControl(
				getSelect().getFirstSelectedOption(),
				ControlLocation.getByXPath("//option[text()='"
						+ getSelect().getFirstSelectedOption() + "']"));
	}

	public void selectByIndex(int index) {
		getSelect().selectByIndex(index);
	}

	public void selectByValue(String value) {
		getSelect().selectByValue(value);
	}

	public void selectByVisibleText(String text) {
		getSelect().selectByVisibleText(text);
	}

	public void selectByPartialText(String partialText) {
		getSelect().selectByPartialText(partialText);
	}

}
