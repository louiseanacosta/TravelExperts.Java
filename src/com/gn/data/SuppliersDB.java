package com.gn.data;

import com.gn.model.Suppliers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;

import java.sql.*;

public class SuppliersDB {
    public static void addNewSupplier(Suppliers suppliers) {
        Connection conn = DBHelper.getConnection();
        PreparedStatement stmt2 = null;
        try {

            stmt2 = conn.prepareStatement("INSERT INTO suppliers (SupplierId,SupName) values(?,?) ");

            stmt2.setString(1, null);
            stmt2.setString(2, suppliers.getSupName());


            int rowsUpdated = stmt2.executeUpdate();
            if (rowsUpdated == 0) {
                new Alert(Alert.AlertType.INFORMATION, "Insert Failed", ButtonType.OK).showAndWait();

            } else {
                new Alert(Alert.AlertType.INFORMATION, "Insert Success", ButtonType.OK).showAndWait();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateSupplier(Suppliers supplier) {
        Connection conn = DBHelper.getConnection();
        try {


            PreparedStatement stmt = conn.prepareStatement("UPDATE suppliers SET SupName=? WHERE SupplierId=? ");
            stmt.setString(1, supplier.getSupName());
            stmt.setInt(2, supplier.getSupplierId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Update Failed", ButtonType.OK);


            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Update Success", ButtonType.OK);
                alert.showAndWait();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void loadSuppliers(TableView tv) {
        ObservableList<Suppliers> suppliers = FXCollections.observableArrayList();
        Connection conn = DBHelper.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from  suppliers");
            while (rs.next()) {
                suppliers.add(new Suppliers(
                        rs.getInt(1),
                        rs.getString(2)


                ));
            }
            conn.close();
            tv.setItems(null);
            tv.setItems(suppliers);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void deleteSupplier(int supplierID) {
        Connection conn = DBHelper.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("delete from suppliers where SupplierId = ?");
            stmt.setInt(1, supplierID);
            int rowsUpdated = stmt.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Delete Success", ButtonType.OK);
            alert.showAndWait();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
