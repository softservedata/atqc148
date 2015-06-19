package com.softserve.edu.atqc.controls;

public interface IButton extends ILabelClickable {

	boolean isEnabled();

	void setFocus();

	void submit();

}
