package main.java.edu.atqc.db.dbhelpers;

import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import main.java.edu.atqc.db.dao.order.Order;
import main.java.edu.atqc.db.dao.order.OrderDaoImpl;
import main.java.edu.atqc.db.dao.order.OrderFromUI;
import main.java.edu.atqc.db.dao.orderstatus.OrderStatusDaoImpl;
import main.java.edu.atqc.db.dao.user.UserDB;
import main.java.edu.atqc.db.dao.user.UserDaoImpl;
import main.java.edu.atqc.db.dao.userroles.UserRoleDaoImpl;
import main.java.edu.atqc.helpers.FilterValues;
import main.java.edu.atqc.helpers.OrderStatuses;
import main.java.edu.atqc.helpers.Report;
import main.java.edu.atqc.helpers.UserRoles;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DbHelpers {
    private ConnectionSource connectionSource;

    /**
     * private constructor
     * @param connectionSource
     */
    private DbHelpers(final ConnectionSource connectionSource) {
        this.connectionSource = connectionSource;
    }

    /**
     *
     * @param connectionSource
     * @return DbHelpers object
     */
    public static DbHelpers setConnection(ConnectionSource connectionSource) {
        return new DbHelpers(connectionSource);
    }

    /**
     * Reads orders from database by Status field.
     * @param filter
     * @param status
     * @return list of orders from database.
     * @throws Exception
     */
    public static List<OrderFromUI> readOrdersByField(FilterValues filter,
                                                      OrderStatuses status) throws Exception {
        Report.log("Reading orders from DB where '" + filter.getName() + "'='" + status.getName() + "'");
        ConnectionSource connection = DbConnector.getConnection();
        List<OrderFromUI> odrderList = setConnection(connection)
                .getOrdersFromDbByStatus(filter.getDbName(), status.getId());
        return odrderList;
    }

    /**
     * Reads orders form database by Role field.
     * @param filter
     * @param role
     * @return list of orders from database.
     * @throws Exception
     */
    public static List<OrderFromUI> readOrdersByField(FilterValues filter,
                                                      UserRoles role) throws Exception {
        Report.log("Reading orders from DB where '"+filter.getName()+"'='"+role.getName()+"'");
        ConnectionSource connection = DbConnector.getConnection();
        List<OrderFromUI> odrderList = setConnection(connection)
                .getOrdersFromDbByRole(filter.getDbName(), role.getId());
        return odrderList;
    }

    /**
     * Reads orders from database by OrderName.
     * @param orderName name of order.
     * @return list of orders from database with specified order name.
     * @throws Exception
     */
    public static List<OrderFromUI> readOrdersByOrderName(String orderName) throws Exception {
        Report.log("Reading orders from DB where 'Order Name'="+orderName+"'");
        ConnectionSource connection = DbConnector.getConnection();
        List<OrderFromUI> odrderList = setConnection(connection)
                .getOrdersFromDbByOrderName(orderName);
        return odrderList;
    }

    /**
     * Search for orders in database by status.
     * @param filed columnt in database to search in.
     * @param fieldValue value for search.
     * @return list of orders where field == fieldValue converted to OrderFromUI.
     * @throws Exception
     */
    public List<OrderFromUI> getOrdersFromDbByStatus(String field, int fieldValue)
            throws Exception {
        OrderDaoImpl orderProc = new OrderDaoImpl(this.connectionSource);
        UserDaoImpl userDao = new UserDaoImpl(this.connectionSource);
        OrderStatusDaoImpl orderStatusDao = new OrderStatusDaoImpl(
                this.connectionSource);
        UserRoleDaoImpl roleDao = new UserRoleDaoImpl(this.connectionSource);
        List<Order> orderList = null;
        try {
            QueryBuilder<Order, Integer> queryBuilder = orderProc
                    .queryBuilder();
            queryBuilder.where().eq(field, fieldValue);
            PreparedQuery<Order> preparedQuery = queryBuilder.prepare();
            orderList = orderProc.query(preparedQuery);

            for (Order order : orderList) {
                userDao.refresh(order.getAssigne());
                roleDao.refresh(order.getAssigne().getRole());
                orderStatusDao.refresh(order.getOrderStatusRef());
            }
        } catch (Exception e) {
            System.out.println("EXCEPTION");
            e.printStackTrace();
            // TODO: handle exception
        } finally {
            orderProc.close();
            userDao.close();
            orderStatusDao.close();
            roleDao.close();
        }
//        for (Order ord: orderList){
//            ord.print();
//        }
        return Order.toOrdersFromUI(orderList);
    }

    /**
     * Search for orders in database by role.
     * @param filed column in database to search in.
     * @param fieldValue value for search  converted to OrderFromUI.
     * @return list of orders where field == fieldValue converted to OrderFromUI.
     * @throws Exception
     */
    public List<OrderFromUI> getOrdersFromDbByRole(String filed, int fieldValue)
            throws Exception {
        OrderDaoImpl orderProc = new OrderDaoImpl(this.connectionSource);
        UserDaoImpl userDao = new UserDaoImpl(this.connectionSource);
        OrderStatusDaoImpl orderStatusDao = new OrderStatusDaoImpl(
                this.connectionSource);
        UserRoleDaoImpl roleDao = new UserRoleDaoImpl(this.connectionSource);


        List<Order> orderList = null;
        try {
            QueryBuilder<UserDB, Integer> userQb = userDao.queryBuilder();
            userQb.where().eq(filed, fieldValue);
            QueryBuilder<Order, Integer> orderQb = orderProc
                    .queryBuilder();
            orderQb.join(userQb);
            PreparedQuery<Order> preparedQuery = orderQb.prepare();
            orderList = orderProc.query(preparedQuery);
            for (Order order : orderList) {
                userDao.refresh(order.getAssigne());
                roleDao.refresh(order.getAssigne().getRole());
                orderStatusDao.refresh(order.getOrderStatusRef());
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            orderProc.close();
            userDao.close();
            orderStatusDao.close();
            roleDao.close();
        }
        return Order.toOrdersFromUI(orderList);
    }

    /**
     * Search for orders in database by order name.
     * @param filed column in database to search in.
     * @param fieldValue value for search  converted to OrderFromUI.
     * @return list of orders where field == fieldValue converted to OrderFromUI.
     * @throws Exception
     */
    public List<OrderFromUI> getOrdersFromDbByOrderName(String fieldValue)
            throws Exception {
        String dbFieldName = "OrderName";
        OrderDaoImpl orderProc = new OrderDaoImpl(this.connectionSource);
        UserDaoImpl userDao = new UserDaoImpl(this.connectionSource);
        OrderStatusDaoImpl orderStatusDao = new OrderStatusDaoImpl(
                this.connectionSource);
        UserRoleDaoImpl roleDao = new UserRoleDaoImpl(this.connectionSource);

        List<Order> orderList = null;
        try {
            QueryBuilder<Order, Integer> orderQb = orderProc
                    .queryBuilder();
            orderQb.where().like(dbFieldName, "%"+fieldValue+"%");
            PreparedQuery<Order> preparedQuery = orderQb.prepare();
            orderList = orderProc.query(preparedQuery);
            for (Order order : orderList) {
                userDao.refresh(order.getAssigne());
                roleDao.refresh(order.getAssigne().getRole());
                orderStatusDao.refresh(order.getOrderStatusRef());
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            orderProc.close();
            userDao.close();
            orderStatusDao.close();
            roleDao.close();
        }
        return Order.toOrdersFromUI(orderList);
    }

    /**
     * Get all orders from orders table in database.
     * @return list of orders.
     * @throws Exception
     */
    public List<Order> getDataFromDB() throws Exception {
        // TODO: handle exceptions
        OrderDaoImpl orderProc = new OrderDaoImpl(this.connectionSource);
        List<Order> orders = new ArrayList<Order>();
        try {
            orders = orderProc.getAllFromOrders();
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            orderProc.close();
            connectionSource.close();
        }
        return orders;
    }

    /**
     * Gets all orders from database
     * @return List of orders from database converted to OrderFromUI
     * @throws Exception
     */
    public List<OrderFromUI> getDataFromDatabase() throws Exception{

        List<Order> ordersFromDB = getDataFromDB();
        List<OrderFromUI> ordersDB = Order.toOrdersFromUI(ordersFromDB);
        return ordersDB;
    }


}
