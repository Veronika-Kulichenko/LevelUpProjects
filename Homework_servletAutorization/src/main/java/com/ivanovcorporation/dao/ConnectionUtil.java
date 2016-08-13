package com.ivanovcorporation.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Сергей on 18.02.2016.
 */
public class ConnectionUtil {


    public static Connection createConnection() {
        Driver driver = new org.postgresql.Driver();
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://10.47.90.137:5432/ivanov", "postgres", "java");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
