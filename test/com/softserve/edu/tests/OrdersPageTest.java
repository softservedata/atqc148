package com.softserve.edu.tests;


import com.j256.ormlite.support.ConnectionSource;
import com.softserve.edu.dao.order.Order;
import com.softserve.edu.dao.order.OrderFromUI;
import com.softserve.edu.db.DbConnector;
import com.softserve.edu.db.DbProcessor;
import com.softserve.edu.helpers.*;
import com.softserve.edu.page.login.LoginPage;
import com.softserve.edu.page.login.Users;
import com.softserve.edu.page.order.*;
import com.softserve.edu.webdriver.BrowserRepository;
import com.softserve.edu.webdriver.WebDriverUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class OrdersPageTest {
    private WebDriver driver;


    @DataProvider
    public Object[][] getBrowser() {
        return new Object[][]{{BrowserRepository.getFirefoxTemporary()}, {BrowserRepository.getChromeTemporary()}};
    }

    @BeforeSuite()
    public void setUp() throws Exception {
        driver = WebDriverUtils.get(BrowserRepository.getFirefoxTemporary()).getWebDriver();
    }

    @BeforeMethod(groups = {"testGroup"})
    public void logIn(Method method) {
        LoginPage.setDriver().getLoginFields().logIn(Users.getCustomerUser());
        OrderPage.navigateToOrderPage();
        Report.log("Test started: " + method.getName());
    }

    @Test(priority = 2, groups = {"testGroup"})
    public void filterByStatusTest() throws Exception {
        OrderTable orderTable = OrderTable.setDriver();
        OrderFilter filter = OrderFilter.setDriver();
        List<String> filterValues = filter
                .readValuesForField(FilterValues.Status.getName());
        OrderStatuses statusValue;
        for (String crit : filterValues) {
            if (!crit.equals("None")) {
                statusValue = OrderStatuses.valueOf(crit);
                filter.orderBy(FilterValues.Status.getName(), statusValue.getName());
                filter.apply();
                List<OrderFromUI> ordersDB = DbHelper.readOrdersByField(
                        FilterValues.Status, statusValue);
                List<OrderFromUI> ordersUI = orderTable.getAllOrdersFromTable();
                Assert.assertTrue(orderTable.listsEqual(ordersUI, ordersDB));
            }
        }
    }

    @Test(priority = 2, groups = {"testGroup"})
    public void filterByRoleTest() throws Exception {
        OrderTable orderTable = OrderTable.setDriver();
        OrderFilter filter = OrderFilter.setDriver();
        List<String> filterValues = filter.readValuesForField(FilterValues.Role
                .getName());
        UserRoles roleValue;
        for (String crit : filterValues) {
            if (!crit.equals("None")) {
                roleValue = UserRoles.valueOf(crit);
                filter.orderBy(FilterValues.Role.getName(), roleValue.getName());
                filter.apply();
                List<OrderFromUI> ordersDB = DbHelper.readOrdersByField(
                        FilterValues.Role, roleValue);
                List<OrderFromUI> ordersUI = orderTable.getAllOrdersFromTable();
                Assert.assertTrue(orderTable.listsEqual(ordersUI, ordersDB));
            }
        }
    }


    @Test(priority = 2, groups = {"testGroup"})
    public void filterByNoneTest() throws Exception {
        OrderTable orderTable = OrderTable.setDriver();
        OrderFilter filter = OrderFilter.setDriver();
        List<String> filterValues = filter
                .readValuesForField(FilterValues.Status.getName());
        OrderStatuses statusValue;
        for (String crit : filterValues) {
            if (crit.equals("None")) {
                statusValue = OrderStatuses.valueOf(crit);
                filter.orderBy(FilterValues.Status.getName(), statusValue.getName());
                filter.apply();
                //read all data from db
                ConnectionSource connection = DbConnector.getConnection();
                List<Order> ordersFromDB = DbProcessor.setConnection(connection)
                        .getDataFromDB();
                List<OrderFromUI> ordersDB = Order.toOrdersFromUI(ordersFromDB);
                //read data from table (ui)
                List<OrderFromUI> ordersUI = orderTable.getAllOrdersFromTable();

                Assert.assertTrue(orderTable.listsEqual(ordersUI, ordersDB));
            }
        }
    }


    @Test(priority = 2, groups = {"testGroup"})
    public void searchTestPositive() throws Exception {
        OrderTable orderTable = OrderTable.setDriver();
        OrderSearch search = OrderSearch.setDriver();
        String testData = "ordername";
        search.setCriterionToOrderName();
        search.typeTextToSearch(testData);
        search.apply();
        List<OrderFromUI> ordersDB = DbHelper.readOrdersByOrderName(testData);
        List<OrderFromUI> ordersUI = orderTable.getAllOrdersFromTable();
        Assert.assertTrue(orderTable.listsEqual(ordersUI, ordersDB));
    }


    @Test(priority = 2, groups = {"testGroup"})
    public void searchTestNegative() throws Exception {
        OrderTable orderTable = OrderTable.setDriver();
        OrderSearch search = OrderSearch.setDriver();
        String testData = "123qwe";
        search.setCriterionToOrderName();
        search.typeTextToSearch(testData);
        search.apply();
        List<OrderFromUI> ordersDB = DbHelper.readOrdersByOrderName(testData);
        List<OrderFromUI> ordersUI = orderTable.getAllOrdersFromTable();
        Assert.assertTrue(ordersUI.size() == 0);
        Assert.assertTrue(orderTable.listsEqual(ordersUI, ordersDB));
    }

    @Test(priority = 2, groups = {"testGroup"})
    public void checkIfFirstButtonIsDisabled() {
        Assert.assertTrue(OrderNavigation.setDriver().isFirstBtnDisabled());
    }

    @Test(priority = 2, groups = {"testGroup"})
    public void checkIfPreviousButtonIsDisabled() {
        Assert.assertTrue(OrderNavigation.setDriver().isPrevBtnDisabled());
    }

    @Test(priority = 2, groups = {"testGroup"})
    public void checkIfNextButtonIsDisabled() {
        OrderNavigation navigation = OrderNavigation.setDriver();
        navigation.navigateToLastPage();
        navigation.getReferences();
        Assert.assertTrue(navigation.isNextBtnDisabled());
    }

    @Test(priority = 2, groups = {"testGroup"})
    public void checkIfLastButtonIsDisabled() {
        OrderNavigation navigation = OrderNavigation.setDriver();
        navigation.navigateToLastPage();
        navigation.getReferences();
        Assert.assertTrue(navigation.isLastBtnDisabled());
    }

    @Test(priority = 2, groups = {"testGroup"})
    public void checkFirstButton() {
        // test data
        List<OrderFromUI> testDataList = new ArrayList<OrderFromUI>();
        testDataList.add(OrderFromUI.get().setOrderName("")
                .setTotalPrice("31.0").setMaxDiscount("0").setDeliveryDate("")
                .setStatus("Created").setAssigne("login2")
                .setRole("Merchandiser").build());

        testDataList.add(OrderFromUI.get().setOrderName("")
                .setTotalPrice("9.0").setMaxDiscount("0")
                .setDeliveryDate("2015-05-21 00:00:00.0").setStatus("Pending")
                .setAssigne("login2").setRole("Merchandiser").build());

        OrderTable orderTable = OrderTable.setDriver();
        OrderNavigation navigation = OrderNavigation.setDriver();
        navigation.navigateToNextPage();
        navigation.getReferences();
        navigation.navigateToFirstPage();
        navigation.getReferences();
        List<OrderFromUI> ordersList = orderTable
                .getOrdersFromTablePage(orderTable.getOrdersTable());
        Assert.assertTrue(orderTable.listsEqual(testDataList, ordersList));
    }

    @Test(priority = 2, groups = {"testGroup"})
    public void checkPreviousButton() {
        // test data
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

        OrderTable orderTable = OrderTable.setDriver();
        OrderNavigation navigation = OrderNavigation.setDriver();
        navigation.navigateToLastPage();
        navigation.getReferences();
        navigation.navigateToPrevPage();
        navigation.getReferences();
        List<OrderFromUI> ordersList = orderTable
                .getOrdersFromTablePage(orderTable.getOrdersTable());
        Assert.assertTrue(orderTable.listsEqual(testDataList, ordersList));
    }

    @Test(priority = 2, groups = {"testGroup"})
    public void checkNextButton() {
        // hardcoding test data
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

        OrderTable orderTable = OrderTable.setDriver();
        OrderNavigation navigation = OrderNavigation.setDriver();
        navigation.navigateToFirstPage();
        navigation.getReferences();
        navigation.navigateToNextPage();
        navigation.getReferences();
        List<OrderFromUI> ordersList = orderTable
                .getOrdersFromTablePage(orderTable.getOrdersTable());
        Assert.assertTrue(orderTable.listsEqual(testDataList, ordersList));
    }

    @Test(priority = 2, groups = {"testGroup"})
    public void checkLastButton() {
        // hardcoding test data
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

        OrderTable orderTable = OrderTable.setDriver();
        OrderNavigation navigation = OrderNavigation.setDriver();
        navigation.navigateToLastPage();
        navigation.getReferences();
        List<OrderFromUI> ordersList = orderTable
                .getOrdersFromTablePage(orderTable.getOrdersTable());
        Assert.assertTrue(orderTable.listsEqual(testDataList, ordersList));
    }

    @Test(priority = 1, enabled = true, groups = {"testGroup"})
    // test runs slow coz of implicitlyWait(2, TimeUnit.SECONDS);
    // look comment in getDataFromRow() method
    public void testTableData() throws Exception {
        OrderTable orderTable = OrderTable.setDriver();
        ConnectionSource connection = DbConnector.getConnection();
        List<OrderFromUI> ordersUI = orderTable.getAllOrdersFromTable();
        List<Order> ordersFromDB = DbProcessor.setConnection(connection)
                .getDataFromDB();
        List<OrderFromUI> ordersDB = Order.toOrdersFromUI(ordersFromDB);
        Assert.assertTrue(orderTable.listsEqual(ordersUI, ordersDB));
    }

    @AfterMethod(groups = {"testGroup"})
    public void logOut(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
//           add thread.sleep
//            take screenshot

            Report.log("Test Failed. Making screenshot.");
            Report.takeScreenshot(testResult.getName());
        }
        if (testResult.getStatus() == ITestResult.SUCCESS) {
            Report.log("Test Succeed.");
        }
        LoginPage.setDriver().logOut();
    }

    @AfterSuite
    public void tearDown() throws Exception {
        driver.quit();
    }
}
