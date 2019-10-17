/**
 * @author: Louise Acosta
 * Purpose: get trip type table from travel experts database
 */

package com.gn.data;


import com.gn.model.TripType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TripTypeDB {

    public static ObservableList<String> getTripType() {
        ObservableList<String> typesList = FXCollections.observableArrayList();
        try {
            // get connection
            Connection connection = DBHelper.getConnection();
            // create statement
            Statement statement = connection.createStatement();
            // query
            String query = "SELECT TRIPTYPEID FROM TRIPTYPES";
            // execute query
            ResultSet rs = statement.executeQuery(query);
            // create object and add to list
            while (rs.next()) {
                typesList.add(rs.getString(1)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return typesList;
    }

}
