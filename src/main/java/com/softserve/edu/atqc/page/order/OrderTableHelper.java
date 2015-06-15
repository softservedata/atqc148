package main.java.com.softserve.edu.atqc.page.order;

import java.util.*;

import main.java.com.softserve.edu.atqc.db.dao.order.OrderFromUI;
import main.java.com.softserve.edu.atqc.helpers.Report;
import main.java.com.softserve.edu.atqc.helpers.webdriver.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderTableHelper {

    private WebDriver driver;

    private OrderTableHelper(WebDriver driver) {
        this.driver = driver;
    }

    public static OrderTableHelper setDriver() {
        return new OrderTableHelper(WebDriverUtils.get().getWebDriver());
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
        if (row.getText().indexOf("Status Assignee") < 0) {
            List<WebElement> cells = row.findElements(By.cssSelector("td"));
            for (WebElement cell : cells) {
                rowString.add(cell.getText());
            }
        }
        return rowString;
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
