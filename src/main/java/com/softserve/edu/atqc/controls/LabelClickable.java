package com.softserve.edu.atqc.controls;

import com.softserve.edu.atqc.tools.ControlLocation;
import com.softserve.edu.atqc.tools.ControlWrapper;

public class LabelClickable extends Label {

	// implements constructor

	LabelClickable(ControlWrapper control, ControlLocation controlLocation) {
		super(control, controlLocation);
	}

	// implements static factory

	static IControlWrapper getByControl(ControlWrapper control,
			ControlLocation controlLocation) {
		return new LabelClickable(control, controlLocation);
	}

}
