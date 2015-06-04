package com.softserve.edu.db.dao.user;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.softserve.edu.db.dao.userroles.UserRole;

@DatabaseTable(tableName = "Users", daoClass = UserDaoImpl.class)
public class User {

	@DatabaseField(columnName = "ID", id = true, canBeNull = false)
	private int id;

	@DatabaseField(columnName = "IsUserActive")
	boolean isUserActive;

	@DatabaseField(columnName = "Balance")
	private String balance;

	@DatabaseField(columnName = "Email", canBeNull = false)
	private String email;

	@DatabaseField(columnName = "FirstName", canBeNull = false)
	private String firstName;

	@DatabaseField(columnName = "LastName", canBeNull = false)
	private String lastName;

	@DatabaseField(columnName = "Login", canBeNull = false)
	private String login;

	@DatabaseField(columnName = "Password", canBeNull = false)
	private String password;

	@DatabaseField(columnName = "CustomerTypeRef")
	private int customerTypeRef;

	@DatabaseField(columnName = "RegionRef")
	private int regionRef;

	@DatabaseField(columnName = "RoleRef", foreign = true)
	private UserRole roleRef;

	public User() {

	}

	public String getLogin() {
		return this.login;
	}

	public int getId() {
		return this.id;
	}

	public UserRole getRole() {
		return this.roleRef;
	}

	public void print() {
		System.out.println("USER" + this.firstName);
	}

}
