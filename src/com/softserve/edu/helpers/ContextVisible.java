package com.softserve.edu.helpers;

import com.softserve.edu.webdriver.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by Xdr on 6/2/15.
 */
public final class ContextVisible {
    private static volatile ContextVisible instance = null;
    private final String ERROR_NOT_FOUND = "";

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


    public WebElement getVisibleWebElement(By controlLocation) {
        WebElement webElement = new WebDriverWait(WebDriverUtils.get().getWebDriver(),
                                        WebDriverUtils.get().getImplicitlyWaitTimeout()).
                                            until(ExpectedConditions.visibilityOfElementLocated(controlLocation));
        if (webElement == null) {
//    log
//    screen
//    exception
        }
        return webElement;
    }

    public List getVisibleWebElements(By controlLocation) {
        List<WebElement> webElements = new WebDriverWait(WebDriverUtils.get().getWebDriver(), WebDriverUtils.get()
                .getImplicitlyWaitTimeout()).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(controlLocation));

        if (webElements.size() == 0) {
//            log
//            screenshot
//            throw exception
        }

        return webElements;
    }

    public WebElement getInvisibleWebElement(By controlLocation) {
        return null;
    }


}
