package test.java.com.softserve.edu.oms.pages;

import main.java.edu.atqc.helpers.ContextVisible;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Xdr on 6/4/15.
 */

public class CustomerHomePage extends HomePage {
    private WebElement orderingTab;

    public CustomerHomePage() {
        super();
        this.orderingTab = ContextVisible.get().getVisibleWebElement(By.partialLinkText("Ordering"));
    }

    // - - - - - - - - - -

     public OrderPage orderingClick() {
        this.orderingTab.click();
         return new OrderPage();
    }

}
