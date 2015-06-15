package main.java.com.softserve.edu.atqc.helpers.webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

//Double Checked Locking & volatile singleton
public class WebDriverUtils {
	private static volatile WebDriverUtils instance = null;
//	implicit wait is 1 sec coz test run faster and need to remake it to explicit wait
	private long implicitlyWaitTimeout = 10;
	private IBrowser browser;
	private WebDriver driver;

	private WebDriverUtils(IBrowser browser) {
		this.browser = browser;
	}

	public static WebDriverUtils get() {
		if (instance == null) {
			synchronized (WebDriverUtils.class) {
				if (instance == null) {
					instance = new WebDriverUtils(new FirefoxBrowser());
				}
			}
		}
		return instance;
	}

	public static WebDriverUtils get(IBrowser browser) {
		if (instance == null) {
			synchronized (WebDriverUtils.class) {
				if (instance == null) {
					instance = new WebDriverUtils(browser);
				}
			}
		}
		return instance;
	}

	public WebDriver getWebDriver() {
		if (driver == null) {
			synchronized (WebDriverUtils.class) {
				if (driver == null) {
					driver = browser.getWebDriver();
					driver.manage()
							.timeouts()
							.implicitlyWait(getImplicitlyWaitTimeout(),
									TimeUnit.SECONDS);
					driver.manage().window().maximize();
				}
			}
		}
		return driver;
	}

	public long getImplicitlyWaitTimeout() {
		return implicitlyWaitTimeout;
	}

	public void load(String url) {
		getWebDriver().get(url);
	}

	public void refresh() {
		getWebDriver().navigate().refresh();
	}

	public void stop() {
		if (driver != null) {
			driver.quit();
		}
		driver = null;
		browser.quit();
	}

	public void forwardPage() {
		// TODO Use try
		getWebDriver().navigate().forward();
	}

	public void previousPage() {
		// TODO Use try
		getWebDriver().navigate().back();
	}

	public String getCurrentUrl() {
		return getWebDriver().getCurrentUrl();
	}

}
