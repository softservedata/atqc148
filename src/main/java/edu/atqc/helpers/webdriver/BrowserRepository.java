package main.java.edu.atqc.helpers.webdriver;

public class BrowserRepository {

    public static IBrowser getFirefoxTemporary() {
        return new FirefoxBrowser();
    }

    public static IBrowser getChromeTemporary() { return new ChromeBrowser();
    }


}