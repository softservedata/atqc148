package com.softserve.edu.atqc.controls;

import java.util.List;

public interface ISelect extends IComponent {

	List<ILabel> getAllOptions();

	ILabel getFirstSelectedOption();

	void selectByIndex(int index);

	void selectByValue(String value);

	void selectByVisibleText(String text);

	void selectByPartialText(String partialText);

}
