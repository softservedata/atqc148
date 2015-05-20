package com.softserve.edu.page.order;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.dao.order.OrderFromUI;

public class OrderTable {

	private WebDriver driver;

	private OrderTable(WebDriver driver) {
		this.driver = driver;
	}

	public static OrderTable setDriver(WebDriver driver) {
		return new OrderTable(driver);
	}

	public List<OrderFromUI> getAllOrdersFromTable() {
		OrderPage.getOrderPage(driver);
		List<OrderFromUI> orders = new ArrayList<OrderFromUI>();

		// 1.get orders from table page
		if (orders.size() == 0) {
			List<OrderFromUI> ordersFromTable = getOrdersFromTablePage(getOrdersTable());
			for (OrderFromUI order : ordersFromTable) {
				orders.add(order);
			}
			OrderNavigation.setDriver(driver).nextPage();
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
			OrderNavigation.setDriver(driver).nextPage();
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

	public OrderFromUI getOrderByNumber(int num) {
		OrderPage.getOrderPage(driver);
		WebElement table = getOrdersTable();
		WebElement row = null;
		int pageNum = 1;
		int rowsSize = table.findElements(By.tagName("tr")).size() - 1;
		boolean elementFound = false;
		while (!elementFound) {
			List<WebElement> rows = table.findElements(By.tagName("tr"));
			if (rowsSize >= num) {
				row = rows.get(num / pageNum);
				elementFound = true;
			} else {
				OrderNavigation.setDriver(driver).nextPage();
				table = getOrdersTable();
				pageNum++;
				rowsSize *= pageNum;
			}
		}
		List<String> rowStr = getDataFromRow(row);
		OrderFromUI ord = new OrderFromUI(rowStr.get(0),
				Double.parseDouble(rowStr.get(1)), Integer.parseInt(rowStr
						.get(2)), rowStr.get(3), rowStr.get(4), rowStr.get(5),
				rowStr.get(6));
		ord.print();
		return ord;
	}

}
