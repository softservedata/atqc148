package com.softserve.edu.oms.pages;

import com.softserve.edu.atqc.controls.ILabel;
import com.softserve.edu.atqc.controls.ILink;
import com.softserve.edu.atqc.controls.Label;
import com.softserve.edu.atqc.controls.Link;

public abstract class HomePage {
	private class HomePageUIMap {
		public final ILabel firstName;
		public final ILabel lastName;
		public final ILabel role;
		public final ILink logout;

		public HomePageUIMap() {
			this.firstName = Label
					.getByXpath("//tbody/tr/td[text( )='First name']/following-sibling::td");
			this.lastName = Label
					.getByXpath("//tbody/tr/td[text( )='Last name']/following-sibling::td");
			this.role = Label
					.getByXpath("//tbody/tr/td[text( )='Role']/following-sibling::td");
			this.logout = Link.getByXpath("//a[@href='/OMS/logout.htm']");
		}
	}

	// Elements
	private HomePageUIMap controls;

	protected HomePage() {
		this.controls = new HomePageUIMap();
	}

	// getters

	public ILabel getFirstName() {
		return this.controls.firstName;
	}

	public ILabel getLastName() {
		return this.controls.lastName;
	}

	public ILabel getRole() {
		return this.controls.role;
	}

	public ILink getLogout() {
		return this.controls.logout;
	}

	// setters

	public void logout() {
		this.controls.logout.click();
	}

}
