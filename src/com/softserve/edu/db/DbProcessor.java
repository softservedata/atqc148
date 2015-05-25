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
import com.softserve.edu.dao.user.UserDao;
import com.softserve.edu.dao.user.UserDaoImpl;
import com.softserve.edu.dao.userroles.UserRoleDaoImpl;

public class DbProcessor {
	private ConnectionSource connectionSource;

	private DbProcessor(ConnectionSource connectionSource) {
		this.connectionSource = connectionSource;
	}

	public static DbProcessor setConnection(ConnectionSource connectionSource) {
		return new DbProcessor(connectionSource);
	}

	public List<OrderFromUI> getOrdersFromDbBy(String filed, int fieldValue)
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
			queryBuilder.where().eq(filed, fieldValue);
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
