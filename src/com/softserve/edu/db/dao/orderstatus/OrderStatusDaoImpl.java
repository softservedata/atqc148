package com.softserve.edu.db.dao.orderstatus;

import java.sql.SQLException;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class OrderStatusDaoImpl extends BaseDaoImpl<OrderStatus, Integer>
		implements OrderStatusDao {

	public OrderStatusDaoImpl(ConnectionSource connectionSource)
			throws SQLException {
		super(connectionSource, OrderStatus.class);
	}

	public void close() throws SQLException {
		connectionSource.close();
	}

}
