package com.ivanovcorporation.service;

import com.ivanovcorporation.dao.AccountDAO;
import com.ivanovcorporation.dao.AccountDAOImpl;
import com.ivanovcorporation.dao.UserDAO;
import com.ivanovcorporation.dao.UserDAOImpl;
import com.ivanovcorporation.domain.Account;
import com.ivanovcorporation.domain.Customer;
import com.ivanovcorporation.domain.Transaction;
import com.ivanovcorporation.domain.User;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * Created by Sergey on 22.02.2016.
 */
public class UserService {

    private UserDAO userDAO = new UserDAOImpl();

    public User get(Long id) {
        return userDAO.get(id);
    }

    public User getByLogin(String login) {
        return userDAO.getByLogin(login);
    }

    public User getByCustomerID(Long customerID) {
        return userDAO.getByCustomerID(customerID);
    }

    public Long save(User user) {

        return userDAO.save(user);
    }

    public void delete(User user) {

        if (user.getLogin() != "super") {


            userDAO.delete(user);

            CustomerService customerService = new CustomerService();
            customerService.delete(customerService.get(user.getCustomerid()));
        }
    }

    public void update(User user) {
        if (user.getLogin() != "super") {

            userDAO.update(user);
        }

    }

    public List<User> list() {
        return userDAO.list();
    }

    public List<User> listOfUsersByRole(String role) {
        return userDAO.listOfUsersByRole(role);
    }
}
