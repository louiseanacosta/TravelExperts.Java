/**
 * @author: Louise Acosta
 * Purpose: get region table from travel experts database
 */

package com.gn.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegionDB {
    public static ObservableList<String> getRegionId() {
        ObservableList<String> regionId = FXCollections.observableArrayList();
        try {
            // get connection
            Connection connection = DBHelper.getConnection();
            // create statement
            Statement statement = connection.createStatement();
            // query
            String query = "SELECT REGIONID FROM REGIONS";
            // execute query
            ResultSet rs = statement.executeQuery(query);
            // create object and add to list
            while (rs.next()) {
                regionId.add(rs.getString(1)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return regionId;
    }
}
