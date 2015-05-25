package com.softserve.edu.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import com.softserve.edu.helpers.DbHelper;
import com.softserve.edu.helpers.FilterValues;
import com.softserve.edu.helpers.OrderStatuses;
import com.softserve.edu.helpers.UserRoles;
import com.softserve.edu.page.login.LoginPage;
import com.softserve.edu.page.login.User;
import com.softserve.edu.page.order.OrderFilter;
import com.softserve.edu.page.order.OrderNavigation;
import com.softserve.edu.page.order.OrderPage;
import com.softserve.edu.page.order.OrderTable;

public class OrdersPageTest {
	private WebDriver driver;

	@BeforeSuite
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@BeforeTest(groups = { "filter", "database" })
	public void logIn() {
		LoginPage.setDriver(driver).logIn(User.CUSTOMER);
		OrderPage.navigateToOrderPage(driver);
	}

	// @BeforeTest(groups = { "navigation" })
	// public void logIn() {
	// LoginPage.setDriver(driver).logIn(User.CUSTOMER);
	// OrderPage.navigateToOrderPage(driver);
	// }

//	@Test(groups = { "filter" })
	public void filterByStatusTest() throws Exception {
		OrderTable orderTable = OrderTable.setDriver(driver);
		OrderFilter filter = OrderFilter.setDriver(driver);
		List<String> filterValues = filter
				.readValuesForField(FilterValues.Status.getName());
		OrderStatuses statusValue;

		Iterator<String> iter = filterValues.iterator();
		iter.next();
		while (iter.hasNext()) {
			String crit = iter.next();
			statusValue = OrderStatuses.valueOf(crit);
			filter.orderBy(FilterValues.Status.getName(), statusValue.getName());
			filter.apply();
			List<OrderFromUI> ordersDB = DbHelper.readOrdersByField(
					FilterValues.Status, statusValue);
			List<OrderFromUI> ordersUI = orderTable.getAllOrdersFromTable();

			// method compares sizes, then compares each by each
			assertTrue(orderTable.isListEqual(ordersUI, ordersDB));
		}
	}
	
	
	@Test(groups = { "filter" })
	public void filterByRoleTest() throws Exception {
		OrderTable orderTable = OrderTable.setDriver(driver);
		OrderFilter filter = OrderFilter.setDriver(driver);
		List<String> filterValues = filter
				.readValuesForField(FilterValues.Role.getName());
		UserRoles roleValue;

		Iterator<String> iter = filterValues.iterator();
		iter.next();
		while (iter.hasNext()) {
			String crit = iter.next();
			roleValue = UserRoles.valueOf(crit);
			filter.orderBy(FilterValues.Role.getName(), roleValue.getName());
			filter.apply();
			List<OrderFromUI> ordersDB = DbHelper.readOrdersByField(
					FilterValues.Role, roleValue);
			List<OrderFromUI> ordersUI = orderTable.getAllOrdersFromTable();

			// method compares sizes, then compares each by each
			assertTrue(orderTable.isListEqual(ordersUI, ordersDB));
		}
	}
	
	

	// @Test(priority = 2, groups = { "navigation" })
	public void checkIfFirstButtonIsDisabled() {
		assertTrue(OrderNavigation.setDriver(driver).isFirstBtnDisabled());
	}

	// @Test(priority = 2, groups = { "navigation" })
	public void checkIfPreviousButtonIsDisabled() {
		assertTrue(OrderNavigation.setDriver(driver).isPrevBtnDisabled());
	}

	// @Test(priority = 2, groups = { "navigation" })
	public void checkIfNextButtonIsDisabled() {
		OrderNavigation navigation = OrderNavigation.setDriver(driver);
		navigation.navigateToLastPage();
		navigation.refreshReferences();
		assertTrue(navigation.isNextBtnDisabled());
	}

	// @Test(priority = 2, groups = { "navigation" })
	public void checkIfLastButtonIsDisabled() {
		OrderNavigation navigation = OrderNavigation.setDriver(driver);
		navigation.navigateToLastPage();
		navigation.refreshReferences();
		assertTrue(navigation.isLastBtnDisabled());
	}

	// @Test(priority = 2, groups = { "navigation" })
	public void checkFirstButton() {
		OrderTable orderTable = OrderTable.setDriver(driver);
		OrderNavigation navigation = OrderNavigation.setDriver(driver);
		navigation.navigateToNextPage();
		navigation.refreshReferences();
		navigation.navigateToFirstPage();
		navigation.refreshReferences();
		List<OrderFromUI> testData = new ArrayList<OrderFromUI>();
		testData.add(new OrderFromUI("", "31.0", "0", "", "Created", "login2",
				"Merchandiser"));
		testData.add(new OrderFromUI("", "9.0", "0", "2015-05-21 00:00:00.0",
				"Pending", "login2", "Merchandiser"));
		List<OrderFromUI> orders = orderTable.getOrdersFromTablePage(orderTable
				.getOrdersTable());
		if (testData.size() == orders.size()) {
			for (int i = 0; i < orders.size(); i++) {
				assertEquals(true, testData.get(i).equals(orders.get(i)));
			}
		} else {
			throw new AssertionError("List are not equal.");
		}
	}

	// @Test(priority = 2, groups = { "navigation" })
	public void checkNextButton() {
		OrderTable orderTable = OrderTable.setDriver(driver);
		OrderNavigation navigation = OrderNavigation.setDriver(driver);
		navigation.navigateToFirstPage();
		navigation.refreshReferences();
		navigation.navigateToNextPage();
		navigation.refreshReferences();
		List<OrderFromUI> testData = new ArrayList<OrderFromUI>();
		testData.add(new OrderFromUI("", "47.0", "0", "", "Ordered", "login2",
				"Merchandiser"));
		testData.add(new OrderFromUI("", "220.0", "0", "", "Ordered", "login2",
				"Merchandiser"));
		List<OrderFromUI> orders = orderTable.getOrdersFromTablePage(orderTable
				.getOrdersTable());
		if (testData.size() == orders.size()) {
			for (int i = 0; i < orders.size(); i++) {
				assertEquals(true, testData.get(i).equals(orders.get(i)));
			}
		} else {
			throw new AssertionError("List are not equal.");
		}
	}

	// @Test(priority = 2, groups = { "navigation" })
	public void checkLastButton() {
		OrderTable orderTable = OrderTable.setDriver(driver);
		OrderNavigation navigation = OrderNavigation.setDriver(driver);
		navigation.navigateToLastPage();
		navigation.refreshReferences();
		List<OrderFromUI> testData = new ArrayList<OrderFromUI>();
		// OrderName2 120.0 12 2012-12-02 00:00:00.0 Ordered marko Administrator
		testData.add(new OrderFromUI("OrderName2", "120.0", "12",
				"2012-12-02 00:00:00.0", "Ordered", "marko", "Administrator"));
		// OrderName4 0.0 14 2012-12-02 00:00:00.0 Created vitalik Administrator
		testData.add(new OrderFromUI("OrderName4", "0.0", "14",
				"2012-12-02 00:00:00.0", "Created", "vitalik", "Administrator"));
		List<OrderFromUI> orders = orderTable.getOrdersFromTablePage(orderTable
				.getOrdersTable());
		if (testData.size() == orders.size()) {
			for (int i = 0; i < orders.size(); i++) {
				assertEquals(true, testData.get(i).equals(orders.get(i)));
			}
		} else {
			throw new AssertionError("List are not equal.");
		}
	}

	// @Test(priority = 2, groups = { "navigation" })
	public void checkPreviousButton() {
		OrderTable orderTable = OrderTable.setDriver(driver);
		OrderNavigation navigation = OrderNavigation.setDriver(driver);
		navigation.navigateToLastPage();
		navigation.refreshReferences();
		navigation.navigateToPrevPage();
		navigation.refreshReferences();
		List<OrderFromUI> testData = new ArrayList<OrderFromUI>();
		// OrderName5 0.0 15 2012-12-02 00:00:00.0 Delivered myroslav
		// Administrator
		testData.add(new OrderFromUI("OrderName5", "0.0", "15",
				"2012-12-02 00:00:00.0", "Delivered", "myroslav",
				"Administrator"));
		// OrderName3 0.0 13 2012-12-02 00:00:00.0 Pending romanS Administrator
		testData.add(new OrderFromUI("OrderName3", "0.0", "13",
				"2012-12-02 00:00:00.0", "Pending", "romanS", "Administrator"));
		List<OrderFromUI> orders = orderTable.getOrdersFromTablePage(orderTable
				.getOrdersTable());
		if (testData.size() == orders.size()) {
			for (int i = 0; i < orders.size(); i++) {
				assertTrue(testData.get(i).equals(orders.get(i)));
			}
		} else {
			throw new AssertionError("List are not equal.");
		}
	}

	// @Test(priority = 1, enabled = true, groups = { "database", "navigation"
	// })
	// test runs slow
	public void testTableData() throws Exception {
		OrderTable orderTable = OrderTable.setDriver(driver);
		ConnectionSource connection = DbConnector.getConnection();
		List<OrderFromUI> orderList = orderTable.getAllOrdersFromTable();
		List<Order> ordersFromDB = DbProcessor.setConnection(connection)
				.getDataFromDB();
		List<OrderFromUI> compareOrd = Order.toOrdersFromUI(ordersFromDB);
		// int equalOrdersNumber = 0;
		// for (OrderFromUI orderFromUI : orderList) {
		// for (OrderFromUI orderFromDB : compareOrd) {
		// if (orderFromDB.equals(orderFromUI)) {
		// equalOrdersNumber++;
		// }
		// }
		// }
		assertTrue(orderTable.isListEqual(orderList, compareOrd));

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
