package com.softserve.edu.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.j256.ormlite.support.ConnectionSource;
import com.softserve.edu.dao.order.Order;
import com.softserve.edu.dao.order.OrderFromUI;
import com.softserve.edu.db.DbConnector;
import com.softserve.edu.db.DbProcessor;
import com.softserve.edu.page.login.LoginPage;
import com.softserve.edu.page.login.User;
import com.softserve.edu.page.order.OrderNavigation;
import com.softserve.edu.page.order.OrderPage;
import com.softserve.edu.page.order.OrderTable;

public class TestNG {
	private WebDriver driver;

	@BeforeSuite
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@BeforeTest
	public void logIn() {
		LoginPage.setDriver(driver).logIn(User.CUSTOMER);
	}

	@Test
	public void checkIfFirstButtonIsDisabled() {

		OrderPage.getOrderPage(driver);
		assertEquals(true, OrderNavigation.setDriver(driver)
				.isFirstBtnDisabled());
	}

	@Test
	public void checkIfPreviousButtonIsDisabled() {
		OrderPage.getOrderPage(driver);
		assertEquals(true, OrderNavigation.setDriver(driver)
				.isPrevBtnDisabled());
	}

	@Test
	public void checkIfNextButtonIsDisabled() {
		OrderPage.getOrderPage(driver);
		OrderNavigation nav = OrderNavigation.setDriver(driver);
		nav.lastPage();
		assertEquals(true, nav.isNextBtnDisabled());
	}

	@Test
	public void checkIfLastButtonIsDisabled() {
		OrderPage.getOrderPage(driver);
		OrderNavigation nav = OrderNavigation.setDriver(driver);
		nav.lastPage();
		assertEquals(true, nav.isLastBtnDisabled());
	}

	@Test
	public void getOrderTest() {
		OrderTable.setDriver(driver).getOrderByNumber(8);
	}

	@Test(enabled = false)
	// test runs slow
	public void testTableData() throws Exception {
		ConnectionSource connection = DbConnector.getConnection();
		List<OrderFromUI> orderList = OrderTable.setDriver(driver)
				.getAllOrdersFromTable();
		List<Order> ordersFromDB = DbProcessor.setConnection(connection)
				.getDataFromDB();
		List<OrderFromUI> compareOrd = Order.toOrdersFromUI(ordersFromDB);
		int equalOrdersNumber = 0;
		for (OrderFromUI orderFromUI : orderList) {
			for (OrderFromUI orderFromDB : compareOrd) {
				if (orderFromDB.equals(orderFromUI)) {
					equalOrdersNumber++;
				}
			}
		}
		System.out.println("Number of equal orders: " + equalOrdersNumber);
		assertEquals(ordersFromDB.size(), equalOrdersNumber);

	}

	@AfterTest
	public void logOut() {
		LoginPage.setDriver(driver).logOut();
	}

	@AfterSuite
	public void tearDown() throws Exception {
		driver.quit();
	}
}
