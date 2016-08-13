package com.ivanovcorporation.dao;


import com.ivanovcorporation.domain.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by java on 20.02.2016.
 */
public class CustomerDAOImpl implements CustomerDAO {

    public Customer get(Long id) {
        try (Connection conn = ConnectionUtil.createConnection()) {
            if (conn != null) {
                PreparedStatement statement = conn.prepareStatement("SELECT * FROM customers WHERE id = ?");
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return extractCustomer(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Customer> list() {
        try (Connection conn = ConnectionUtil.createConnection()) {
            if (conn != null) {
                PreparedStatement statement = conn.prepareStatement("SELECT * FROM customers");
                ResultSet resultSet = statement.executeQuery();
                List<Customer> customers = new ArrayList<>();
                while (resultSet.next()) {
                    customers.add(extractCustomer(resultSet));
                }
                return customers;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Customer customer) {
        try (Connection conn = ConnectionUtil.createConnection()) {
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(
                        "DELETE FROM customers WHERE id = ?",
                        ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE
                );

                st.setLong(1,customer.getId());
                st.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Long save(Customer customer) {

        try (Connection conn = ConnectionUtil.createConnection()) {
            if (conn != null) {

                PreparedStatement statement = conn.prepareStatement(
                        "INSERT INTO customers " +
                        "(firstname, lastname, birthdate, address, city, passport, phone) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
                );

                statement.setString(1, customer.getFirstname());
                statement.setString(2, customer.getLastname());
                statement.setDate(3, customer.getBirthdate());
                statement.setString(4, customer.getAddress());
                statement.setString(5, customer.getCity());
                statement.setString(6, customer.getPassport());
                statement.setString(7, customer.getPhone());

                int affectedRows = statement.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating user failed, no rows affected.");
                }
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getLong(1);
                    } else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Customer customer) {

        try (Connection conn = ConnectionUtil.createConnection()) {
            if (conn != null) {

                PreparedStatement statement = conn.prepareStatement(
                        "UPDATE customers SET firstname = ?, " +
                        "lastname = ?, " +
                        "birthdate = ?, " +
                        "address = ?, " +
                        "city = ?, " +
                        "passport = ?, " +
                        "phone = ? WHERE id = ?"
                );

                statement.setString(1, customer.getFirstname());
                statement.setString(2, customer.getLastname());
                statement.setDate(3, customer.getBirthdate());
                statement.setString(4, customer.getAddress());
                statement.setString(5, customer.getCity());
                statement.setString(6, customer.getPassport());
                statement.setString(7, customer.getPhone());
                statement.setLong(8, customer.getId());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Customer extractCustomer(ResultSet resultSet) throws SQLException {

        Customer customer = new Customer(
                resultSet.getLong("id"),
                resultSet.getString("firstname"),
                resultSet.getString("lastname"),
                resultSet.getDate("birthdate"),
                resultSet.getString("address"),
                resultSet.getString("city"),
                resultSet.getString("passport"),
                resultSet.getString("phone")
        );
        return customer;
    }
}



