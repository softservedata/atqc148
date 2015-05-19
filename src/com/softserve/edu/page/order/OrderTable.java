package com.softserve.edu.page.order;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.dao.order.OrderFromUI;

public class OrderTable {

	WebDriver driver;

	public OrderTable(WebDriver driver) {
		this.driver = driver;
	}

	public List<OrderFromUI> getAllOrdersFromTable() {
		new OrderPage(driver).getOrderPage();
		List<OrderFromUI> orders = new ArrayList<OrderFromUI>();

		// 1.get orders from table page
		if (orders.size() == 0) {
			List<OrderFromUI> ordersFromTable = getOrdersFromTablePage(getOrdersTable());
			for (OrderFromUI order : ordersFromTable) {
				orders.add(order);
			}
			new OrderNavigation(driver).nextPage();
		}
		// 2.compare
		// TODO refactor hard code here
		while (!orders
				.get(orders.size()
						- getOrdersFromTablePage(getOrdersTable()).size())
				.equals(getOrdersFromTablePage(getOrdersTable()).get(0))) {
			List<OrderFromUI> ordersFromTable = getOrdersFromTablePage(getOrdersTable());
			for (OrderFromUI ord : ordersFromTable) {
				orders.add(ord);
			}
			new OrderNavigation(driver).nextPage();
		}
		return orders;
	}

	public WebElement getOrdersTable() {
		return driver.findElement(By.id("list"));
	}

	public List<OrderFromUI> getOrdersFromTablePage(WebElement table) {
		List<OrderFromUI> orderList = new ArrayList<OrderFromUI>();
		List<WebElement> rowWebElements = new ArrayList<WebElement>();
		rowWebElements = table.findElements(By.tagName("tr"));
		List<List<String>> rows = new ArrayList<List<String>>();
		for (WebElement row : rowWebElements) {
			List<String> rowCells = getDataFromRow(row);
			if (!rowCells.isEmpty())
				rows.add(rowCells);
		}
		for (List<String> cellsStr : rows) {
			orderList.add(new OrderFromUI(cellsStr.get(0), Double
					.parseDouble(cellsStr.get(1)), Integer.parseInt(cellsStr
					.get(2)), cellsStr.get(3), cellsStr.get(4),
					cellsStr.get(5), cellsStr.get(6)));
		}
		return orderList;
	}

	public static List<String> getDataFromRow(WebElement row) {
		List<String> rowString = new ArrayList<String>();
		List<WebElement> cells = row.findElements(By.tagName("td"));
		for (WebElement cell : cells) {
			rowString.add(cell.getText());
		}
		return rowString;
	}

}
