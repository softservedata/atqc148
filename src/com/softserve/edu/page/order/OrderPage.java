package com.softserve.edu.page.order;

import org.openqa.selenium.WebDriver;

public abstract class OrderPage {

	public static void getOrderPage(WebDriver driver) {
		driver.get("http://localhost:8080/OMS/order.htm");
	}

}
