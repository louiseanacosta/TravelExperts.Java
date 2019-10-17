package com.gn.data;

import com.gn.model.Booking;
import com.gn.model.Products;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductsDB {

    public static ObservableList<Products> getProductsIncludedInPackage(int packageId) {
        ObservableList<Products> products = FXCollections.observableArrayList();

        try {
            // load driver
            Class.forName("com.mysql.jdbc.Driver");
            // get connection
            Connection connection = DBHelper.getConnection();
            // create statement
            Statement statement = connection.createStatement();
            // select query
            String selectQuery = "SELECT q.ProductSupplierId, p.ProdName, s.SupName " +
                    "FROM Products p, Products_Suppliers q, Suppliers s, Packages t, Packages_Products_Suppliers u " +
                    "WHERE p.ProductId = q.ProductId and s.SupplierId = q.SupplierId and " +
                    "q.ProductSupplierId = u.ProductSupplierId and t.PackageId = u.PackageId and " +
                    "t.PackageId = " + packageId;


            // execute
            ResultSet resultSet = statement.executeQuery(selectQuery);
            // create new object
            while (resultSet.next()) {
                products.add(new Products(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                ));
            }
            // close connection
            connection.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please contact IT", ButtonType.CLOSE);
            alert.showAndWait();
        }
        return products;
    }

}
