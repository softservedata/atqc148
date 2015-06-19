package com.softserve.edu.atqc.controls;

import com.softserve.edu.atqc.tools.ControlLocation;
import com.softserve.edu.atqc.tools.ControlWrapper;

public class Button extends LabelClickable {

	// implements constructor

	Button(ControlWrapper control, ControlLocation controlLocation) {
		super(control, controlLocation);
	}

	// implements static factory

	static IControlWrapper getByControl(ControlWrapper control,
			ControlLocation controlLocation) {
		return new Button(control, controlLocation);
	}

}
