package com.softserve.edu.db.dao.user;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

/** JDBC implementation of the UserDao interface. */
public class UserDaoImpl extends BaseDaoImpl<User, Integer> implements UserDao,
		AutoCloseable {
	// this constructor must be defined
	public UserDaoImpl(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, User.class);
	}

	public List<User> selectAllFromTable() {

		return null;
	}

	public User selectFromTableById(String id) {
		return null;
	}

	@Override
	public void close() throws Exception {
		connectionSource.close();
	}
}
