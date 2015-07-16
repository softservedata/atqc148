package com.softserve.edu.oms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.softserve.edu.atqc.tools.ConnectionUtils;
import com.softserve.edu.atqc.tools.DataSource;
import com.softserve.edu.atqc.tools.GeneralCustomException;
import com.softserve.edu.oms.entity.UserDB;
import com.softserve.edu.oms.entity.UserDB.UserDBQueries;

public final class UserDao {
    private static volatile UserDao instance = null;
    private DataSource dataSource;

    private UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static UserDao get(DataSource dataSource) {
        if (instance == null) {
            synchronized (UserDao.class) {
                if (instance == null) {
                    instance = new UserDao(dataSource);
                }
            }
        }
        return instance;
    }

    // TODO Develop get() {}

    public UserDB getUserByLogin(String login) {
        UserDB user = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String query = String.format(
                UserDBQueries.GET_USER_BY_LOGIN.toString(), login);
        try {
            statement = ConnectionUtils.get(dataSource).getConnection()
                    .createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                // TODO Use Builder
                user = new UserDB(Long.parseLong(resultSet.getString(1)),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        Long.parseLong(resultSet.getString(7)),
                        Long.parseLong(resultSet.getString(8)));
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            // TODO Throw Custom Exception
            throw new RuntimeException("DB Error");
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception ex) {
                    // ex.printStackTrace();
                    // TODO Warning
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception ex) {
                    // ex.printStackTrace();
                    // TODO Warning
                }
            }
        }
        if (user == null) {
            // TODO Throw Custom Exception
            throw new GeneralCustomException("DB Error. User not found.");
        }
        return user;
    }

    public List<UserDB> getAllUsers() {
        List<UserDB> allUsers = new ArrayList<UserDB>();
        UserDB user = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String query = UserDBQueries.GET_ALL_USERS.toString();
        try {
            statement = ConnectionUtils.get(dataSource).getConnection()
                    .createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                // TODO Use Builder
                user = new UserDB(Long.parseLong(resultSet.getString(1)),
                        resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5),
                        resultSet.getString(6), Long.parseLong(resultSet
                                .getString(7)), Long.parseLong(resultSet
                                .getString(8)));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            // TODO Throw Custom Exception
            throw new RuntimeException("DB Error");
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception ex) {
                    // ex.printStackTrace();
                    // TODO Warning
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception ex) {
                    // ex.printStackTrace();
                    // TODO Warning
                }
            }
        }
        return allUsers;
    }

    public boolean deleteUserById(Long id) {
        Statement statement = null;
        boolean result = false;
        String query = String.format(UserDBQueries.DELETE_USER_BY_ID.toString(), id);
        try {
            statement = ConnectionUtils.get(dataSource).getConnection()
                    .createStatement();
            result = statement.execute(query);
        } catch (SQLException e) {
            // e.printStackTrace();
            // TODO Throw Custom Exception
            throw new RuntimeException("DB Error");
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception ex) {
                    // ex.printStackTrace();
                 // TODO Warning
                }
            }
        }
        // TODO result must be return if delete Ok
        return result;
    }

    public boolean deleteUserByLogin(String login) {
        Statement statement = null;
        boolean result = false;
        String query = String.format(UserDBQueries.DELETE_USER_BY_PARTIAL_LOGIN.toString(), login);
        try {
            statement = ConnectionUtils.get(dataSource).getConnection()
                    .createStatement();
            result = statement.execute(query);
        } catch (SQLException e) {
            // e.printStackTrace();
            // TODO Throw Custom Exception
            throw new RuntimeException("DB Error");
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception ex) {
                    // ex.printStackTrace();
                 // TODO Warning
                }
            }
        }
        // TODO result must be return if delete Ok
        return result;
    }

}
