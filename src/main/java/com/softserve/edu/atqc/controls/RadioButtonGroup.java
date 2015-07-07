package com.softserve.edu.atqc.controls;

import java.util.ArrayList;
import java.util.List;

import com.softserve.edu.atqc.tools.ControlLocation;
import com.softserve.edu.atqc.tools.ControlWrapper;

public class RadioButtonGroup implements IRadioButtonGroup {
	private List<IRadioButton> radioButtons;
	private ControlLocation controlLocation;
	
	// implements constructor

	RadioButtonGroup(List<ControlWrapper> controlWrappers, ControlLocation controlLocation) {
		radioButtons = new ArrayList<IRadioButton>();
		for (ControlWrapper controlWrapper : controlWrappers) {
			radioButtons.add(new Component(controlWrapper,controlLocation));
		}
		this.controlLocation = controlLocation;
	}

	// implements static factory

	static IRadioButtonGroup getByControl(List<ControlWrapper> controlWrappers,
			ControlLocation controlLocation) {
		return new RadioButtonGroup(controlWrappers, controlLocation);
	}

	public static IRadioButtonGroup getByName(String name) {
		return get(ControlLocation.getByName(name));
	}

	static IRadioButtonGroup get(ControlLocation controlLocation) {
		// TODO Change strategy Visible/Present
		return getByControl(ControlWrapper.getVisibleWebElements(controlLocation),
				controlLocation);
	}

}
