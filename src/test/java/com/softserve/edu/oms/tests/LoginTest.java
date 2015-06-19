package com.softserve.edu.oms.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.atqc.tools.BrowserRepository;
import com.softserve.edu.atqc.tools.IBrowser;
import com.softserve.edu.atqc.tools.WebDriverUtils;
import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.UrlRepository.Urls;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.pages.AdminHomePage;
import com.softserve.edu.oms.pages.AdministrationPage;
import com.softserve.edu.oms.pages.AdministrationPage.Conditions;
import com.softserve.edu.oms.pages.AdministrationPage.Fields;
import com.softserve.edu.oms.pages.CustomerHomePage;
import com.softserve.edu.oms.pages.LoginPage;
import com.softserve.edu.oms.pages.StartLoginPage;
import com.softserve.edu.oms.pages.ValidatorLoginPage;
import com.softserve.edu.oms.pages.ValidatorLoginPage.Validators;

public class LoginTest {

	@DataProvider
	public Object[][] invalidProvider() {
		return new Object[][] { {
				BrowserRepository.getFirefoxByTemporaryProfile(),
				Urls.SSU_HOST.toString(),
				UserRepository.getInvalidUser() },
		// { BrowserRepository.getChromeByTemporaryProfile() }
		};
	}

	@Test(dataProvider = "invalidProvider")
	public void checkInvalid(IBrowser browser, String url, IUser invalidUser) {
		// Preconditions
		// Steps
		/*
		LoginPage loginPage = StartLoginPage.load(browser, url);
		ValidatorLoginPage validatorLoginPage = loginPage
				.unSuccesfulLogin(invalidUser);
		// Check
		Assert.assertEquals(
				Validators.UNSUCCESS_VALIDATOR.toString(),
				validatorLoginPage.getUnSuccessValidator().getText()
						.substring(0, 49));
		// Return to previous state
		 */
	}

	@DataProvider
	public Object[][] adminProvider() {
		return new Object[][] { {
				BrowserRepository.getFirefoxByTemporaryProfile(),
				Urls.SSU_HOST.toString(),
				UserRepository.getAdminUser() },
		// { BrowserRepository.getChromeByTemporaryProfile() }
		};
	}

	//@Test(dataProvider = "adminProvider")
	public void checkAdmin(IBrowser browser, String url, IUser adminUser) {
		// Preconditions
		// Steps
		/*
		AdminHomePage adminHomePage = StartLoginPage.load(browser, url)
				.successAdminLogin(adminUser);
		// Check
		Assert.assertEquals(adminUser.getFirstName(),
				adminHomePage.getFirstName().getText());
		Assert.assertEquals(adminUser.getLastName(),
				adminHomePage.getLastName().getText());
		Assert.assertEquals(adminUser.getRole(),
				adminHomePage.getRole().getText());
		// Return to previous state
		adminHomePage.logout();
		*/
	}

	@DataProvider
	public Object[][] customerProvider() {
		return new Object[][] { {
				BrowserRepository.getFirefoxByTemporaryProfile(),
				Urls.SSU_HOST.toString(),
				UserRepository.getCustomerUser() },
		// { BrowserRepository.getChromeByTemporaryProfile() }
		};
	}

	//@Test(dataProvider = "customerProvider")
	public void checkCustomer(IBrowser browser, String url, IUser customerUser) {
		// Preconditions
		// Steps
		/*
		CustomerHomePage customerHomePage = StartLoginPage.load(browser, url)
				.successCustomerLogin(customerUser);
		// Check
		Assert.assertEquals(customerUser.getFirstName(),
				customerHomePage.getFirstName().getText());
		Assert.assertEquals(customerUser.getLastName(),
				customerHomePage.getLastName().getText());
		Assert.assertEquals(customerUser.getRole(),
				customerHomePage.getRole().getText());
		// Return to previous state
		customerHomePage.logout();
		*/
	}

	@DataProvider
	public Object[][] searchProvider() {
		return new Object[][] { {
				BrowserRepository.getFirefoxByTemporaryProfile(),
				Urls.SSU_HOST.toString(),
				UserRepository.getSearchUser() },
		// { BrowserRepository.getChromeByTemporaryProfile() }
		};
	}

	@Test(dataProvider = "searchProvider")
	public void checkSearchByLogin(IBrowser browser, String url, IUser searchUser) {
		// Preconditions
		/*
		AdministrationPage administrationPage = StartLoginPage
				.load(browser, url)
				.successAdminLogin(UserRepository.getAdminUser())
				.administrationClick();
		// Steps
		administrationPage.selectColumnFilter(Fields.LOGIN_NAME);
		administrationPage.selectMatchFilter(Conditions.STARTS_WITH);
		// administrationPage.typeSearchField(UserRepository.getAdminUser().getFirstName());
		administrationPage.typeSearchField(searchUser.getLoginName());
		// administrationPage.clickSearchButton();
		// Check
		Assert.assertEquals(searchUser.getFirstName(),
				administrationPage.getFirstName().getText());
		// Return to previous state
		administrationPage.logout();
		*/
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		WebDriverUtils.get().stop();
	}

}
