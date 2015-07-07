package com.softserve.edu.atqc.controls;

public interface ICheckBox extends IButton {

	boolean isSelected();

	ILabelClickable getLabelClickable();

	void setLabelClickable(ILabelClickable labelClickable);

}
