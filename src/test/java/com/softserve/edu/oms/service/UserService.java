package com.softserve.edu.oms.service;

import com.softserve.edu.atqc.tools.DataSource;
import com.softserve.edu.oms.dao.UserDao;
import com.softserve.edu.oms.entity.UserDB;

public class UserService {
    private static volatile UserService instance = null;
    private UserDao userDao;

    public UserService(DataSource dataSource) {
        userDao = UserDao.get(dataSource);
    }

    public static UserService get(DataSource dataSource) {
        if (instance == null) {
            synchronized (UserService.class) {
                if (instance == null) {
                    instance = new UserService(dataSource);
                }
            }
        }
        return instance;
    }

//    public IUser getUserByLogin(String login) {
//        UserDB userDB=userDao.getUserByLogin(login);
//        return User.get()
//                .setLogin("iva")
//        .setPassword("qwerty")
//        .setFirstname("ivanka")
//        .setLastname("horoshko")
//        .setEmail("abcd@gmail.com")
//        .setRegion("West")
//        .setRole("Administrator")
//        .build();
//    }

    public String getUserFirstnameByLogin(String login) {
        UserDB userDB=userDao.getUserByLogin(login);
        return userDB.getFirstname();
    }

    public void DeleteUserByLogin(String login) {
        userDao.deleteUserByLogin(login);
    }

}
