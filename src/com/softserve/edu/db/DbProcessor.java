package com.softserve.edu.db;

import java.util.List;

import com.j256.ormlite.support.ConnectionSource;
import com.softserve.edu.dao.order.Order;
import com.softserve.edu.dao.order.OrderDaoImpl;

public class DbProcessor {
	ConnectionSource connectionSource;

	public DbProcessor(ConnectionSource connectionSource) {
		this.connectionSource = connectionSource;
	}

	public List<Order> getDataFromDB() throws Exception {
		// handle exceptions
		OrderDaoImpl orderProc = new OrderDaoImpl(this.connectionSource);
		List<Order> orders = orderProc.getAllFromOrders();
		for (Order order : orders) {
			// order.print();
		}
		// System.out.println("-----------------------------");
		// System.out.println("Table size:" + orders.size());
		connectionSource.close();
		return orders;
	}

}
