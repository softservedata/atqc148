package com.softserve.edu.dao.userroles;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Roles")
public class UserRole {
	@DatabaseField(id = true, columnName = "ID", canBeNull = false)
	int id;
	@DatabaseField(columnName = "RoleName")
	String roleName;

	public UserRole() {

	}

	public int getId() {
		return this.id;
	}

	public String getRoleName() {
		return this.roleName;
	}

}
