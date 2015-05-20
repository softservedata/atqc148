package com.softserve.edu.page.order;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderNavigation {
	private WebDriver driver;

	private OrderNavigation(WebDriver driver) {
		this.driver = driver;
	}

	public static OrderNavigation setDriver(WebDriver driver) {
		return new OrderNavigation(driver);
	}

	public void nextPage() {
		driver.get("http://localhost:8080/OMS/orderNextPage.htm");
		if (driver.getPageSource().contains("HTTP Status 404")) {
			driver.navigate().back();
			driver.findElement(By.name("nextPage")).click();
		}
	}

	public void prevPage() {
		driver.get("http://localhost:8080/OMS/orderPreviousPage.htm");
		if (driver.getPageSource().contains("HTTP Status 404")) {
			driver.navigate().back();
			driver.findElement(By.name("previoustPage")).click();
		}
	}

	public void firstPage() {
		driver.get("http://localhost:8080/OMS/orderFirstPage.htm");
		if (driver.getPageSource().contains("HTTP Status 404")) {
			driver.navigate().back();
			driver.findElement(By.name("firstPage")).click();
		}
	}

	public void lastPage() {
		driver.get("http://localhost:8080/OMS/orderLastPage.htm");
		if (driver.getPageSource().contains("HTTP Status 404")) {
			driver.navigate().back();
			driver.findElement(By.name("lastPage")).click();
		}
	}

	public boolean isNextBtnDisabled() {
		return !driver.findElement(By.name("nextPage")).isEnabled();
	}

	public boolean isPrevBtnDisabled() {
		return !driver.findElement(By.name("previousPage")).isEnabled();
	}

	public boolean isFirstBtnDisabled() {
		return !driver.findElement(By.name("firstPage")).isEnabled();
	}

	public boolean isLastBtnDisabled() {
		return !driver.findElement(By.name("lastPage")).isEnabled();
	}
}
