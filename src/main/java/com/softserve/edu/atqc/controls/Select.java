package com.softserve.edu.atqc.controls;

import com.softserve.edu.atqc.tools.ControlLocation;
import com.softserve.edu.atqc.tools.ControlWrapper;
import com.softserve.edu.atqc.tools.SelectWrapper;

public class Select extends Component {
	//private SelectWrapper select;

	// implements constructor

	Select(ControlWrapper control, ControlLocation controlLocation) {
		super(control, SelectWrapper.getVisibleSelectWebElement(control), controlLocation);
		//this.select = SelectWrapper.getVisibleSelectWebElement(control); 
	}

	// implements static factory

	static IControlWrapper getByControl(ControlWrapper control,
			ControlLocation controlLocation) {
		return new Select(control, controlLocation);
	}

	// implements getters

//	SelectWrapper getSelect() {
//		return select;
//	}

	// ISelect

	/*
	public List<ILabel> getAllOptions() {
		List<ILabel> selectLabels = new ArrayList<ILabel>();
		for (ControlWrapper controlWrapper : getSelect().getSelectWebElements()) {
			selectLabels.add(Label.getByControl(
					controlWrapper,
					ControlLocation.getByXPath("//option[text()='"
							+ controlWrapper.getText() + "']")));
		}
		return selectLabels;
	}

	public ILabel getFirstSelectedOption() {
		return Label.getByControl(
				getSelect().getFirstSelectedOption(),
				ControlLocation.getByXPath("//option[text()='"
						+ getSelect().getFirstSelectedOption() + "']"));
	}

	public void selectByIndex(int index) {
		getSelect().selectByIndex(index);
	}

	public void selectByValue(String value) {
		getSelect().selectByValue(value);
	}

	public void selectByVisibleText(String text) {
		System.out.println("class SELECT selectByVisibleText START text="+text);
		getSelect().selectByVisibleText(text);
		System.out.println("class SELECT selectByVisibleText done");
	}

	public void selectByPartialText(String partialText) {
		getSelect().selectByPartialText(partialText);
	}
	 */
}
