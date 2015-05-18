package com.softserve.edu.dao.orderstatus;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "OrderStatuses")
public class OrderStatus {

	@DatabaseField(id = true, columnName = "ID", canBeNull = false)
	private int id;
	@DatabaseField(columnName = "OrederStatusName")
	private String orderStatusName;

	OrderStatus() {

	}

	public int getId() {
		return this.id;
	}

	public String getOrderStatusName() {
		return this.orderStatusName;
	}

}
