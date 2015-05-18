package com.softserve.edu.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.softserve.edu.dao.order.Order;
import com.softserve.edu.db.DbProcessor;
import com.softserve.edu.page.login.LoginPage;

public class Tests {
	private WebDriver driver;
	DbProcessor dbProc;

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		dbProc = new DbProcessor();
		new LoginPage(driver).logIn("login1", "qwerty", "Customer");
	}

	@Test
	public void testTableData() throws Exception {

		List<Order> ordersFromDB = dbProc.getDataFromDB();
		System.out.println("Number of orders: " + ordersFromDB.size());
		List<OrderToCompare> compareOrd = new ArrayList<OrderToCompare>();
		for (Order order : ordersFromDB) {
			compareOrd.add(order.toOrderToCompare());
		}

		List<OrderToCompare> orderList = new ArrayList<OrderToCompare>();
		driver.get("http://localhost:8080/OMS/order.htm");
		// size/2 coz we got 2 rows per table
		for (int i = 0; i < ordersFromDB.size() / 2; i++) {
			WebElement table = driver.findElement(By.id("list"));
			List<List<String>> cellsStrList = new ArrayList<List<String>>();

			List<WebElement> allRows = table.findElements(By.tagName("tr"));

			for (WebElement row : allRows) {
				List<String> cellsStrTmp = new ArrayList<String>();
				List<WebElement> cells = row.findElements(By.tagName("td"));
				// can find element by xpath
				// List<WebElement> cells = row.findElements(By.xpath("./td"));
				for (WebElement cell : cells) {
					cellsStrTmp.add(cell.getText());
				}
				// first row will be empty coz doesnt have <td> so i added check
				// for
				// isEmpty() before adding it
				if (!cellsStrTmp.isEmpty())
					cellsStrList.add(cellsStrTmp);
			}

			for (List<String> cellsStr : cellsStrList) {
				orderList.add(new OrderToCompare(cellsStr.get(0), Double
						.parseDouble(cellsStr.get(1)), Integer
						.parseInt(cellsStr.get(2)), cellsStr.get(3), cellsStr
						.get(4), cellsStr.get(5), cellsStr.get(6)));
			}
			driver.get("http://localhost:8080/OMS/orderNextPage.htm");
			// of find by name
			// driver.findElement(By.name("nextPage")).click();
		}

		int count = 0;
		for (OrderToCompare orderFromUI : orderList) {
			for (OrderToCompare orderFromDB : compareOrd) {
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
