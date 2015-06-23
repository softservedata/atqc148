package com.softserve.edu.oms.pages;

import com.softserve.edu.atqc.controls.ILink;
import com.softserve.edu.atqc.controls.Link;

public class AdminHomePage extends HomePage {
	private class AdminHomePageUIMap {
		public final ILink administrationTab;

		public AdminHomePageUIMap() {
			this.administrationTab = Link
					.getByPartialLinkText("Administration");
		}
	}

	// Elements
	private AdminHomePageUIMap controls;

	public AdminHomePage() {
		super();
		this.controls = new AdminHomePageUIMap();
	}

	// getters

	public ILink getAdministrationTab() {
		return this.controls.administrationTab;
	}

	// setters

	public void administrationTabClick() {
		this.controls.administrationTab.click();
	}

	/*
	 * 
	 * public AdministrationPage administrationClick() { // public void
	 * administrationClick() { this.administrationTab.click(); return new
	 * AdministrationPage(); }
	 */
}
