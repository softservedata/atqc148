package com.softserve.edu.page.order;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.softserve.edu.helpers.Report;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class OrderFilter {

	private WebDriver driver;
	protected Select fieldSelect;
	protected Select criterionSelect;

	private OrderFilter(WebDriver driver) {
		this.driver = driver;
		fieldSelect = new Select(driver.findElement(By.id("filterBy")));
		criterionSelect = new Select(driver.findElement(By.id("filterValue")));
	}

	public void refreshFilterSelectors() {
		fieldSelect = new Select(driver.findElement(By.id("filterBy")));
		criterionSelect = new Select(driver.findElement(By.id("filterValue")));
	}

	public static OrderFilter setDriver(WebDriver driver) {
		return new OrderFilter(driver);
	}

	public List<WebElement> getFiltersBy() {
		Select filterSelect = new Select(driver.findElement(By.id("filterBy")));
		return filterSelect.getOptions();
	}

	public List<WebElement> getFilterValues() {
		refreshFilterSelectors();
		Select filterSelect = new Select(driver.findElement(By
				.id("filterValue")));
		return filterSelect.getOptions();
	}

	public void orderBy(String field, String criterion) {
		refreshFilterSelectors();
		fieldSelect.selectByVisibleText(field);// "orderStatus"
		if (criterion != null)
			criterionSelect.selectByVisibleText(criterion);

	}

	public List<String> readValuesForField(String field) {
		List<String> result = new ArrayList<String>();
		fieldSelect.selectByVisibleText(field);

		// need to apply coz theres bug with role
		apply();
		refreshFilterSelectors();

		Iterator<WebElement> iter = criterionSelect.getOptions().iterator();
		while (iter.hasNext()) {
			result.add(iter.next().getText());
		}

		return result;
	}

	public void setFilterByStatus() {
		getFiltersBy().get(0).click();
	}

	public void setFilterByRole() {
		getFiltersBy().get(1).click();
	}

	public void apply() {
		driver.findElement(By.name("Apply")).click();
		// or
		// driver.findElement(By.name("Apply")).submit();
	}
}
