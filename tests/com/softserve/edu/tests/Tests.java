package com.softserve.edu.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.j256.ormlite.support.ConnectionSource;
import com.softserve.edu.dao.order.Order;
import com.softserve.edu.dao.order.OrderFromUI;
import com.softserve.edu.db.DbConnector;
import com.softserve.edu.db.DbProcessor;
import com.softserve.edu.page.login.LoginPage;
import com.softserve.edu.page.order.OrderNavigation;
import com.softserve.edu.page.order.OrderPage;
import com.softserve.edu.page.order.OrderTable;

public class Tests {
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		new LoginPage(driver).logIn("login1", "qwerty", "Customer");
	}

	// @Test
	public void checkIfFirstButtonIsDisabled() {
		new OrderPage(driver).getOrderPage();
		assertEquals(true, new OrderNavigation(driver).isFirstBtnDisabled());
	}

	// @Test
	public void checkIfPreviousButtonIsDisabled() {
		new OrderPage(driver).getOrderPage();
		assertEquals(true, new OrderNavigation(driver).isPrevBtnDisabled());
	}

	// @Test
	public void checkIfNextButtonIsDisabled() {
		new OrderPage(driver).getOrderPage();
		OrderNavigation nav = new OrderNavigation(driver);
		nav.lastPage();
		assertEquals(true, nav.isNextBtnDisabled());
	}

	// @Test
	public void checkIfLastButtonIsDisabled() {
		new OrderPage(driver).getOrderPage();
		OrderNavigation nav = new OrderNavigation(driver);
		nav.lastPage();
		assertEquals(true, nav.isLastBtnDisabled());
	}

	// @Test
	public void getOrderTest() {
		new OrderTable(driver).getOrderByNumber(5);
	}

	// @Test
	public void testTableData() throws Exception {
		ConnectionSource connection = new DbConnector().getConnection();
		List<OrderFromUI> orderList = new OrderTable(driver)
				.getAllOrdersFromTable();
		List<Order> ordersFromDB = new DbProcessor(connection).getDataFromDB();
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

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
