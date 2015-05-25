package com.softserve.edu.page.order;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class OrderPage {

	public static void navigateToOrderPage(WebDriver driver) {
		//driver.get("http://localhost:8080/OMS/order.htm");
		driver.findElement(By.linkText("Ordering")).click();
	}

}
