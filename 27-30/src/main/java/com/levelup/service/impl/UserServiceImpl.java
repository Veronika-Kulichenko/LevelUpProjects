package com.levelup.service.impl;

import com.levelup.core.dto.UserDTO;
import com.levelup.core.entity.User;
import com.levelup.dao.UserDao;
import com.levelup.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean registerUser(User user) {
        if (userDao.getUserByEmail(user.getEmail()) == null) {
            userDao.save(user);
            System.out.println(String.format("User with email %s created", user.getEmail()));
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isPasswordCorrect(String login, String pass) {
        return userDao.isPasswordCorrect(login, pass);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }
}
