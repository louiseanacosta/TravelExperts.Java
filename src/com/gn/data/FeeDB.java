/**
 * @author: Louise Acosta
 * Purpose: get fee table from travel experts database
 */

package com.gn.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FeeDB {

    public static ObservableList<String> getFeeId() {
        ObservableList<String> feeId = FXCollections.observableArrayList();
        try {
            // get connection
            Connection connection = DBHelper.getConnection();
            // create statement
            Statement statement = connection.createStatement();
            // query
            String query = "SELECT FEEID FROM FEES";
            // execute query
            ResultSet rs = statement.executeQuery(query);
            // create object and add to list
            while (rs.next()) {
                feeId.add(rs.getString(1)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feeId;
    }
}

