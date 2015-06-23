package com.softserve.edu.oms.pages;

import com.softserve.edu.atqc.controls.ILabel;
import com.softserve.edu.atqc.controls.ILink;
import com.softserve.edu.atqc.controls.ITextField;
import com.softserve.edu.atqc.controls.Label;
import com.softserve.edu.atqc.controls.Link;
import com.softserve.edu.atqc.controls.TextField;

public class AdministrationPage {
	public static enum AdministrationPageFields {
		ALL_COLUMNS("All Columns"),
		FIRST_NAME("First Name"),
		LAST_NAME("Last Name"),
		ROLE("Role"),
		LOGIN_NAME("Login Name");
		private String field;

		private AdministrationPageFields(String field) {
			this.field = field;
		}

		@Override
		public String toString() {
			return this.field;
		}
	}

	public static enum AdministrationPageConditions {
		EQUALS("equals"),
		NOT_EQUALS_TO("not equals to"),
		STARTS_WITH("starts with"),
		CONTAINS("contains"),
		DOES_NOT_CONTAIN("does not contain");
		private String field;

		private AdministrationPageConditions(String field) {
			this.field = field;
		}

		@Override
		public String toString() {
			return this.field;
		}
	}

	private class AdministrationPageUIMap {
		public final ILink logout;
		public final ILink createNewUser;
		public final ITextField searchField;

		// public final ISelect field;
		// public final ISelect condition;

		public AdministrationPageUIMap() {
			this.logout = Link.getByXpath("//a[@href='/OMS/logout.htm']");
			this.createNewUser = Link.getByPartialLinkText("Create New User");
			this.searchField = TextField.getById("searchField");
			// this.field = Select.getById("field");
			// this.condition = Select.getById("condition");
		}
	}

	private class AdministrationPageUIMapAjax {
		public final ILabel usersFound;
		public final ILabel firstName;
		public final ILabel lastName;
		public final ILabel login;
		public final ILink delete;

		public AdministrationPageUIMapAjax() {
			this.usersFound = Label.getById("usersFound");
			if (Integer.parseInt(usersFound.getText()) > 0) {
				this.firstName = Label.getByXpath("//tbody/tr[1]/td[1]");
				this.lastName = Label.getByXpath("//tbody/tr[1]/td[2]");
				this.login = Label.getByXpath("//tbody/tr[1]/td[3]");
				this.delete = Link.getByXpath("//tbody/tr[1]/td[7]/a");
			} else {
				this.firstName = Label.getByXpath("//thead/tr[1]/th[1]");
				this.lastName = Label.getByXpath("//thead/tr[1]/th[2]");
				this.login = Label.getByXpath("//thead/tr[1]/th[3]");
				this.delete = Link.getByXpath("//thead/tr[1]/th[1]");
			}
		}

		public AdministrationPageUIMapAjax(String login) {
			this.usersFound = Label.getById("usersFound");
			//
			this.login = Label.getByXpath("//tbody//td[text()='" + login + "']");
			this.lastName = Label.getByXpath("//tbody//td[text()='" + login + "']/preceding-sibling::td[1]");
			this.firstName = Label.getByXpath("//tbody//td[text()='" + login + "']/preceding-sibling::td[2]");
			//this.delete = Link.getByXpath(""//tbody//td[text()='" + login + "']   /a");
		}

	}
	/*
	private WebElement createNewUser;
	private Select field;
	private Select condition;
	private WebElement searchField;
	private WebElement logout;
	// Refresh
	private WebElement firstName;
	private WebElement lastName;
	private WebElement loginButton;
	//private WebElement searchButton;


	public AdministrationPage() {
		this.createNewUser = ContextVisible.get().getVisibleWebElement(By.partialLinkText("Create New User"));
		this.field = new Select(ContextVisible.get().getVisibleWebElement(By.id("field")));
		this.condition = new Select(ContextVisible.get().getVisibleWebElement(By.id("condition")));
		this.searchField = ContextVisible.get().getVisibleWebElement(By.id("searchField"));
		this.logout = ContextVisible.get().getVisibleWebElement(By.xpath("//a[@href='/OMS/logout.htm']"));
		//
		this.firstName = ContextVisible.get().getVisibleWebElement(By.xpath("//tbody/tr[1]/td[1]"));
		this.lastName = ContextVisible.get().getVisibleWebElement(By.xpath("//tbody/tr[1]/td[2]"));
		this.loginButton = ContextVisible.get().getVisibleWebElement(By.xpath("//tbody/tr[1]/td[3]"));
//		this.searchButton = ContextVisible.get().getVisibleWebElement(By.tagName("search"));
	}

	// - - - - - - - - - -

	public WebElement getCreateNewUser() {
		return createNewUser;
	}

	public Select getField() {
		return field;
	}

	public Select getCondition() {
		return condition;
	}

	public WebElement getSearchField() {
		return searchField;
	}

	public WebElement getFirstName() {
		return firstName;
	}

	public WebElement getLastName() {
		return lastName;
	}

	public WebElement getLogin() {
		return loginButton;
	}
	
//	public WebElement getSearchButton() {
//		return searchButton;
//	}

	// - - - - - - - - - -

	public CreateNewUserPage gotoCreateNewUser(IUser newUser) {
		createNewUser.click();
		return new CreateNewUserPage();
	}

	public LoginPage logout() {
		this.logout.click();
		return new LoginPage();
	}

	// - - - - - - - - - -

	public void selectColumnFilter(Fields setField) {
		field.selectByVisibleText(setField.toString());
	}

	public void selectMatchFilter(Conditions setCondition) {
		condition.selectByVisibleText(setCondition.toString());
	}

	private void resetTable() {
		this.firstName = ContextVisible.get().getVisibleWebElement(By.xpath("//tbody/tr[1]/td[1]"));
		System.out.println("firstName = " + firstName.getText());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.firstName = ContextVisible.get().getVisibleWebElement(By.xpath("//tbody/tr[1]/td[1]"));
		this.lastName = ContextVisible.get().getVisibleWebElement(By.xpath("//tbody/tr[1]/td[2]"));
		this.loginButton =ContextVisible.get().getVisibleWebElement(By.xpath("//tbody/tr[1]/td[3]"));
		System.out.println("new firstName = " + firstName.getText());
	}

	public void typeSearchField(String text) {
		searchField.click();
		searchField.clear();
		searchField.sendKeys(text);
		//
		resetTable();
	}
	
//	public void clickSearchButton()
//	{
//		searchButton.click();		
//	}
*/
}
