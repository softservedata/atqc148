package com.softserve.edu.atqc.controls;

public interface IComponent {

	String getAttribute(String attribute);

	String getAttributeName();

	String getTagName();

	boolean isDisplayed();
	
//	boolean isInvisible();
	
	boolean isStalenessOf();

}
