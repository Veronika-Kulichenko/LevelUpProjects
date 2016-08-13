package com.ivanovcorporation.dao;


import com.ivanovcorporation.domain.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Сергей on 21.02.2016.
 */
public class AccountDAOImpl implements AccountDAO {

    public Account get(Long accountnumber) {
        try (Connection conn = ConnectionUtil.createConnection()) {
            if (conn != null) {
                PreparedStatement statement = conn.prepareStatement("SELECT * FROM accounts WHERE accountnumber = ?");
                statement.setLong(1, accountnumber);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return extractAccount(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Account> list() {
        try (Connection conn = ConnectionUtil.createConnection()) {
            if (conn != null) {
                PreparedStatement statement = conn.prepareStatement("SELECT * FROM accounts");
                ResultSet resultSet = statement.executeQuery();
                List<Account> accounts = new ArrayList<>();
                while (resultSet.next()) {
                    accounts.add(extractAccount(resultSet));
                }
                return accounts;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Account> listOfAccountsByCustomer(Long customerId) {
        try (Connection conn = ConnectionUtil.createConnection()) {
            if (conn != null) {
                PreparedStatement statement = conn.prepareStatement("SELECT * FROM accounts WHERE customerid = ?");
                statement.setLong(1, customerId);
                ResultSet resultSet = statement.executeQuery();
                List<Account> accounts = new ArrayList<>();
                while (resultSet.next()) {
                    accounts.add(extractAccount(resultSet));
                }
                return accounts;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Account account) {
        try (Connection conn = ConnectionUtil.createConnection()) {
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(
                        "DELETE FROM accounts WHERE accountnumber = ?",
                        ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE
                );
                st.setLong(1, account.getAccountnumber());
                st.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByCustomer(Long customerId) {
        try (Connection conn = ConnectionUtil.createConnection()) {
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(
                        "DELETE FROM accounts WHERE customerid = ?",
                        ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE
                );

                st.setLong(1, customerId);
                st.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Long save(Account account) {

        try (Connection conn = ConnectionUtil.createConnection()) {
            if (conn != null) {

                PreparedStatement statement = conn.prepareStatement(
                        "INSERT INTO accounts " +
                                "(balance, creationdate, currency, customerid) " +
                                "VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
                );

                statement.setBigDecimal(1, account.getBalance());
                statement.setTimestamp(2, new Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));
                statement.setString(3, account.getCurrency());
                statement.setLong(4, account.getCustomerid());

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

    public void update(Account account) {

        try (Connection conn = ConnectionUtil.createConnection()) {
            if (conn != null) {

                PreparedStatement statement = conn.prepareStatement(
                        "UPDATE accounts SET balance = ?, " +
                                "creationdate = ?, " +
                                "currency = ?, " +
                                "blocked = ? WHERE accountnumber = ?"
                );

                statement.setBigDecimal(1, account.getBalance());
                statement.setTimestamp(2, new Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));
                statement.setString(3, account.getCurrency());
                statement.setBoolean(4, account.getBlocked());
                statement.setLong(5, account.getAccountnumber());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Account extractAccount(ResultSet resultSet) throws SQLException {

        Account account = new Account(resultSet.getLong("accountnumber"),
                resultSet.getBigDecimal("balance"),
                resultSet.getTimestamp("creationdate"),
                resultSet.getString("currency"),
                resultSet.getBoolean("blocked"),
                resultSet.getLong("customerid")
        );
        return account;
    }
}
