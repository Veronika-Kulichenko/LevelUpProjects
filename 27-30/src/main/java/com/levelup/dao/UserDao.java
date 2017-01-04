package com.levelup.dao;

import com.levelup.core.entity.User;

public interface UserDao {

    void save(User user);

    User getUserByEmail(String email);

    User getUserById(Long id);

    void update(User user);

    void delete(User user);

    boolean isPasswordCorrect(String login, String pass);
}
