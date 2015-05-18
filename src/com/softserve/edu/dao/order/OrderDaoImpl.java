package com.softserve.edu.dao.order;

import java.sql.SQLException;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class OrderDaoImpl extends BaseDaoImpl<Order, Integer> implements
		OrderDao, AutoCloseable {
	public OrderDaoImpl(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Order.class);
	}

	@Override
	public void close() throws Exception {
		connectionSource.close();
	}
}
