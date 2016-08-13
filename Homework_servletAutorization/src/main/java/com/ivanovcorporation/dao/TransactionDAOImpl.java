package com.ivanovcorporation.dao;


import com.ivanovcorporation.domain.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Сергей on 21.02.2016.
 */
public class TransactionDAOImpl implements TransactionDAO {

    public Transaction get(Long id) {
        try (Connection conn = ConnectionUtil.createConnection()) {
            if (conn != null) {
                PreparedStatement statement = conn.prepareStatement("SELECT * FROM transactions WHERE id = ?");
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return extractTransaction(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Transaction> list() {
        try (Connection conn = ConnectionUtil.createConnection()) {
            if (conn != null) {
                PreparedStatement statement = conn.prepareStatement("SELECT * FROM transactions");
                ResultSet resultSet = statement.executeQuery();
                List<Transaction> transactions = new ArrayList<>();
                while (resultSet.next()) {
                    transactions.add(extractTransaction(resultSet));
                }
                return transactions;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Transaction> listOfTransactionsByAccount(Long accountnumber) {
        try (Connection conn = ConnectionUtil.createConnection()) {
            if (conn != null) {
                PreparedStatement statement = conn.prepareStatement("SELECT * FROM transactions WHERE accountnumber = ?");
                statement.setLong(1, accountnumber);
                ResultSet resultSet = statement.executeQuery();
                List<Transaction> transactions = new ArrayList<>();
                while (resultSet.next()) {
                    transactions.add(extractTransaction(resultSet));
                }
                return transactions;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Transaction transaction) {
        try (Connection conn = ConnectionUtil.createConnection()) {
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(
                        "DELETE FROM transactions WHERE id = ?",
                        ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE
                );

                st.setLong(1, transaction.getId());
                st.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByAccount(Long accountId) {
        try (Connection conn = ConnectionUtil.createConnection()) {
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(
                        "DELETE FROM transactions WHERE accountnumber = ?",
                        ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE
                );
                st.setLong(1, accountId);
                st.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Long save(Transaction transaction) {

        try (Connection conn = ConnectionUtil.createConnection()) {
            if (conn != null) {

                PreparedStatement statement = conn.prepareStatement(
                        "INSERT INTO transactions " +
                                "(amount, date, operationtype, accountnumber) " +
                                "VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
                );

                statement.setBigDecimal(1, transaction.getAmount());
                statement.setTimestamp(2, new Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));
                statement.setString(3, transaction.getOperationtype());
                statement.setLong(4, transaction.getAccountnumber());

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

    public void update(Transaction transaction) {

        try (Connection conn = ConnectionUtil.createConnection()) {
            if (conn != null) {

                PreparedStatement statement = conn.prepareStatement(
                        "UPDATE transactions SET amount = ?, " +
                                "date = ?, " +
                                "operationtype = ? WHERE id = ?"
                );

                statement.setBigDecimal(1, transaction.getAmount());
                statement.setTimestamp(2, new Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));
                statement.setString(3, transaction.getOperationtype());
                statement.setLong(4, transaction.getId());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Transaction extractTransaction(ResultSet resultSet) throws SQLException {

        Transaction transaction = new Transaction(
                resultSet.getLong("id"),
                resultSet.getBigDecimal("amount"),
                resultSet.getTimestamp("date"),
                resultSet.getString("operationtype"),
                resultSet.getLong("accountnumber")
        );
        return transaction;
    }
}
