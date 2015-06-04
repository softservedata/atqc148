package com.softserve.edu.page.order;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.softserve.edu.helpers.Report;
import com.softserve.edu.helpers.webdriver.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.db.dao.order.OrderFromUI;

public class OrderTable {

    private WebDriver driver;

    private OrderTable(WebDriver driver) {
        this.driver = driver;
    }

    public static OrderTable setDriver() {
        return new OrderTable(WebDriverUtils.get().getWebDriver());
    }

    /**
     * Gets order table size. Navigates through orders table until finds duplicated values.
     *
     * @return Size of orders table.
     */
    public int getTableSize() {
        OrderPage.navigateToOrderPage();
        WebElement ordersTable = getOrdersTable();
        List<OrderFromUI> ordersFromTable = getOrdersFromTablePage(ordersTable);
        int size = ordersFromTable.size();
        OrderNavigation.setDriver().navigateToNextPage();
        ordersTable = getOrdersTable();
        while (!ordersFromTable.get(0).equals(
                getOrdersFromTablePage(ordersTable).get(0))) {

            if (!ordersFromTable.get(ordersFromTable.size() - 1).equals(
                    getOrdersFromTablePage(ordersTable).get(0))) {
                size += 2;
                ordersFromTable = getOrdersFromTablePage(ordersTable);
                OrderNavigation.setDriver().navigateToNextPage();
                ordersTable = getOrdersTable();
            } else {
                size += 1;
                ordersFromTable = getOrdersFromTablePage(ordersTable);
                OrderNavigation.setDriver().navigateToNextPage();
                ordersTable = getOrdersTable();
            }
        }
        return size;
    }

    /**
     * Reads all orders from table. Navigates through untill duplicate is found.
     *
     * @return List of orders from orders table.
     */
    public List<OrderFromUI> getAllOrdersFromTable() {
        Report.log("Getting all orders from table.");
        // OrderPage.navigateToOrderPage(driver);
        WebElement ordersTable = getOrdersTable();
        List<OrderFromUI> orders = new ArrayList<OrderFromUI>();

        // 1.get orders from table page
        if (orders.size() == 0) {
            List<OrderFromUI> ordersFromTable = getOrdersFromTablePage(ordersTable);
            if (ordersFromTable.size() == 0) {
                return orders;
            }
            for (OrderFromUI order : ordersFromTable) {
                orders.add(order);
            }
            OrderNavigation.setDriver().navigateToNextPage();
            ordersTable = getOrdersTable();
        }
        // 2.compare
        // there's order duplicate *(look comment below)
        List<OrderFromUI> ordersUi = getOrdersFromTablePage(ordersTable);
        while (!orders.get(orders.size() - ordersUi.size()).equals(
                ordersUi.get(0))) {
            // List<OrderFromUI> ordersFromTable =
            // getOrdersFromTablePage(ordersTable);
            Iterator<OrderFromUI> iter = ordersUi.iterator();
            while (iter.hasNext()) {
                OrderFromUI order = iter.next();
                // added check for duplicates
                if (!orders.get(orders.size() - 1).equals(order)) {
                    orders.add(order);
                }
            }
            OrderNavigation.setDriver().navigateToNextPage();
            ordersTable = getOrdersTable();
            ordersUi = getOrdersFromTablePage(ordersTable);
        }
        return orders;
    }

    /**
     * Finds order table on page.
     *
     * @return orders table web element.
     */
    public WebElement getOrdersTable() {
        return driver.findElement(By.id("list"));
    }

    /**
     * Reads all rows from specified table.
     *
     * @param table web element - table.
     * @return list of orders from table.
     */
    public List<OrderFromUI> getOrdersFromTablePage(WebElement table) {
        List<OrderFromUI> orderList = new ArrayList<OrderFromUI>();
        List<WebElement> rowWebElements = table.findElements(By.tagName("tr"));
        List<List<String>> rows = new ArrayList<List<String>>();

        for (WebElement row : rowWebElements) {
            List<String> rowCells = getDataFromRow(row);
            if (!rowCells.isEmpty())
                rows.add(rowCells);
        }
        for (List<String> cellsStr : rows) {
            orderList.add(new OrderFromUI(cellsStr.get(0), cellsStr.get(1),
                    cellsStr.get(2), cellsStr.get(3), cellsStr.get(4), cellsStr
                    .get(5), cellsStr.get(6)));
        }
        return orderList;
    }

    /**
     * Reads data from specified table row.
     *
     * @param row row as web element.
     * @return list of row cell values converted to string.
     */
    public static List<String> getDataFromRow(WebElement row) {
        List<String> rowString = new LinkedList<String>();
        // try to make driver explicitly wait
        // cos it runs slow implisitly waits amount of seconds from
        // driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        List<WebElement> cells = row.findElements(By.cssSelector("td"));
        for (WebElement cell : cells) {
            rowString.add(cell.getText());
        }
        return rowString;
    }

    /**
     * Reads order by row number in table.
     *
     * @param num row number.
     * @return OrderFromUI object with data from table row.
     */
    public OrderFromUI getOrderByNumber(int num) {
        OrderPage.navigateToOrderPage();
        WebElement table = getOrdersTable();
        WebElement row = null;
        int pageNum = 1;
        int rowsSize = table.findElements(By.tagName("tr")).size() - 1;
        boolean elementFound = false;
        while (!elementFound) {
            List<WebElement> rows = table.findElements(By.tagName("tr"));
            if (rowsSize >= num) {
                row = rows.get(num / pageNum);
                elementFound = true;
            } else {
                OrderNavigation.setDriver().navigateToNextPage();
                table = getOrdersTable();
                pageNum++;
                rowsSize *= pageNum;
            }
        }
        List<String> rowStr = getDataFromRow(row);
//        OrderFromUI ord = new OrderFromUI(rowStr.get(0), rowStr.get(1),
//                rowStr.get(2), rowStr.get(3), rowStr.get(4), rowStr.get(5),
//                rowStr.get(6));
        OrderFromUI ord = OrderFromUI.get().setOrderName(rowStr.get(0)).setTotalPrice(rowStr.get(1))
                                                .setMaxDiscount(rowStr.get(2)).setDeliveryDate(rowStr.get(3))
                                                        .setStatus(rowStr.get(4)).setAssigne(rowStr.get(5))
                                                                                             .setRole(rowStr.get(6));
        ord.print();
        return ord;
    }

    /**
     * Checks two list for equality. Compares all list elements.
     *
     * @param listOne first list to compare.
     * @param listTwo second list to compare.
     * @return true if are equal, false if arent equal.
     */
    public boolean listsEqual(List<OrderFromUI> listOne,
                              List<OrderFromUI> listTwo) {
        Report.log("Checking lists equality.");
        // 1. compare sizes
        if (listOne.size() != listTwo.size()) {
            return false;

            // 2. if they are empty then they are equal
        } else if (listOne.size() == 0 && listTwo.size() == 0) {
            return true;
        }
        // 2.if sizes are equal, compare each by each
        int equalFieldsNumber = 0;
        for (OrderFromUI elementFromFirst : listOne) {
            for (OrderFromUI elementFromSecond : listTwo) {
                if (elementFromFirst.equals(elementFromSecond)) {
                    equalFieldsNumber++;
                }
            }
        }
        Report.log("Number of equal orders: " + equalFieldsNumber);
        // if all orders are equal, result will be true
        return (equalFieldsNumber == listOne.size());
    }
}
