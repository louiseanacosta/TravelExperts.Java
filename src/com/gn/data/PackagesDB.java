

/*
 * Author:Liming Hong
 * Date: 2019 Jun 6
 * database class for package
 * */
package com.gn.data;

import com.gn.model.Packages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;

import java.sql.*;

public class PackagesDB {
    static public void addNewPackage(Packages p) {
        Connection conn = DBHelper.getConnection();
        PreparedStatement stmt2 = null;
        try {
            /*java.util.Date date =
                    java.util.Date.from(tfStartDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date sqlStartDate = new Date(date.getTime());

            java.util.Date date2 =
                    java.util.Date.from(tfEndDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date sqlEndDate = new Date(date2.getTime());*/
            stmt2 = conn.prepareStatement("INSERT INTO packages (PackageId,PkgName, PkgStartDate, PkgEndDate, PkgDesc, PkgBasePrice, PkgAgencyCommission) values(?,?, ?, ?, ?, ?, ?) ");

            stmt2.setString(1, null);
            stmt2.setString(2, p.getPkgName());
            stmt2.setDate(3, new java.sql.Date(p.getPkgStartDate().getTime()));
            stmt2.setDate(4, new java.sql.Date(p.getPkgEndDate().getTime()));
            stmt2.setString(5, p.getPkgDesc());
            stmt2.setDouble(6, p.getPkgBasePrice());
            stmt2.setDouble(7, p.getPkgAgencyCommission());

            int rowsUpdated = stmt2.executeUpdate();
            if (rowsUpdated == 0) {
                new Alert(Alert.AlertType.INFORMATION, "Failed to Add Package", ButtonType.OK).showAndWait();

            } else {
                new Alert(Alert.AlertType.INFORMATION, "New Package Added", ButtonType.OK).showAndWait();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    static public void updatePackage(Packages p) {
        Connection conn = DBHelper.getConnection();
        try {
//            Date date =
//                    Date.from(tfStartDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
//            java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
//
//            Date date2 =
//                    Date.from(tfEndDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
//            java.sql.Date sqlEndDate = new java.sql.Date(date2.getTime());

            PreparedStatement stmt = conn.prepareStatement("UPDATE packages SET PkgName=?, PkgStartDate=?, PkgEndDate=?, PkgDesc=?, PkgBasePrice=?, PkgAgencyCommission=? WHERE PackageId=? ");
            stmt.setString(1, p.getPkgName());
            stmt.setDate(2, new java.sql.Date(p.getPkgStartDate().getTime()));
            stmt.setDate(3, new java.sql.Date(p.getPkgEndDate().getTime()));
            stmt.setString(4, p.getPkgDesc());
            stmt.setDouble(5, p.getPkgBasePrice());
            stmt.setDouble(6, p.getPkgAgencyCommission());
            stmt.setInt(7, p.getPackageId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Update Failed", ButtonType.OK);
                alert.showAndWait();

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Package Updated", ButtonType.OK);
                alert.showAndWait();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void loadPackages(TableView tb) {
        Connection conn = DBHelper.getConnection();
        ObservableList<Packages> packages = FXCollections.observableArrayList();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from  packages");
            while (rs.next()) {
                packages.add(new Packages(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getDate(4),
                        rs.getString(5),
                        rs.getDouble(6),
                        rs.getDouble(7)

                ));
            }
            conn.close();
            tb.setItems(null);
            tb.setItems(packages);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deletePackage(int packageId) {
        Connection conn = DBHelper.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("delete from packages where PackageId = ?");
            stmt.setInt(1, packageId);
            int rowsUpdated = stmt.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Package Deleted", ButtonType.OK);
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Integer> getPackageId() {
        ObservableList<Integer> packageIdList = FXCollections.observableArrayList();
        try {
            // get connection
            Connection connection = DBHelper.getConnection();
            // create statement
            Statement statement = connection.createStatement();
            // query
            String query = "SELECT PACKAGEID FROM PACKAGES";
            // execute query
            ResultSet rs = statement.executeQuery(query);
            // create object and add to list
            while (rs.next()) {
                packageIdList.add(rs.getInt(1)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return packageIdList;
    }

}
