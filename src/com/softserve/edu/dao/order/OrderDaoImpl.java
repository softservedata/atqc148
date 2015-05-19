package com.softserve.edu.dao.order;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.softserve.edu.dao.orderstatus.OrderStatusDaoImpl;
import com.softserve.edu.dao.user.UserDaoImpl;
import com.softserve.edu.dao.userroles.UserRoleDaoImpl;

public class OrderDaoImpl extends BaseDaoImpl<Order, Integer> implements
		OrderDao, AutoCloseable {
	public OrderDaoImpl(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Order.class);
	}

	public List<Order> getAllFromOrders() throws Exception {
		List<Order> ordersList = new ArrayList<Order>();
		// DbConnector dbc = new DbConnector();
		OrderDaoImpl orderDao = new OrderDaoImpl(connectionSource);
		UserDaoImpl userDao = new UserDaoImpl(connectionSource);
		OrderStatusDaoImpl orderStatusDao = new OrderStatusDaoImpl(
				connectionSource);
		UserRoleDaoImpl roleDao = new UserRoleDaoImpl(connectionSource);
		try {
			for (Order order : orderDao) {
				userDao.refresh(order.getAssigne());
				roleDao.refresh(order.getAssigne().getRole());
				orderStatusDao.refresh(order.getOrderStatusRef());
				ordersList.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			orderDao.close();
			userDao.close();
			orderStatusDao.close();
			roleDao.close();
			// dbc.close();
		}
		return ordersList;
	}

	public Order getFromOrdersById(int id) {
		Order order = null;
		try (OrderDaoImpl orderDao = new OrderDaoImpl(this.connectionSource)) {
			order = orderDao.queryForId(id);
			if (order == null) {
				throw new Exception("There's no order with id -" + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public void close() throws Exception {
		connectionSource.close();
	}
}
