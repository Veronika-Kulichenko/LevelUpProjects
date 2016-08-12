package com.levelup.table.dao.mapper;

import com.levelup.table.entity.Citizen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Alexandr Shegeda on 21.07.16.
 */
public class CitizenRowMapper {

    public static ArrayList<Citizen> fetch(ResultSet resultSet) {
        ArrayList<Citizen> result = new ArrayList<>();

        try {
            while (resultSet.next()) {
                result.add(new Citizen(
                        resultSet.getLong("ID"),
                        resultSet.getString("FIRST_NAME"),
                        resultSet.getString("LAST_NAME"),
                        resultSet.getInt("AGE"),
                        (Integer) resultSet.getObject("STREET_ID")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Citizen fetchOne(ResultSet resultSet) {
        Citizen result = null;
        try {
            if (resultSet.next()) {
                result = new Citizen(
                        resultSet.getLong("ID"),
                        resultSet.getString("FIRST_NAME"),
                        resultSet.getString("LAST_NAME"),
                        resultSet.getInt("AGE"),
                        (Integer) resultSet.getObject("STREET_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
