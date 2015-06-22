package com.softserve.edu.atqc.controls;

import com.softserve.edu.atqc.tools.ControlLocation;
import com.softserve.edu.atqc.tools.ControlWrapper;

public class Link extends LabelClickable {

	// implements constructor

	Link(ControlWrapper control, ControlLocation controlLocation) {
		super(control, controlLocation);
	}

	// implements static factory

	static IControlWrapper getByControl(ControlWrapper control,
			ControlLocation controlLocation) {
		return new Link(control, controlLocation);
	}

	public static IControlWrapper getByPartialLinkText(String partialLinkText) {
		return get(ControlLocation.getByPartialLinkText(partialLinkText));
	}

}
