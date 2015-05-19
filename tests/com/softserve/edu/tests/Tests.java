package com.softserve.edu.tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.j256.ormlite.support.ConnectionSource;
import com.softserve.edu.dao.order.Order;
import com.softserve.edu.dao.order.OrderFromUI;
import com.softserve.edu.db.DbConnector;
import com.softserve.edu.db.DbProcessor;
import com.softserve.edu.page.login.LoginPage;
import com.softserve.edu.page.order.OrderTable;

public class Tests {
	private WebDriver driver;
	private DbConnector connector;
	private ConnectionSource connection;

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		connector = new DbConnector();
		connection = connector.getConnection();
		new LoginPage(driver).logIn("login1", "qwerty", "Customer");
	}

	@Test
	public void testTableData() throws Exception {
		List<Order> ordersFromDB = new DbProcessor(connection).getDataFromDB();
		List<OrderFromUI> compareOrd = Order.toOrdersFromUI(ordersFromDB);
		List<OrderFromUI> orderList = new OrderTable(driver)
				.getAllOrdersFromTable();
		int count = 0;
		for (OrderFromUI orderFromUI : orderList) {
			for (OrderFromUI orderFromDB : compareOrd) {
				if (orderFromDB.equals(orderFromUI)) {
					count++;
				}
			}
		}
		System.out.println("Number of equal orders: " + count);
		assertEquals(ordersFromDB.size(), count);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
