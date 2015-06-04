package com.softserve.edu.page.order;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.softserve.edu.helpers.ContextVisible;
import com.softserve.edu.helpers.webdriver.WebDriverUtils;
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
		getFilterSelectors();
	}

	/**
	 * Finds filter select elements.
	 */
	public void getFilterSelectors() {
		fieldSelect = new Select(ContextVisible.get().getVisibleWebElement(By.id("filterBy")));
		criterionSelect = new Select(ContextVisible.get().getVisibleWebElement(By.id("filterValue")));
	}

	public static OrderFilter setDriver() {
		return new OrderFilter(WebDriverUtils.get().getWebDriver());
	}

	/**
	 * Finds select elements.
	 * @return list of select web elements for filters.
	 */
	public List<WebElement> getFiltersBy() {
		getFilterSelectors();
		return fieldSelect.getOptions();
	}

	/**
	 * Finds select valuse elements.
	 * @return list of select web elements for filter values.
	 */
	public List<WebElement> getFilterValues() {
		getFilterSelectors();
		return criterionSelect.getOptions();
	}

	/**
	 * Select filter.
	 * @param field filter select (Status or Role)
	 * @param criterion filter Value (None, Pendidng...)
	 */
	public void orderBy(String field, String criterion) {
		getFilterSelectors();
		fieldSelect.selectByVisibleText(field);// "orderStatus"
		if (criterion != null)
			criterionSelect.selectByVisibleText(criterion);
	}

	/**
	 * Get values for specified filter.
	 * @param field filter field.
	 * @return list of String filter values.
	 */
	public List<String> readValuesForField(String field) {
		List<String> result = new ArrayList<String>();
		fieldSelect.selectByVisibleText(field);

		// need to apply coz theres bug with role
		apply();
		getFilterSelectors();

		Iterator<WebElement> iter = criterionSelect.getOptions().iterator();
		while (iter.hasNext()) {
			result.add(iter.next().getText());
		}

		return result;
	}

	/**
	 * Select filter by status.
	 */
	public void setFilterByStatus() {
		List<WebElement> filters = getFiltersBy();
		for (WebElement filter:filters){
			if(filter.getText().equals("Status")){
				filter.click();
			}
		}
	}

	/**
	 * Select filter by role.
	 */
	public void setFilterByRole() {
				List<WebElement> filters = getFiltersBy();
		for (WebElement filter:filters){
			if(filter.getText().equals("Role")){
				filter.click();
			}
		}
	}

	/**
	 * Click on apply button.
	 */
	public void apply() {
		ContextVisible.get().getVisibleWebElement(By.name("Apply")).click();
	}
}
