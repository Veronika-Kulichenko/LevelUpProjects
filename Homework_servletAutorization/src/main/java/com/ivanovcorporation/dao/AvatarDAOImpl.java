package com.ivanovcorporation.dao;


import com.ivanovcorporation.domain.Account;
import com.ivanovcorporation.domain.Avatar;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Сергей on 21.02.2016.
 */
public class AvatarDAOImpl implements AvatarDAO {

    public Avatar get(Long avatarid) {
        try (Connection conn = ConnectionUtil.createConnection()) {
            if (conn != null) {
                PreparedStatement statement = conn.prepareStatement("SELECT * FROM avatars WHERE avatarid = ?");
                statement.setLong(1, avatarid);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return extractAvatar(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Avatar> list() {
        try (Connection conn = ConnectionUtil.createConnection()) {
            if (conn != null) {
                PreparedStatement statement = conn.prepareStatement("SELECT * FROM avatars");
                ResultSet resultSet = statement.executeQuery();
                List<Avatar> avatars = new ArrayList<>();
                while (resultSet.next()) {
                    avatars.add(extractAvatar(resultSet));
                }
                return avatars;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Avatar> listOfAvatarsByUser(Long userid) {
        try (Connection conn = ConnectionUtil.createConnection()) {
            if (conn != null) {
                PreparedStatement statement = conn.prepareStatement("SELECT * FROM avatars WHERE userid = ?");
                statement.setLong(1, userid);
                ResultSet resultSet = statement.executeQuery();
                List<Avatar> avatars = new ArrayList<>();
                while (resultSet.next()) {
                    avatars.add(extractAvatar(resultSet));
                }
                return avatars;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Avatar avatar) {
        try (Connection conn = ConnectionUtil.createConnection()) {
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(
                        "DELETE FROM avatars WHERE avatarid = ?",
                        ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE
                );
                st.setLong(1, avatar.getAvatarid());
                st.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByUser(Long userid) {
        try (Connection conn = ConnectionUtil.createConnection()) {
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(
                        "DELETE FROM avatars WHERE userid = ?",
                        ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE
                );

                st.setLong(1, userid);
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
