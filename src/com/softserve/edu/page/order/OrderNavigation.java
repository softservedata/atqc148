package com.softserve.edu.page.order;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderNavigation {
	WebDriver driver;

	public OrderNavigation(WebDriver driver) {
		this.driver = driver;
	}

	public void toNextPage() {
		driver.get("http://localhost:8080/OMS/orderNextPage.htm");
		if (driver.getPageSource().contains("404")) {
			driver.navigate().back();
			driver.findElement(By.name("Next")).click();
		}
	}

	public void toPrevPage() {
		driver.get("http://localhost:8080/OMS/orderPreviousPage.htm");
		if (driver.getPageSource().contains("404")) {
			driver.navigate().back();
			driver.findElement(By.name("Previous")).click();
		}
	}

	public void toFirstPage() {
		driver.get("http://localhost:8080/OMS/orderFirstPage.htm");
		if (driver.getPageSource().contains("404")) {
			driver.navigate().back();
			driver.findElement(By.name("First")).click();
		}
	}

	public void toLastPage() {
		driver.get("http://localhost:8080/OMS/orderLastPage.htm");
		if (driver.getPageSource().contains("404")) {
			driver.navigate().back();
			driver.findElement(By.name("Last")).click();
		}
	}
}
