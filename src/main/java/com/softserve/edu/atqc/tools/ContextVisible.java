package com.softserve.edu.atqc.tools;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class ContextVisible {
	private final String ERROR_NOT_FOUND = "WebElement was not found %s";
	private final String ERROR_STILL_VISIBLE = "WebElement is Still Visible %s";
	private static volatile ContextVisible instance = null;

	private ContextVisible() {
	}

	public static ContextVisible get() {
		if (instance == null) {
			synchronized (ContextVisible.class) {
				if (instance == null) {
					instance = new ContextVisible();
				}
			}
		}
		return instance;
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a
	 * page and visible.
	 */
	WebElement getVisibleWebElement(ControlLocation controlLocation) {
//		WebElement webElement = new WebDriverWait(
//				WebDriverUtils.get().getWebDriver(),
//				WebDriverUtils.get().getImplicitlyWaitTimeout())
//			.until(ExpectedConditions
//				.visibilityOfElementLocated(controlLocation.getBy()));
//		if (webElement == null) {
//			// TODO Develop My Exception
//			throw new RuntimeException(String.format(ERROR_NOT_FOUND,
//					controlLocation.toString()));
//		}
//		return webElement;
		WebElement webElement = null;
		try {
			webElement = new WebDriverWait(
					WebDriverUtils.get().getWebDriver(),
					WebDriverUtils.get().getImplicitlyWaitTimeout())
				.until(ExpectedConditions
					.visibilityOfElementLocated(controlLocation.getBy()));
		} catch (Exception e) {
			throw new ScreenCapturingCustomException(String.format(ERROR_NOT_FOUND,
					controlLocation.getValue()));
		}
		if (webElement == null) {
			throw new ScreenCapturingCustomException(String.format(ERROR_NOT_FOUND,
					controlLocation.getValue()));
		}
		return webElement;
	}

	/**
	 * An expectation for checking that all elements present on the web page
	 * that match the locator are visible.
	 */
	List<WebElement> getVisibleWebElements(ControlLocation controlLocation) {
		List<WebElement> webElements = new WebDriverWait(
				WebDriverUtils.get().getWebDriver(),
				WebDriverUtils.get().getImplicitlyWaitTimeout())
			.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(controlLocation.getBy()));
		if (webElements.size() == 0) {
			// TODO Develop My Exception
			throw new RuntimeException(String.format(ERROR_NOT_FOUND,
					controlLocation.toString()));
		}
		return webElements;
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a
	 * page. This does not necessarily mean that the element is visible.
	 */
	WebElement getPresentWebElement(ControlLocation controlLocation) {
		WebElement webElement = new WebDriverWait(
				WebDriverUtils.get().getWebDriver(),
				WebDriverUtils.get().getImplicitlyWaitTimeout())
			.until(ExpectedConditions
				.presenceOfElementLocated(controlLocation.getBy()));
		if (webElement == null) {
			// TODO Develop My Exception
			throw new RuntimeException(String.format(ERROR_NOT_FOUND,
					controlLocation.toString()));
		}
		return webElement;
	}

	/**
	 * An expectation for checking that an element is either invisible or not
	 * present on the DOM.
	 */
	public boolean isInvisibleWebElement(ControlLocation controlLocation) {
		Boolean invisibilityWebElement = new WebDriverWait(
				WebDriverUtils.get().getWebDriver(),
				WebDriverUtils.get().getImplicitlyWaitTimeout())
			.until(ExpectedConditions
				.invisibilityOfElementLocated(controlLocation.getBy()));
		if (!invisibilityWebElement) {
			// TODO Develop My Exception
			throw new RuntimeException(String.format(ERROR_STILL_VISIBLE,
					controlLocation.toString()));
		}
		return invisibilityWebElement;
	}

	/**
	 * Wait until an element is no longer attached to the DOM.
	 * Do not mix implicit and explicit waits.
	 */
	public boolean isStalenessOfWebElement(ControlWrapper controlWrapper) {
		WebDriverUtils.get().setImplicitlyWaitTimeout(0L);
		Boolean stalenessOfWebElement = new WebDriverWait(
				WebDriverUtils.get().getWebDriver(),
				WebDriverUtils.get().getImplicitlyWaitTimeout())
			.until(ExpectedConditions.stalenessOf(controlWrapper.getWebElement()));
		if (!stalenessOfWebElement) {
			// TODO Develop My Exception
			throw new RuntimeException(String.format(ERROR_STILL_VISIBLE,
					controlWrapper.getWebElement().getTagName()));
		}
		WebDriverUtils.get().setImplicitlyWaitTimeout(WebDriverUtils.get().getImplicitlyWaitTimeout());
		return stalenessOfWebElement;
	}

	Select getVisibleSelectWebElement(ControlLocation controlLocation) {
		return new Select(getVisibleWebElement(controlLocation));
	}

	Select getVisibleSelectWebElement(ControlWrapper controlWrapper) {
		return new Select(controlWrapper.getWebElement());
	}

	Select getPresentSelectWebElement(ControlLocation controlLocation) {
		return new Select(getPresentWebElement(controlLocation));
	}

	Select getPresentSelectWebElement(ControlWrapper controlWrapper) {
		return new Select(controlWrapper.getWebElement());
	}

	public boolean isVisibleTitle(String partialTitle) {
		return new WebDriverWait(
				WebDriverUtils.get().getWebDriver(),
				WebDriverUtils.get().getImplicitlyWaitTimeout())
				.until(ExpectedConditions.titleContains(partialTitle));
	}

}
