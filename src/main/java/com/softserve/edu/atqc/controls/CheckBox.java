package com.softserve.edu.atqc.controls;

import com.softserve.edu.atqc.tools.ControlLocation;
import com.softserve.edu.atqc.tools.ControlWrapper;

public class CheckBox extends Button {

	// implements constructor

	CheckBox(ControlWrapper control, ControlLocation controlLocation) {
		super(control, controlLocation);
	}

	// implements static factory

	static IControlWrapper getByControl(ControlWrapper control,
			ControlLocation controlLocation) {
		return new CheckBox(control, controlLocation);
	}

}
