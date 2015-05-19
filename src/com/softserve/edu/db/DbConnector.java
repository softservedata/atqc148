package com.softserve.edu.db;

import java.sql.SQLException;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

public class DbConnector {
	JdbcConnectionSource connectionSource;
	private String url = "jdbc:mysql://localhost:3306/oms";
	private String username = "xdr";
	private String password = "32x2c";

	public DbConnector() {
	}

	public ConnectionSource getConnection() throws SQLException {
		System.out.println("Opening the connection.");
		connectionSource = new JdbcConnectionSource(url);
		connectionSource.setUsername(username);
		connectionSource.setPassword(password);
		return connectionSource;
	}

	public void close() throws Exception {
		System.out.println("Closing the connection.");
		if (connectionSource != null)
			try {
				connectionSource.close();
			} catch (SQLException ignore) {
			}
	}

}
