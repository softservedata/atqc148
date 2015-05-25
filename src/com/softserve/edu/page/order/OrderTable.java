package com.softserve.edu.page.order;

import java.util.ArrayList;
import java.util.Iterator;
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

	public int getTableSize() {
		OrderPage.navigateToOrderPage(driver);
		WebElement ordersTable = getOrdersTable();
		List<OrderFromUI> ordersFromTable = getOrdersFromTablePage(ordersTable);
		int size = ordersFromTable.size();
		OrderNavigation.setDriver(driver).navigateToNextPage();
		ordersTable = getOrdersTable();
		while (!ordersFromTable.get(0).equals(
				getOrdersFromTablePage(ordersTable).get(0))) {

			if (!ordersFromTable.get(ordersFromTable.size() - 1).equals(
					getOrdersFromTablePage(ordersTable).get(0))) {
				size += 2;
				ordersFromTable = getOrdersFromTablePage(ordersTable);
				OrderNavigation.setDriver(driver).navigateToNextPage();
				ordersTable = getOrdersTable();
			} else {
				size += 1;
				ordersFromTable = getOrdersFromTablePage(ordersTable);
				OrderNavigation.setDriver(driver).navigateToNextPage();
				ordersTable = getOrdersTable();
			}
		}
		return size;
	}

	public List<OrderFromUI> getAllOrdersFromTable() {
//		OrderPage.navigateToOrderPage(driver);
		WebElement ordersTable = getOrdersTable();
		List<OrderFromUI> orders = new ArrayList<OrderFromUI>();

		// 1.get orders from table page
		if (orders.size() == 0) {
			List<OrderFromUI> ordersFromTable = getOrdersFromTablePage(ordersTable);
			if (ordersFromTable.size() == 0) {
				return orders;
			}
			for (OrderFromUI order : ordersFromTable) {
				orders.add(order);
			}
			OrderNavigation.setDriver(driver).navigateToNextPage();
			ordersTable = getOrdersTable();
		}
		// 2.compare
		// TODO refactor hard code here
		// TODO problem here. if orders count in ui isn't odd, on last 2 pages
		// there's order duplicate *(look comment below)
		while (!orders.get(
				orders.size() - getOrdersFromTablePage(ordersTable).size())
				.equals(getOrdersFromTablePage(ordersTable).get(0))) {
			List<OrderFromUI> ordersFromTable = getOrdersFromTablePage(ordersTable);
			Iterator<OrderFromUI> iter = ordersFromTable.iterator();
			while (iter.hasNext()) {
				OrderFromUI order = iter.next();
				// added check for duplicates
				if (!orders.get(orders.size() - 1).equals(order)) {
					orders.add(order);
				}
			}
			OrderNavigation.setDriver(driver).navigateToNextPage();
			ordersTable = getOrdersTable();
		}

		return orders;
	}

	public WebElement getOrdersTable() {
		return driver.findElement(By.id("list"));
	}

	public List<OrderFromUI> getOrdersFromTablePage(WebElement table) {
		List<OrderFromUI> orderList = new ArrayList<OrderFromUI>();
		List<WebElement> rowWebElements = table.findElements(By.tagName("tr"));
		List<List<String>> rows = new ArrayList<List<String>>();
		for (WebElement row : rowWebElements) {
			List<String> rowCells = getDataFromRow(row);
			if (!rowCells.isEmpty())
				rows.add(rowCells);
		}
		for (List<String> cellsStr : rows) {
			orderList.add(new OrderFromUI(cellsStr.get(0), cellsStr.get(1),
					cellsStr.get(2), cellsStr.get(3), cellsStr.get(4), cellsStr
							.get(5), cellsStr.get(6)));
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
		OrderPage.navigateToOrderPage(driver);
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
				OrderNavigation.setDriver(driver).navigateToNextPage();
				table = getOrdersTable();
				pageNum++;
				rowsSize *= pageNum;
			}
		}
		List<String> rowStr = getDataFromRow(row);
		OrderFromUI ord = new OrderFromUI(rowStr.get(0), rowStr.get(1),
				rowStr.get(2), rowStr.get(3), rowStr.get(4), rowStr.get(5),
				rowStr.get(6));
		ord.print();
		return ord;
	}

	public boolean isListEqual(List<OrderFromUI> fromDB,
			List<OrderFromUI> fromUI) {

		// 1. compare sizes
		if (fromDB.size() != fromUI.size()) {
			return false;
			
//			2. if they are empty then they are equal
		} else if (fromDB.size() == 0 && fromUI.size() == 0) {
			return true;
		}
		// 2.if sizes are equal, compare each by each
		int equalOrdersNumber = 0;
		for (OrderFromUI orderFromUI : fromUI) {
			for (OrderFromUI orderFromDB : fromDB) {
				if (orderFromDB.equals(orderFromUI)) {
					equalOrdersNumber++;
				}
			}
		}
		System.out.println("Number of equal orders: " + equalOrdersNumber);
		// if all orders are equal, result will be true
		return (equalOrdersNumber == fromDB.size());
	}

}
