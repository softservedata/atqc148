package com.softserve.edu.page.order;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.softserve.edu.dao.order.OrderFromUI;

public class OrderTable {

	WebDriver driver;

	public OrderTable(WebDriver driver) {
		this.driver = driver;
	}

	public List<OrderFromUI> getAllRowsFromTable() {
		new OrderPage(driver).getOrderPage();
		return null;
	}

}
