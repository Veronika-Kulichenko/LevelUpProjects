package com.ivanovcorporation.service;


import com.ivanovcorporation.dao.ConnectionUtil;
import com.ivanovcorporation.dao.CustomerDAO;
import com.ivanovcorporation.dao.CustomerDAOImpl;
import com.ivanovcorporation.domain.Account;
import com.ivanovcorporation.domain.Customer;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Sergey on 22.02.2016.
 */
public class CustomerService {

    private CustomerDAO customerDAO = new CustomerDAOImpl();

    public Customer get(Long id) {
        return customerDAO.get(id);
    }

    public Long save(Customer customer) {

        Long idOfInsertedCustomer = customerDAO.save(customer);

        if(!customer.getFirstname().equals("super")) {

            AccountService accountService = new AccountService();
            accountService.save(new Account(
                    new BigDecimal(0),
                    "UAH",
                    true,
                    idOfInsertedCustomer)
            );
        }
        return idOfInsertedCustomer;
    }

    public void update(Customer customer) {

        customerDAO.update(customer);
    }

    public void delete(Customer customer) {

        AccountService accountService = new AccountService();

        try (Connection conn = ConnectionUtil.createConnection()) {
            if (conn != null) {
                //Doing selection in "accounts" table by customer id
                PreparedStatement accountStatement = conn.prepareStatement(
                        "SELECT * FROM accounts WHERE customerid = ?",
                        ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_UPDATABLE
                );
                accountStatement.setLong(1, customer.getId());
                ResultSet accountResultSet = accountStatement.executeQuery();
                //Deleting accounts that match customer id
                while (accountResultSet.next()) {

                    accountService.delete(accountService.get(accountResultSet.getLong("accountnumber")));
                    accountResultSet.deleteRow();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        customerDAO.delete(customer);
    }

    public List<Customer> list() {
        return customerDAO.list();
    }
}
