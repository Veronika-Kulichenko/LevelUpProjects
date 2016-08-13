package com.ivanovcorporation.dao;


import com.ivanovcorporation.domain.Account;
import com.ivanovcorporation.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Сергей on 21.02.2016.
 */
public class UserDAOImpl implements UserDAO {

    public User get(Long id) {
        try (Connection conn = ConnectionUtil.createConnection()) {
            if (conn != null) {
                PreparedStatement statement = conn.prepareStatement("SELECT * FROM users WHERE userid = ?");
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return extractUser(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getByLogin(String login) {
        try (Connection conn = ConnectionUtil.createConnection()) {
            if (conn != null) {
                PreparedStatement statement = conn.prepareStatement("SELECT * FROM users WHERE login = ?");
                statement.setString(1, login);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return extractUser(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getByCustomerID(Long customerID) {
        try (Connection conn = ConnectionUtil.createConnection()) {
            if (conn != null) {
                PreparedStatement statement = conn.prepareStatement("SELECT * FROM users WHERE customerid = ?");
                statement.setLong(1, customerID);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return extractUser(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> list() {
        try (Connection conn = ConnectionUtil.createConnection()) {
            if (conn != null) {
                PreparedStatement statement = conn.prepareStatement("SELECT * FROM users");
                ResultSet resultSet = statement.executeQuery();
                List<User> users = new ArrayList<>();
                while (resultSet.next()) {
                    users.add(extractUser(resultSet));
                }
                return users;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> listOfUsersByRole(String role) {
        try (Connection conn = ConnectionUtil.createConnection()) {
            if (conn != null) {
                PreparedStatement statement = conn.prepareStatement("SELECT * FROM users WHERE role = ?");
                statement.setString(1, role);
                ResultSet resultSet = statement.executeQuery();
                List<User> users = new ArrayList<>();
                while (resultSet.next()) {
                    users.add(extractUser(resultSet));
                }
                return users;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void delete(User user) {
        try (Connection conn = ConnectionUtil.createConnection()) {
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(
                        "DELETE FROM users WHERE userid = ?",
                        ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE
                );
                st.setLong(1, user.getUserid());
                st.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Long save(User user) {

        try (Connection conn = ConnectionUtil.createConnection()) {
            if (conn != null) {

                PreparedStatement statement = conn.prepareStatement(
                        "INSERT INTO users " +
                                "(login, password, name, role, customerid) " +
                                "VALUES(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
                );

                statement.setString(1, user.getLogin());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getName());
                statement.setString(4, user.getRole());
                statement.setLong(5, user.getCustomerid());

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

    public void update(User user) {

        try (Connection conn = ConnectionUtil.createConnection()) {
            if (conn != null) {

                PreparedStatement statement = conn.prepareStatement(
                        "UPDATE users SET " +
                                "login = ?, " +
                                "password = ?, " +
                                "name = ?, " +
                                "role = ?, " +
                                "customerid = ? " +
                                "WHERE userid = ? "
                );

                statement.setString(1, user.getLogin());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getName());
                statement.setString(4, user.getRole());
                statement.setLong(5, user.getCustomerid());
                statement.setLong(6, user.getUserid());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User extractUser(ResultSet resultSet) throws SQLException {

        User user = new User(
                resultSet.getLong("userid"),
                resultSet.getString("login"),
                resultSet.getString("password"),
                resultSet.getString("name"),
                resultSet.getString("role"),
                resultSet.getLong("customerid")
        );
        return user;
    }
}
