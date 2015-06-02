package com.softserve.edu.db;

import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.softserve.edu.dao.order.Order;
import com.softserve.edu.dao.order.OrderDaoImpl;
import com.softserve.edu.dao.order.OrderFromUI;
import com.softserve.edu.dao.orderstatus.OrderStatusDaoImpl;
import com.softserve.edu.dao.user.User;
import com.softserve.edu.dao.user.UserDao;
import com.softserve.edu.dao.user.UserDaoImpl;
import com.softserve.edu.dao.userroles.UserRoleDaoImpl;

public class DbProcessor {
    private ConnectionSource connectionSource;

    /**
     * private constructor
     * @param connectionSource
     */
    private DbProcessor(final ConnectionSource connectionSource) {
        this.connectionSource = connectionSource;
    }

    /**
     *
     * @param connectionSource
     * @return DbProcessor object
     */
    public static DbProcessor setConnection(ConnectionSource connectionSource) {
        return new DbProcessor(connectionSource);
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
            QueryBuilder<User, Integer> userQb = userDao.queryBuilder();
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

}
