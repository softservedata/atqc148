package com.softserve.edu.dao.user;

import java.sql.SQLException;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

/** JDBC implementation of the UserDao interface. */
public class UserDaoImpl extends BaseDaoImpl<User, Integer> implements UserDao,
		AutoCloseable {
	// this constructor must be defined
	public UserDaoImpl(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, User.class);
	}

	@Override
	public void close() throws Exception {
		connectionSource.close();
	}
}
