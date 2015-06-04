package com.softserve.edu.db.dao.userroles;

import java.sql.SQLException;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class UserRoleDaoImpl extends BaseDaoImpl<UserRole, Integer> implements
		UserRoleDao {

	public UserRoleDaoImpl(ConnectionSource connectionSource)
			throws SQLException {
		super(connectionSource, UserRole.class);
		// TODO Auto-generated constructor stub
	}

	public void close() throws SQLException {
		connectionSource.close();
	}
}
