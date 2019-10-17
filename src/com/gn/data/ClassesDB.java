/**
 * @author: Louise Acosta
 * Purpose: get classes table
 */

package com.gn.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClassesDB {
    public static ObservableList<String> getClassId() {
        ObservableList<String> classId = FXCollections.observableArrayList();
        try {
            // get connection
            Connection connection = DBHelper.getConnection();
            // create statement
            Statement statement = connection.createStatement();
            // query
            String query = "SELECT CLASSID FROM CLASSES";
            // execute query
            ResultSet rs = statement.executeQuery(query);
            // create object and add to list
            while (rs.next()) {
                classId.add(rs.getString(1)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classId;
    }
}
