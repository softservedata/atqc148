package main.java.edu.atqc.db.dao.user;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

/** JDBC implementation of the UserDao interface. */
public class UserDaoImpl extends BaseDaoImpl<UserDB, Integer> implements UserDao,
		AutoCloseable {
	// this constructor must be defined
	public UserDaoImpl(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, UserDB.class);
	}

	public List<UserDB> selectAllFromTable() {

		return null;
	}

	public UserDB selectFromTableById(String id) {
		return null;
	}

	@Override
	public void close() throws Exception {
		connectionSource.close();
	}
}
