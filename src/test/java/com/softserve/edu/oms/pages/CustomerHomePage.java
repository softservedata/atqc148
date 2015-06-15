package com.softserve.edu.oms.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.softserve.edu.atqc.tools.ContextVisible;

public class CustomerHomePage extends HomePage {
	private WebElement orderingTab;

	public CustomerHomePage() {
		super();
		this.orderingTab = ContextVisible.get().getVisibleWebElement(By.partialLinkText("Ordering"));
	}

	// - - - - - - - - - -

	// public OrderingPage orderingClick() {
	public void orderingClick() {
		this.orderingTab.click();
		// return new OrderingPage();
	}

}