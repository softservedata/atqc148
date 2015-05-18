package com.softserve.edu.page.order;

import org.openqa.selenium.WebDriver;

public class OrderPage {
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		this.driver = driver;
	}

	public void getOrderPage() {
		driver.get("http://localhost:8080/OMS/order.htm");
	}

}
