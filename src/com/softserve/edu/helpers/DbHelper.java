package com.softserve.edu.helpers;

import java.util.List;

import com.j256.ormlite.support.ConnectionSource;
import com.softserve.edu.dao.order.OrderFromUI;
import com.softserve.edu.db.DbConnector;
import com.softserve.edu.db.DbProcessor;

public class DbHelper {

	public static List<OrderFromUI> readOrdersByField(FilterValues filter,
			OrderStatuses status) throws Exception {
		ConnectionSource connection = DbConnector.getConnection();
		List<OrderFromUI> odrderList = DbProcessor.setConnection(connection)
				.getOrdersFromDbByStatus(filter.getDbName(), status.getId());
		return odrderList;
	}
	
	
	public static List<OrderFromUI> readOrdersByField(FilterValues filter,
			UserRoles role) throws Exception {
		ConnectionSource connection = DbConnector.getConnection();
		List<OrderFromUI> odrderList = DbProcessor.setConnection(connection)
				.getOrdersFromDbByRole(filter.getDbName(), role.getId());
		return odrderList;
	}
	
	
	
}
