package test.java.com.softserve.edu.oms.tests;


import com.j256.ormlite.support.ConnectionSource;
import main.java.edu.atqc.db.dao.order.Order;
import main.java.edu.atqc.db.dao.order.OrderFromUI;
import main.java.edu.atqc.db.dbhelpers.DbConnector;
import main.java.edu.atqc.db.dbhelpers.DbHelpers;
import main.java.edu.atqc.helpers.*;
import main.java.edu.atqc.page.login.LoginPageHelper;
import main.java.edu.atqc.page.order.OrderPageHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.java.com.softserve.edu.oms.data.UrlRepository;
import test.java.com.softserve.edu.oms.data.user.UserRepository;
import main.java.edu.atqc.page.order.*;
import main.java.edu.atqc.helpers.webdriver.BrowserRepository;
import main.java.edu.atqc.helpers.webdriver.WebDriverUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import test.java.com.softserve.edu.oms.pages.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class OrdersPageTest {
    private WebDriver driver;


    @DataProvider
    public Object[][] browserProvider() {
        return new Object[][]{{BrowserRepository.getFirefoxTemporary()}, {BrowserRepository.getChromeTemporary()}};
    }

    @BeforeSuite()
    public void setUp() throws Exception {
    }

    @BeforeMethod(groups = {"testGroup"})
    public void logIn(Method method) {
        Report.log("Test started: " + method.getName());
    }

    @Test(priority = 2, groups = {"testGroup"}, dependsOnMethods = "checkNextButton")
    public void filterByStatusTest() throws Exception {
        // DEPENDS ON NEXTPAGEBUTTONTEST
        //        Login as customer
        CustomerHomePage customerHomePage = StartLoginPage
                .load(BrowserRepository.getFirefoxTemporary(), UrlRepository.Urls.LOCAL_HOST.toString())
                .successCustomerLogin(UserRepository.getCustomerUser());
//        open orders page
        OrderPage orderpage = customerHomePage.orderingClick();
        List<String> filterValues = orderpage.readValuesForField(FilterValues.Status.getName());
        OrderStatuses statusValue;
        for (String crit : filterValues) {
            if (!crit.equals("None")) {
                statusValue = OrderStatuses.valueOf(crit);
                orderpage.orderBy(FilterValues.Status.getName(), statusValue.getName());
                orderpage.apply();
                List<OrderFromUI> ordersDB = DbHelpers.readOrdersByField(
                        FilterValues.Status, statusValue);
                List<OrderFromUI> ordersUI = orderpage.getAllOrdersFromTable();
                Assert.assertTrue(orderpage.listsEqual(ordersUI, ordersDB));
            }
        }
    }

    @Test(priority = 2, groups = {"testGroup"}, dependsOnMethods = "checkNextButton")
    public void filterByRoleTest() throws Exception {
// DEPENDS ON NEXTPAGEBUTTONTEST
//      Login as customer, open orders page
        OrderPage orderpage = StartLoginPage
                .load(BrowserRepository.getFirefoxTemporary(), UrlRepository.Urls.LOCAL_HOST.toString())
                .successCustomerLogin(UserRepository.getCustomerUser()).orderingClick();
        List<String> filterValues = orderpage.readValuesForField(FilterValues.Role
                .getName());
        UserRoles roleValue;
        for (String crit : filterValues) {
            if (!crit.equals("None")) {
                roleValue = UserRoles.valueOf(crit);
                orderpage.orderBy(FilterValues.Role.getName(), roleValue.getName());
                orderpage.apply();
                List<OrderFromUI> ordersDB = DbHelpers.readOrdersByField(
                        FilterValues.Role, roleValue);
                List<OrderFromUI> ordersUI = orderpage.getAllOrdersFromTable();
                Assert.assertTrue(orderpage.listsEqual(ordersUI, ordersDB));
            }
        }
    }

    @Test(priority = 2, groups = {"testGroup"}, dependsOnMethods = "checkNextButton")
    public void filterByNoneTest() throws Exception {
        // DEPENDS ON NEXTPAGEBUTTONTEST
        //      Login as customer, open orders page
        OrderPage orderpage = StartLoginPage
                .load(BrowserRepository.getFirefoxTemporary(), UrlRepository.Urls.LOCAL_HOST.toString())
                .successCustomerLogin(UserRepository.getCustomerUser()).orderingClick();
        List<String> filterValues = orderpage
                .readValuesForField(FilterValues.Status.getName());
        OrderStatuses statusValue;
        for (String crit : filterValues) {
            if (crit.equals("None")) {
                statusValue = OrderStatuses.valueOf(crit);
                orderpage.orderBy(FilterValues.Status.getName(), statusValue.getName());
                orderpage.apply();
                //read all data from db
                ConnectionSource connection = DbConnector.getConnection();
                List<Order> ordersFromDB = DbHelpers.setConnection(connection)
                        .getDataFromDB();
                List<OrderFromUI> ordersDB = Order.toOrdersFromUI(ordersFromDB);
                //read data from table (ui)
                List<OrderFromUI> ordersUI = orderpage.getAllOrdersFromTable();

                Assert.assertTrue(orderpage.listsEqual(ordersUI, ordersDB));
            }
        }
    }


    @Test(priority = 2, groups = {"testGroup"}, dependsOnMethods = "checkNextButton")
    public void searchTestPositive() throws Exception {
//            DEPENDS ON NEXTBUTTONTEST
        OrderPage orderpage = StartLoginPage
                .load(BrowserRepository.getFirefoxTemporary(), UrlRepository.Urls.LOCAL_HOST.toString())
                .successCustomerLogin(UserRepository.getCustomerUser()).orderingClick();
        String testData = "ordername";
        orderpage.setCriterionToOrderName();
        orderpage.typeTextToSearch(testData);
        orderpage.apply();
        List<OrderFromUI> ordersDB = DbHelpers.readOrdersByOrderName(testData);
        List<OrderFromUI> ordersUI = orderpage.getAllOrdersFromTable();
        Assert.assertTrue(orderpage.listsEqual(ordersUI, ordersDB));
    }


    @Test(priority = 2, groups = {"testGroup"})
    public void searchTestNegative() throws Exception {
        OrderPage orderpage = StartLoginPage
                .load(BrowserRepository.getFirefoxTemporary(), UrlRepository.Urls.LOCAL_HOST.toString())
                .successCustomerLogin(UserRepository.getCustomerUser()).orderingClick();
        String testData = "123qwe";
        orderpage.setCriterionToOrderName();
        orderpage.typeTextToSearch(testData);
        orderpage.apply();
        List<OrderFromUI> ordersDB = DbHelpers.readOrdersByOrderName(testData);
        List<OrderFromUI> ordersUI = orderpage.getAllOrdersFromTable();
        Assert.assertTrue(ordersUI.size() == 0);
        Assert.assertTrue(orderpage.listsEqual(ordersUI, ordersDB));
    }

    @Test(priority = 2, groups = {"testGroup"})
    public void checkIfFirstButtonIsDisabled() {
        OrderPage orderpage = StartLoginPage
                .load(BrowserRepository.getFirefoxTemporary(), UrlRepository.Urls.LOCAL_HOST.toString())
                .successCustomerLogin(UserRepository.getCustomerUser()).orderingClick();
        Assert.assertTrue(orderpage.isFirstBtnDisabled());
    }

    @Test(priority = 2, groups = {"testGroup"})
    public void checkIfPreviousButtonIsDisabled() {
        OrderPage orderpage = StartLoginPage
                .load(BrowserRepository.getFirefoxTemporary(), UrlRepository.Urls.LOCAL_HOST.toString())
                .successCustomerLogin(UserRepository.getCustomerUser()).orderingClick();
        Assert.assertTrue(orderpage.isPrevBtnDisabled());
    }

    @Test(priority = 2, groups = {"testGroup"})
    public void checkIfNextButtonIsDisabled() {
        OrderPage orderpage = StartLoginPage
                .load(BrowserRepository.getFirefoxTemporary(), UrlRepository.Urls.LOCAL_HOST.toString())
                .successCustomerLogin(UserRepository.getCustomerUser()).orderingClick();
        orderpage.navigateToLastPage();
        orderpage.getNavButtons(); //makes sense to call this method inside navigatetolastpage();
        Assert.assertTrue(orderpage.isNextBtnDisabled());
    }

    @Test(priority = 2, groups = {"testGroup"})
    public void checkIfLastButtonIsDisabled() {
        OrderPage orderpage = StartLoginPage
                .load(BrowserRepository.getFirefoxTemporary(), UrlRepository.Urls.LOCAL_HOST.toString())
                .successCustomerLogin(UserRepository.getCustomerUser()).orderingClick();
        orderpage.navigateToLastPage();
        orderpage.getNavButtons();
        Assert.assertTrue(orderpage.isLastBtnDisabled());
    }


    @DataProvider
    public Object[][] firstOrdersProvider() {
        List<OrderFromUI> testDataList = new ArrayList<OrderFromUI>();
        testDataList.add(OrderFromUI.get().setOrderName("")
                .setTotalPrice("31.0").setMaxDiscount("0").setDeliveryDate("")
                .setStatus("Created").setAssigne("login2")
                .setRole("Merchandiser").build());

        testDataList.add(OrderFromUI.get().setOrderName("")
                .setTotalPrice("9.0").setMaxDiscount("0")
                .setDeliveryDate("2015-05-21 00:00:00.0").setStatus("Pending")
                .setAssigne("login2").setRole("Merchandiser").build());
        return new Object[][]{{testDataList}};
    }

    @Test(priority = 2, groups = {"testGroup"}, dataProvider = "firstOrdersProvider")
    public void checkFirstButton(List<OrderFromUI> testDataList) {
        OrderPage orderpage = StartLoginPage
                .load(BrowserRepository.getFirefoxTemporary(), UrlRepository.Urls.LOCAL_HOST.toString())
                .successCustomerLogin(UserRepository.getCustomerUser()).orderingClick();
        orderpage.navigateToNextPage();
        orderpage.getNavButtons();
        orderpage.findOrdersTable();
        orderpage.navigateToFirstPage();
        orderpage.getNavButtons();
        orderpage.findOrdersTable();
        List<OrderFromUI> ordersList = orderpage
                .getOrdersFromTablePage();
        Assert.assertTrue(orderpage.listsEqual(testDataList, ordersList));
    }

    @DataProvider
    public Object[][] previousOrdersProvider() {
        List<OrderFromUI> testDataList = new ArrayList<OrderFromUI>();
        testDataList.add(OrderFromUI.get().setOrderName("OrderName5")
                .setTotalPrice("0.0").setMaxDiscount("15")
                .setDeliveryDate("2012-12-02 00:00:00.0")
                .setStatus("Delivered").setAssigne("myroslav")
                .setRole("Administrator"));
        testDataList.add(OrderFromUI.get().setOrderName("OrderName3")
                .setTotalPrice("0.0").setMaxDiscount("13")
                .setDeliveryDate("2012-12-02 00:00:00.0")
                .setStatus("Pending").setAssigne("romanS")
                .setRole("Administrator"));
        return new Object[][]{{testDataList}};
    }

    @Test(priority = 2, groups = {"testGroup"}, dataProvider = "previousOrdersProvider")
    public void checkPreviousButton(List<OrderFromUI> testDataList) {
        OrderPage orderpage = StartLoginPage
                .load(BrowserRepository.getFirefoxTemporary(), UrlRepository.Urls.LOCAL_HOST.toString())
                .successCustomerLogin(UserRepository.getCustomerUser()).orderingClick();
        orderpage.navigateToLastPage();
        orderpage.getNavButtons();
        orderpage.findOrdersTable();
        orderpage.navigateToPrevPage();
        orderpage.getNavButtons();
        orderpage.findOrdersTable();
        List<OrderFromUI> ordersList = orderpage
                .getOrdersFromTablePage();
        Assert.assertTrue(orderpage.listsEqual(testDataList, ordersList));
    }


    @DataProvider
    public Object[][] nextOrdersProvider() {
        List<OrderFromUI> testDataList = new ArrayList<OrderFromUI>();
        testDataList.add(OrderFromUI.get().setOrderName("")
                .setTotalPrice("47.0").setMaxDiscount("0")
                .setDeliveryDate("")
                .setStatus("Ordered").setAssigne("login2")
                .setRole("Merchandiser"));
        testDataList.add(OrderFromUI.get().setOrderName("")
                .setTotalPrice("220.0").setMaxDiscount("0")
                .setDeliveryDate("")
                .setStatus("Ordered").setAssigne("login2")
                .setRole("Merchandiser"));
        return new Object[][]{{testDataList}};
    }

    @Test(priority = 1, groups = {"testGroup"}, dataProvider = "nextOrdersProvider")
    public void checkNextButton(List<OrderFromUI> testDataList) {
        OrderPage orderpage = StartLoginPage
                .load(BrowserRepository.getFirefoxTemporary(), UrlRepository.Urls.LOCAL_HOST.toString())
                .successCustomerLogin(UserRepository.getCustomerUser()).orderingClick();
        orderpage.navigateToFirstPage();
        orderpage.getNavButtons();
        orderpage.findOrdersTable();
        orderpage.navigateToNextPage();
        orderpage.getNavButtons();
        orderpage.findOrdersTable();
        List<OrderFromUI> ordersList = orderpage
                .getOrdersFromTablePage();
        Assert.assertTrue(orderpage.listsEqual(testDataList, ordersList));
    }


    @DataProvider
    public Object[][] lastOrdersProvider() {
        List<OrderFromUI> testDataList = new ArrayList<OrderFromUI>();
        testDataList.add(OrderFromUI.get().setOrderName("OrderName2")
                .setTotalPrice("120.0").setMaxDiscount("12")
                .setDeliveryDate("2012-12-02 00:00:00.0")
                .setStatus("Ordered").setAssigne("marko")
                .setRole("Administrator"));
        testDataList.add(OrderFromUI.get().setOrderName("OrderName4")
                .setTotalPrice("0.0").setMaxDiscount("14")
                .setDeliveryDate("2012-12-02 00:00:00.0")
                .setStatus("Created").setAssigne("vitalik")
                .setRole("Administrator"));
        return new Object[][]{{testDataList}};
    }

    @Test(priority = 2, groups = {"testGroup"}, dataProvider = "lastOrdersProvider")
    public void checkLastButton(List<OrderFromUI> testDataList) {
        OrderPage orderpage = StartLoginPage
                .load(BrowserRepository.getFirefoxTemporary(), UrlRepository.Urls.LOCAL_HOST.toString())
                .successCustomerLogin(UserRepository.getCustomerUser()).orderingClick();
        orderpage.navigateToLastPage();
        orderpage.getNavButtons();
        orderpage.findOrdersTable();
        List<OrderFromUI> ordersList = orderpage
                .getOrdersFromTablePage();
        Assert.assertTrue(orderpage.listsEqual(testDataList, ordersList));
    }

    @Test(priority = 1, enabled = true, groups = {"testGroup"}, dependsOnMethods = "checkNextButton")
    public void testTableData() throws Exception {
        OrderPage orderpage = StartLoginPage
                .load(BrowserRepository.getFirefoxTemporary(), UrlRepository.Urls.LOCAL_HOST.toString())
                .successCustomerLogin(UserRepository.getCustomerUser()).orderingClick();
//        get all orders from db
        List<OrderFromUI> ordersDB = DbHelpers.setConnection(DbConnector.getConnection()).getDataFromDatabase();
//        get all orders from ui
        List<OrderFromUI> ordersUI = orderpage.getAllOrdersFromTable();
//        compare lists
        Assert.assertTrue(orderpage.listsEqual(ordersUI, ordersDB));
    }

    @AfterMethod(groups = {"testGroup"})
    public void takeScreenshotIfFail(ITestResult testResult) {

        if (testResult.getStatus() == ITestResult.FAILURE) {
//           wait till opacity is 1 or
//            take screenshot

//            ExpectedConditions.
            Report.log("Test Failed. Making screenshot.");
            Report.takeScreenshot(testResult.getName());
        }
        if (testResult.getStatus() == ITestResult.SUCCESS) {
            Report.log("Test Succeed.");
        }

//   TODO     to logout from page. need to refactor
        ContextVisible.get().getVisibleWebElement(By.xpath("//a[@href='/OMS/logout.htm']")).click();
    }

    @AfterSuite
    public void tearDown() throws Exception {
        WebDriverUtils.get().stop();
    }
}
