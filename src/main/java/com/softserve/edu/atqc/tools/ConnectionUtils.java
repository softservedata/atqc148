package com.softserve.edu.atqc.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
    private static volatile ConnectionUtils instance = null;
    // TODO multithreading
    private Connection connection = null;
    private DataSource dataSource;

    private ConnectionUtils(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static ConnectionUtils get(DataSource dataSource) {
        if (instance == null) {
            synchronized (ConnectionUtils.class) {
                if (instance == null) {
                    instance = new ConnectionUtils(dataSource);
                    try {
                        DriverManager
                                .registerDriver(dataSource.getJdbcDriver());
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        // TODO LOGS, Develop My Exception
                        throw new RuntimeException("DB Error");

                    }
                }
            }
        }
        return instance;
    }

    public static ConnectionUtils get() {
        if (instance == null) {
            // TODO LOGS, Develop My Exception
            throw new RuntimeException("DB Error");
        }
        return instance;
    }

    public Connection getConnection() {
        if (connection == null) {
            synchronized (ConnectionUtils.class) {
                if (connection == null) {
                    try {
                        connection = DriverManager.getConnection(
                                dataSource.getUrl(), dataSource.getUsername(),
                                dataSource.getPassword());
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        // TODO LOGS, Develop My Exception
                        throw new RuntimeException("DB Error");
                    }
                }
            }
        }
        return connection;
    }

    public void closeConnection() {
        if (getConnection() != null) {
            try {
                getConnection().close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                // TODO LOGS, Develop My Exception
                throw new RuntimeException("DB Error");
            }
        }
    }

}
