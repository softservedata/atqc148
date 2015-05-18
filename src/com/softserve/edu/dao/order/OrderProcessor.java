package com.softserve.edu.dao.order;

import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.support.ConnectionSource;
import com.softserve.edu.dao.orderstatus.OrderStatusDaoImpl;
import com.softserve.edu.dao.user.UserDaoImpl;
import com.softserve.edu.dao.userroles.UserRoleDaoImpl;
import com.softserve.edu.db.DbConnector;

public class OrderProcessor {

	public OrderProcessor() {

	}

	public List<Order> getAllFromOrders() throws Exception {
		List<Order> ordersList = new ArrayList<Order>();
		DbConnector dbc = new DbConnector();
		ConnectionSource connection = dbc.getConnection();
		OrderDaoImpl orderDao = new OrderDaoImpl(connection);
		UserDaoImpl userDao = new UserDaoImpl(connection);
		OrderStatusDaoImpl orderStatusDao = new OrderStatusDaoImpl(connection);
		UserRoleDaoImpl roleDao = new UserRoleDaoImpl(connection);
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
			dbc.close();
		}
		return ordersList;
	}

	public Order getFromOrdersById(int id) {
		Order order = null;
		try (DbConnector dbc = new DbConnector();
				OrderDaoImpl orderDao = new OrderDaoImpl(dbc.getConnection());) {
			order = orderDao.queryForId(id);
			if (order == null) {
				throw new Exception("There's no order with id -" + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}
}
