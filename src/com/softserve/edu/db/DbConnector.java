package com.softserve.edu.db;

import java.sql.SQLException;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

public abstract class DbConnector {
	private static JdbcConnectionSource connectionSource;
	private static final String url = "jdbc:mysql://localhost:3306/oms";
	private static final String username = "xdr";
	private static final String password = "32x2c";

	public static ConnectionSource getConnection() throws SQLException {
		connectionSource = new JdbcConnectionSource(url);
		connectionSource.setUsername(username);
		connectionSource.setPassword(password);
		return connectionSource;
	}

	public static void close() throws Exception {
		System.out.println("Closing the connection.");
		if (connectionSource != null)
			try {
				connectionSource.close();
			} catch (SQLException ignore) {
			}
	}

}
