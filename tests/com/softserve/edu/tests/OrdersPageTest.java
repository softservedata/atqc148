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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OrdersPageTest {
    private WebDriver driver;

    @BeforeSuite
    public void setUp() throws Exception {
        driver = WebDriverUtils.get(BrowserRepository.getFirefoxTemporary()).getWebDriver();
    }

    @BeforeMethod(groups = {"testGroup"})
    public void logIn() {
        LoginPage.setDriver(driver).getLoginFields().logIn(Users.getCustomerUser());
        OrderPage.navigateToOrderPage(driver);
    }

    @Test(groups = {"testGroup"})
    public void filterByStatusTest() throws Exception {
        OrderTable orderTable = OrderTable.setDriver(driver);
        OrderFilter filter = OrderFilter.setDriver(driver);
        List<String> filterValues = filter
                .readValuesForField(FilterValues.Status.getName());
        OrderStatuses statusValue;

        Iterator<String> iter = filterValues.iterator();
//            skip 1st filter because its "None"
        iter.next();
        while (iter.hasNext()) {
            String crit = iter.next();
            statusValue = OrderStatuses.valueOf(crit);
            filter.orderBy(FilterValues.Status.getName(), statusValue.getName());
            filter.apply();
            List<OrderFromUI> ordersDB = DbHelper.readOrdersByField(
                    FilterValues.Status, statusValue);
            List<OrderFromUI> ordersUI = orderTable.getAllOrdersFromTable();
            // method compares sizes, then compares each by each
            Assert.assertTrue(orderTable.listsEqual(ordersUI, ordersDB));
        }
    }

    @Test(groups = {"testGroup"})
    public void filterByRoleTest() throws Exception {
        OrderTable orderTable = OrderTable.setDriver(driver);
        OrderFilter filter = OrderFilter.setDriver(driver);
        List<String> filterValues = filter.readValuesForField(FilterValues.Role
                .getName());
        UserRoles roleValue;

        Iterator<String> iter = filterValues.iterator();
        iter.next();
        while (iter.hasNext()) {
            String crit = iter.next();
            roleValue = UserRoles.valueOf(crit);
            filter.orderBy(FilterValues.Role.getName(), roleValue.getName());
            filter.apply();
            List<OrderFromUI> ordersDB = DbHelper.readOrdersByField(
                    FilterValues.Role, roleValue);
            List<OrderFromUI> ordersUI = orderTable.getAllOrdersFromTable();

            // method compares sizes, then compares each by each
            Assert.assertTrue(orderTable.listsEqual(ordersUI, ordersDB));
        }
    }


    @Test(groups = {"testGroup"})
    public void filterByNoneTest() throws Exception {
        OrderTable orderTable = OrderTable.setDriver(driver);
        OrderFilter filter = OrderFilter.setDriver(driver);
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


    @Test(groups = {"testGroup"})
    public void searchTestPositive() throws Exception {
        OrderTable orderTable = OrderTable.setDriver(driver);
        OrderSearch search = OrderSearch.setDriver(driver);
        String testData = "ordername";
        search.setCriterionToOrderName();
        search.typeTextToSearch(testData);
        search.apply();
        List<OrderFromUI> ordersDB = DbHelper.readOrdersByOrderName(testData);
        List<OrderFromUI> ordersUI = orderTable.getAllOrdersFromTable();
        Assert.assertTrue(orderTable.listsEqual(ordersUI, ordersDB));
    }


    @Test(groups = {"testGroup"})
    public void searchTestNegative() throws Exception {
        OrderTable orderTable = OrderTable.setDriver(driver);
        OrderSearch search = OrderSearch.setDriver(driver);
        String testData = "123qwe";
        search.setCriterionToOrderName();
        search.typeTextToSearch(testData);
        search.apply();
        List<OrderFromUI> ordersDB = DbHelper.readOrdersByOrderName(testData);
        List<OrderFromUI> ordersUI = orderTable.getAllOrdersFromTable();
//        need two checks here: empty table and equal with db table.
        Assert.assertTrue(ordersUI.size() == 0);
        Assert.assertTrue(orderTable.listsEqual(ordersUI, ordersDB));
    }

    @Test(priority = 2, groups = {"testGroup"})
    public void checkIfFirstButtonIsDisabled() {
        Assert.assertTrue(OrderNavigation.setDriver(driver).isFirstBtnDisabled());
    }

    @Test(priority = 2, groups = {"testGroup"})
    public void checkIfPreviousButtonIsDisabled() {
        Assert.assertTrue(OrderNavigation.setDriver(driver).isPrevBtnDisabled());
    }

    @Test(priority = 2, groups = {"testGroup"})
    public void checkIfNextButtonIsDisabled() {
        OrderNavigation navigation = OrderNavigation.setDriver(driver);
        navigation.navigateToLastPage();
        navigation.refreshReferences();
        Assert.assertTrue(navigation.isNextBtnDisabled());
    }

    @Test(priority = 2, groups = {"testGroup"})
    public void checkIfLastButtonIsDisabled() {
        OrderNavigation navigation = OrderNavigation.setDriver(driver);
        navigation.navigateToLastPage();
        navigation.refreshReferences();
        Assert.assertTrue(navigation.isLastBtnDisabled());
    }

    @Test(priority = 2, groups = {"testGroup"})
    public void checkFirstButton() {
        // hardcoding test data
        List<OrderFromUI> testDataList = new ArrayList<OrderFromUI>();
        // testDataList.add(new OrderFromUI("", "31.0", "0", "", "Created",
        // "login2", "Merchandiser"));
        // testDataList.add(new OrderFromUI("", "9.0", "0",
        // "2015-05-21 00:00:00.0", "Pending", "login2", "Merchandiser"));
        testDataList.add(OrderFromUI.get().setOrderName("")
                .setTotalPrice("31.0").setMaxDiscount("0").setDeliveryDate("")
                .setStatus("Created").setAssigne("login2")
                .setRole("Merchandiser").build());

        testDataList.add(OrderFromUI.get().setOrderName("")
                .setTotalPrice("9.0").setMaxDiscount("0")
                .setDeliveryDate("2015-05-21 00:00:00.0").setStatus("Pending")
                .setAssigne("login2").setRole("Merchandiser").build());

        OrderTable orderTable = OrderTable.setDriver(driver);
        OrderNavigation navigation = OrderNavigation.setDriver(driver);
        navigation.navigateToNextPage();
        navigation.refreshReferences();
        navigation.navigateToFirstPage();
        navigation.refreshReferences();
        List<OrderFromUI> ordersList = orderTable
                .getOrdersFromTablePage(orderTable.getOrdersTable());
        Assert.assertTrue(orderTable.listsEqual(testDataList, ordersList));
    }

    @Test(priority = 2, groups = {"testGroup"})
    public void checkPreviousButton() {
        // hardcoding test data
        List<OrderFromUI> testDataList = new ArrayList<OrderFromUI>();
        // OrderName5 0.0 15 2012-12-02 00:00:00.0 Delivered myroslav
        // Administrator
//		testDataList.add(new OrderFromUI("OrderName5", "0.0", "15",
//				"2012-12-02 00:00:00.0", "Delivered", "myroslav",
//				"Administrator"));

        testDataList.add(OrderFromUI.get().setOrderName("OrderName5")
                .setTotalPrice("0.0").setMaxDiscount("15")
                .setDeliveryDate("2012-12-02 00:00:00.0")
                .setStatus("Delivered").setAssigne("myroslav")
                .setRole("Administrator"));

        // OrderName3 0.0 13 2012-12-02 00:00:00.0 Pending romanS Administrator
//		testDataList.add(new OrderFromUI("OrderName3", "0.0", "13",
//				"2012-12-02 00:00:00.0", "Pending", "romanS", "Administrator"));

        testDataList.add(OrderFromUI.get().setOrderName("OrderName3")
                .setTotalPrice("0.0").setMaxDiscount("13")
                .setDeliveryDate("2012-12-02 00:00:00.0")
                .setStatus("Pending").setAssigne("romanS")
                .setRole("Administrator"));

        OrderTable orderTable = OrderTable.setDriver(driver);
        OrderNavigation navigation = OrderNavigation.setDriver(driver);
        navigation.navigateToLastPage();
        navigation.refreshReferences();
        navigation.navigateToPrevPage();
        navigation.refreshReferences();
        List<OrderFromUI> ordersList = orderTable
                .getOrdersFromTablePage(orderTable.getOrdersTable());
        Assert.assertTrue(orderTable.listsEqual(testDataList, ordersList));
    }

    @Test(priority = 2, groups = {"testGroup"})
    public void checkNextButton() {
        // hardcoding test data
        List<OrderFromUI> testDataList = new ArrayList<OrderFromUI>();

//		testDataList.add(new OrderFromUI("", "47.0", "0", "", "Ordered",
//				"login2", "Merchandiser"));

        testDataList.add(OrderFromUI.get().setOrderName("")
                .setTotalPrice("47.0").setMaxDiscount("0")
                .setDeliveryDate("")
                .setStatus("Ordered").setAssigne("login2")
                .setRole("Merchandiser"));

//		testDataList.add(new OrderFromUI("", "220.0", "0", "", "Ordered",
//				"login2", "Merchandiser"));

        testDataList.add(OrderFromUI.get().setOrderName("")
                .setTotalPrice("220.0").setMaxDiscount("0")
                .setDeliveryDate("")
                .setStatus("Ordered").setAssigne("login2")
                .setRole("Merchandiser"));


        OrderTable orderTable = OrderTable.setDriver(driver);
        OrderNavigation navigation = OrderNavigation.setDriver(driver);
        navigation.navigateToFirstPage();
        navigation.refreshReferences();
        navigation.navigateToNextPage();
        navigation.refreshReferences();
        List<OrderFromUI> ordersList = orderTable
                .getOrdersFromTablePage(orderTable.getOrdersTable());
        Assert.assertTrue(orderTable.listsEqual(testDataList, ordersList));
    }

    @Test(priority = 2, groups = {"testGroup"})
    public void checkLastButton() {
        // hardcoding test data
        List<OrderFromUI> testDataList = new ArrayList<OrderFromUI>();
        // OrderName2 120.0 12 2012-12-02 00:00:00.0 Ordered marko Administrator
//		testDataList.add(new OrderFromUI("OrderName2", "120.0", "12",
//				"2012-12-02 00:00:00.0", "Ordered", "marko", "Administrator"));

        testDataList.add(OrderFromUI.get().setOrderName("OrderName2")
                .setTotalPrice("120.0").setMaxDiscount("12")
                .setDeliveryDate("2012-12-02 00:00:00.0")
                .setStatus("Ordered").setAssigne("marko")
                .setRole("Administrator"));


        // OrderName4 0.0 14 2012-12-02 00:00:00.0 Created vitalik Administrator
        // testDataList
        // .add(new OrderFromUI("OrderName4", "0.0", "14",
        // "2012-12-02 00:00:00.0", "Created", "vitalik",
        // "Administrator"));

        testDataList.add(OrderFromUI.get().setOrderName("OrderName4")
                .setTotalPrice("0.0").setMaxDiscount("14")
                .setDeliveryDate("2012-12-02 00:00:00.0")
                .setStatus("Created").setAssigne("vitalik")
                .setRole("Administrator"));


        OrderTable orderTable = OrderTable.setDriver(driver);
        OrderNavigation navigation = OrderNavigation.setDriver(driver);
        navigation.navigateToLastPage();
        navigation.refreshReferences();
        List<OrderFromUI> ordersList = orderTable
                .getOrdersFromTablePage(orderTable.getOrdersTable());
        Assert.assertTrue(orderTable.listsEqual(testDataList, ordersList));
    }

    @Test(priority = 1, enabled = true, groups = {"testGroup"})
    // test runs slow coz of implicitlyWait(2, TimeUnit.SECONDS);
    // look comment in getDataFromRow() method
    public void testTableData() throws Exception {
        OrderTable orderTable = OrderTable.setDriver(driver);
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
            ScreenShot.takeScreenshot(testResult.getName());
        }
        LoginPage.setDriver(driver).logOut();
    }

    @AfterSuite
    public void tearDown() throws Exception {
        driver.quit();
    }
}
