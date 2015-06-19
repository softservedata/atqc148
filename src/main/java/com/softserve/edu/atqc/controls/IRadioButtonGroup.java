package com.softserve.edu.atqc.controls;

public interface IRadioButtonGroup {

	int getRadioButtonSize();

	void selectByPartialText(String partialText);

	IRadioButton getSelectedRadioButton();

}
