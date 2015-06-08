package test.java.com.softserve.edu.oms.pages;

import main.java.edu.atqc.helpers.ContextVisible;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Xdr on 6/4/15.
 */
public class AdminHomePage extends  HomePage{

        private WebElement administrationTab;

        public AdminHomePage() {
            this.administrationTab = ContextVisible.get().getVisibleWebElement(By.partialLinkText("Administration"));
        }
        // - - - - - - - - - -
//    public AdministrationPage administrationClick() {
//        // public void administrationClick() {
//        this.administrationTab.click();
//        return new AdministrationPage();
//    }
    }