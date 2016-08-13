package com.ivanovcorporation.dao;


import com.ivanovcorporation.domain.Account;
import com.ivanovcorporation.domain.User;

import java.util.List;

/**
 * Created by java on 20.02.2016.
 */
public interface UserDAO {

    User get(Long id);

    User getByLogin(String Login);

    User getByCustomerID(Long CustomerID);

    Long save(User user);

    void delete(User user);

    void update(User user);

    List<User> list();

    List<User> listOfUsersByRole(String role);
}
