/**
 * @author: Louise Acosta
 * PURPOSE: Get packages_products_suppliers in travel experts database (packages_products_suppliers Table)
 */


package com.gn.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Packages_products_suppliersDB {
    public static ObservableList<Integer> getProductSupplierId() {
        ObservableList<Integer> productSupplierId = FXCollections.observableArrayList();
        try {
            // get connection
            Connection connection = DBHelper.getConnection();
            // create statement
            Statement statement = connection.createStatement();
            // query
            String query = "SELECT PRODUCTSUPPLIERID FROM PACKAGES_PRODUCTS_SUPPLIERS";
            // execute query
            ResultSet rs = statement.executeQuery(query);
            // create object and add to list
            while (rs.next()) {
                productSupplierId.add(rs.getInt(1)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productSupplierId;
    }
}
