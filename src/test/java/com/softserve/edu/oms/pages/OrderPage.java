package test.java.com.softserve.edu.oms.pages;

import main.java.edu.atqc.db.dao.order.OrderFromUI;
import main.java.edu.atqc.helpers.ContextVisible;
import main.java.edu.atqc.helpers.Report;
import main.java.edu.atqc.page.order.OrderNavigationHelper;
import main.java.edu.atqc.page.order.OrderPageHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Xdr on 6/5/15.
 */
public class OrderPage {

    //    create order
    private WebElement createNewOrder;
    //    filters
    private Select filterBySelect;
    private Select filterValueSelect;
    //    search
    private Select searchBySelect;
    private WebElement searchField;
    private WebElement applyBtn;
    //    table
    private WebElement ordersTable;
    //    navigation
    private WebElement firstPageBtn;
    private WebElement previousPageBtn;
    private WebElement nextPageBtn;
    private WebElement lastPageBtn;
    //    logout
    private WebElement logout;


    public OrderPage() {
        this.createNewOrder = ContextVisible.get().getVisibleWebElement(By.partialLinkText("Create new order"));
        //    filters
        this.filterBySelect = new Select(ContextVisible.get().getVisibleWebElement(By.id("filterBy")));
        this.filterValueSelect = new Select(ContextVisible.get().getVisibleWebElement(By.id("filterValue")));
        //    search
        this.searchBySelect = new Select(ContextVisible.get().getVisibleWebElement(By.id("search")));
        this.searchField = ContextVisible.get().getVisibleWebElement(By.id("searchValue"));
        this.applyBtn = ContextVisible.get().getVisibleWebElement(By.name("Apply"));
        //    table
        this.ordersTable = ContextVisible.get().getVisibleWebElement(By.id("list"));
        //    navigation
        this.nextPageBtn = ContextVisible.get().getVisibleWebElement(By.name("nextPage"));
        this.previousPageBtn = ContextVisible.get().getVisibleWebElement(By.name("previousPage"));
        this.firstPageBtn = ContextVisible.get().getVisibleWebElement(By.name("firstPage"));
        this.lastPageBtn = ContextVisible.get().getVisibleWebElement(By.name("lastPage"));
        //        logout
        this.logout = ContextVisible.get().getVisibleWebElement(By.xpath("//a[@href='/OMS/logout.htm']"));
    }

    public WebElement getCreateNewOrder() {
        return createNewOrder;
    }

    public Select getFilterBySelect() {
        return filterBySelect;
    }

    public Select getFilterValueSelect() {
        return filterValueSelect;
    }

    public Select getSearchBySelect() {
        return searchBySelect;
    }

    public WebElement getSearchField() {
        return searchField;
    }

    public WebElement getApplyBtn() {
        return applyBtn;
    }

    public WebElement getOrdersTable() {
        return ordersTable;
    }

    public WebElement getFirstPageBtn() {
        return firstPageBtn;
    }

    public WebElement getPreviousPageBtn() {
        return previousPageBtn;
    }

    public WebElement getNextPageBtn() {
        return nextPageBtn;
    }

    public WebElement getLastPageBtn() {
        return lastPageBtn;
    }


    // - - - - - - - - - -
    public LoginPage logout() {
        this.logout.click();
        return new LoginPage();
    }
    // - - - - - - - - - -


//    ----------------------------FILTERS----------------------------------------------------

    /**
     * Finds filter select elements.
     */
    public void findFilterSelectors() {
        this.filterBySelect = new Select(ContextVisible.get().getVisibleWebElement(By.id("filterBy")));
        this.filterValueSelect = new Select(ContextVisible.get().getVisibleWebElement(By.id("filterValue")));
    }

    /**
     * Finds select elements.
     *
     * @return list of select web elements for filters.
     */
    public List<WebElement> getFiltersBy() {
        findFilterSelectors();
        return filterBySelect.getOptions();
    }

    /**
     * Finds select valuse elements.
     *
     * @return list of select web elements for filter values.
     */
    public List<WebElement> getFilterValues() {
        findFilterSelectors();
        return filterValueSelect.getOptions();
    }

    /**
     * Select filter.
     *
     * @param field     filter select (Status or Role)
     * @param criterion filter Value (None, Pendidng...)
     */
    public void orderBy(String field, String criterion) {
        findFilterSelectors();
        filterBySelect.selectByVisibleText(field);// "orderStatus"
        if (criterion != null)
            filterValueSelect.selectByVisibleText(criterion);
    }

    /**
     * Get values for specified filter.
     *
     * @param field filter field.
     * @return list of String filter values.
     */
    public List<String> readValuesForField(String field) {
        List<String> result = new ArrayList<String>();
        filterBySelect.selectByVisibleText(field);

        // need to apply coz theres bug with role
        apply();
        findFilterSelectors();

        Iterator<WebElement> iter = filterValueSelect.getOptions().iterator();
        while (iter.hasNext()) {
            result.add(iter.next().getText());
        }

        return result;
    }

    /**
     * Select filter by status.
     */
    public void setFilterByStatus() {
        List<WebElement> filters = getFiltersBy();
        for (WebElement filter : filters) {
            if (filter.getText().equals("Status")) {
                filter.click();
            }
        }
    }

    /**
     * Select filter by role.
     */
    public void setFilterByRole() {
        List<WebElement> filters = getFiltersBy();
        for (WebElement filter : filters) {
            if (filter.getText().equals("Role")) {
                filter.click();
            }
        }
    }

    /**
     * Click on apply button.
     */
    public void apply() {
        ContextVisible.get().getVisibleWebElement(By.name("Apply")).click();
    }

//    ---------------------------------FILTERS-----------------------------------------


//     -------------------------------NAVIGATION----------------------------------------

    /**
     * Finds navigation buttons on page.
     */
    public void getNavButtons() {
        this.nextPageBtn = ContextVisible.get().getVisibleWebElement(By.name("nextPage"));
        this.previousPageBtn = ContextVisible.get().getVisibleWebElement(By.name("previousPage"));
        this.firstPageBtn = ContextVisible.get().getVisibleWebElement(By.name("firstPage"));
        this.lastPageBtn = ContextVisible.get().getVisibleWebElement(By.name("lastPage"));
    }

    /**
     * Click on 'next' button
     */
    public void navigateToNextPage() {
        Report.log("Click on '" + this.nextPageBtn.getAttribute("value") + "'");
        this.nextPageBtn.click();
    }

    /**
     * Click on 'previous' button
     */
    public void navigateToPrevPage() {
        Report.log("Click on '" + this.previousPageBtn.getAttribute("value") + "'");
        this.previousPageBtn.click();
    }

    /**
     * Click on 'first' button
     */
    public void navigateToFirstPage() {
        Report.log("Click on '" + this.firstPageBtn.getAttribute("value") + "'");
        this.firstPageBtn.click();
    }

    /**
     * Click on 'last' button
     */
    public void navigateToLastPage() {
        Report.log("Click on '" + this.lastPageBtn.getAttribute("value") + "'");
        this.lastPageBtn.click();
    }

    /**
     * Check if 'next' button is disabled.
     *
     * @return true if is disabled, fasle if is enabled.
     */
    public boolean isNextBtnDisabled() {
        return !this.nextPageBtn.isEnabled();
    }

    /**
     * Check if 'previous' button is disabled.
     *
     * @return true if is disabled, fasle if is enabled.
     */
    public boolean isPrevBtnDisabled() {
        return !this.previousPageBtn.isEnabled();
    }

    /**
     * Check if 'first' button is disabled.
     *
     * @return true if is disabled, fasle if is enabled.
     */
    public boolean isFirstBtnDisabled() {
        return !this.firstPageBtn.isEnabled();
    }

    /**
     * Check if 'last' button is disabled.
     *
     * @return true if is disabled, fasle if is enabled.
     */
    public boolean isLastBtnDisabled() {
        return !this.lastPageBtn.isEnabled();
    }


    /**
     * Check if 'last' button is disabled.
     *
     * @throws AssertionError if button is not disabled.
     */
    public void isLastBtnDisabledWithException() throws AssertionError {
        OrderNavigationHelper.get().isBtnDisabled(this.lastPageBtn);
    }


//     -------------------------------NAVIGATION----------------------------------------


//     -------------------------------SEARCH----------------------------------------

    public void getSearchFields() {
        this.searchBySelect = new Select(ContextVisible.get().getVisibleWebElement(By.id("search")));
        this.searchField = ContextVisible.get().getVisibleWebElement(By.id("searchValue"));
        this.applyBtn = ContextVisible.get().getVisibleWebElement(By.name("Apply"));
    }

    /**
     * Set search criterion to 'Status'
     */
    public void setCriterionToStatus() {
        for (WebElement criterion : this.searchBySelect.getOptions()) {
            if (criterion.equals("Status")) {
                Report.log("Click on '" + criterion + "'");
                criterion.click();
            }
        }
    }

    /**
     * Set search criterion to 'Order Name'
     */
    public void setCriterionToOrderName() {
        for (WebElement criterion : this.searchBySelect.getOptions()) {
            if (criterion.equals("Order Name")) {
                Report.log("Click on '" + criterion + "'");
                criterion.click();
            }
        }
    }

    /**
     * Click on 'Apply' button
     */
//    public void apply() {
//        Report.log("Click on '" + applyBtn.getAttribute("value") + "'");
//        this.applyBtn.click();
//    }

    /**
     * Type specified text to search field
     *
     * @param text text to type
     */
    public void typeTextToSearch(String text) {
        Report.log("Type '" + text + "' to '" + searchField.getAttribute("id") + "'");
        this.searchField.sendKeys(text);
    }
//     -------------------------------SEARCH----------------------------------------


//     -------------------------------ORDER TABLE----------------------------------------

    /**
     * Gets order table size. Navigates through orders table until finds duplicated values.
     *
     * @return Size of orders table.
     */
    public int getTableSize() {
        OrderPageHelper.navigateToOrderPage();
        WebElement ordersTable = findOrdersTable();
        List<OrderFromUI> ordersFromTable = getOrdersFromTablePage();
        int size = ordersFromTable.size();
        navigateToNextPage();
        ordersTable = findOrdersTable();
        while (!ordersFromTable.get(0).equals(
                getOrdersFromTablePage().get(0))) {

            if (!ordersFromTable.get(ordersFromTable.size() - 1).equals(
                    getOrdersFromTablePage().get(0))) {
                size += 2;
                ordersFromTable = getOrdersFromTablePage();
                navigateToNextPage();
                ordersTable = findOrdersTable();
            } else {
                size += 1;
                ordersFromTable = getOrdersFromTablePage();
                navigateToNextPage();
                ordersTable = findOrdersTable();
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
        // OrderPageHelper.navigateToOrderPage(driver);
        WebElement ordersTable = findOrdersTable();
        List<OrderFromUI> orders = new ArrayList<OrderFromUI>();

        // 1.get orders from table page
        if (orders.size() == 0) {
            List<OrderFromUI> ordersFromTable = getOrdersFromTablePage();
            if (ordersFromTable.size() == 0) {
                return orders;
            }
            for (OrderFromUI order : ordersFromTable) {
                orders.add(order);
            }
            getNavButtons();
            navigateToNextPage();
            ordersTable = findOrdersTable();
        }
        // 2.compare
        // there's order duplicate *(look comment below)
        List<OrderFromUI> ordersUi = getOrdersFromTablePage();
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
            getNavButtons();
            navigateToNextPage();
            ordersTable = findOrdersTable();
            ordersUi = getOrdersFromTablePage();
        }
        return orders;
    }

    /**
     * Finds order table on page.
     *
     * @return orders table web element.
     */
    public WebElement findOrdersTable() {
        this.ordersTable = ContextVisible.get().getVisibleWebElement(By.id("list"));
        return this.ordersTable;
    }

    /**
     * Reads all rows from specified table.
     *
     * @param table web element - table.
     * @return list of orders from table.
     */
    public List<OrderFromUI> getOrdersFromTablePage() {
        WebElement table = this.ordersTable;
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
     * Reads order by row number in table.
     *
     * @param num row number.
     * @return OrderFromUI object with data from table row.
     */
    public OrderFromUI getOrderByNumber(int num) {
        OrderPageHelper.navigateToOrderPage();
        WebElement table = findOrdersTable();
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
                navigateToNextPage();
                table = findOrdersTable();
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
//        ord.print();
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

//     -------------------------------ORDER TABLE----------------------------------------

}
