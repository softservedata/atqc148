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
import com.softserve.edu.oms.logics.AdminHomePageLogic;
import com.softserve.edu.oms.logics.AdministrationPageLogic;
import com.softserve.edu.oms.logics.CustomerHomePageLogic;
import com.softserve.edu.oms.logics.LoginPageLogic;
import com.softserve.edu.oms.logics.ValidatorLoginPageLogic;
import com.softserve.edu.oms.pages.AdministrationPage.AdministrationPageConditions;
import com.softserve.edu.oms.pages.AdministrationPage.AdministrationPageFields;
import com.softserve.edu.oms.pages.ValidatorLoginPage.LoginPageValidators;
import com.softserve.edu.oms.spans.StartApplication;

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

	//@Test(dataProvider = "invalidProvider")
	public void checkInvalid(IBrowser browser, String url, IUser invalidUser) {
		// Preconditions
		// Steps
		LoginPageLogic loginPageLogic = StartApplication.load(browser, url);
		ValidatorLoginPageLogic validatorLoginPageLogic = loginPageLogic
				.unSuccesfulLogin(invalidUser);
		// Check
		Assert.assertEquals(
				LoginPageValidators.UNSUCCESS_VALIDATOR.toString(),
				validatorLoginPageLogic.getUnSuccessValidator()
						.substring(0, 49));
		// Return to previous state
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
		AdminHomePageLogic adminHomePageLogic = StartApplication.load(browser, url)
				.successAdminLogin(adminUser);
		// Check
		Assert.assertEquals(adminUser.getFirstName(),
				adminHomePageLogic.getFirstName());
		Assert.assertEquals(adminUser.getLastName(),
				adminHomePageLogic.getLastName());
		Assert.assertEquals(adminUser.getRole(), adminHomePageLogic.getRole());
		// Return to previous state
		adminHomePageLogic.logout();
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
		CustomerHomePageLogic customerHomePageLogic = StartApplication.load(browser, url)
				.successCustomerLogin(customerUser);
		// Check
		Assert.assertEquals(customerUser.getFirstName(),
				customerHomePageLogic.getFirstName());
		Assert.assertEquals(customerUser.getLastName(),
				customerHomePageLogic.getLastName());
		Assert.assertEquals(customerUser.getRole(),
				customerHomePageLogic.getRole());
		// Return to previous state
		customerHomePageLogic.logout();
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
		  AdministrationPageLogic administrationPageLogic = StartApplication.load(browser, url)
				  .successAdminLogin(UserRepository.getAdminUser())
				  .gotoAdministration();
		  // Steps
		  administrationPageLogic.searchByLoginName(AdministrationPageFields.LOGIN_NAME,
				  AdministrationPageConditions.STARTS_WITH, searchUser);
		  // Check
		  Assert.assertEquals(searchUser.getFirstName(),
				  administrationPageLogic.getAdministrationPage().getFirstName().getText());
		  // Return to previous state
		  administrationPageLogic.logout();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		WebDriverUtils.get().stop();
	}

}
