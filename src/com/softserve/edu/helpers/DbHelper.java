package com.softserve.edu.helpers;

import java.util.List;

import com.j256.ormlite.support.ConnectionSource;
import com.softserve.edu.dao.order.OrderFromUI;
import com.softserve.edu.db.DbConnector;
import com.softserve.edu.db.DbProcessor;

public class DbHelper {

    /**
     * Reads orders from database by Status field.
     * @param filter
     * @param status
     * @return list of orders from database.
     * @throws Exception
     */
    public static List<OrderFromUI> readOrdersByField(FilterValues filter,
                                                      OrderStatuses status) throws Exception {
        Report.log("Reading orders from DB where '"+filter.getName()+"'='"+status.getName()+"'");
        ConnectionSource connection = DbConnector.getConnection();
        List<OrderFromUI> odrderList = DbProcessor.setConnection(connection)
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
        List<OrderFromUI> odrderList = DbProcessor.setConnection(connection)
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
        List<OrderFromUI> odrderList = DbProcessor.setConnection(connection)
                .getOrdersFromDbByOrderName(orderName);
        return odrderList;
    }


}
