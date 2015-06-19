package com.softserve.edu.atqc.controls;

import com.softserve.edu.atqc.tools.ControlLocation;
import com.softserve.edu.atqc.tools.ControlWrapper;

public class Label extends Component {

	// implements constructor

	Label(ControlWrapper control, ControlLocation controlLocation) {
		super(control, controlLocation);
	}

	// implements static factory

	static IControlWrapper getByControl(ControlWrapper control,
			ControlLocation controlLocation) {
		return new Label(control, controlLocation);
	}

}
