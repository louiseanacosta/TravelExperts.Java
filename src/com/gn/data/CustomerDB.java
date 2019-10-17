package com.gn.data;

import com.gn.model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.*;

public class CustomerDB {
    /**
     * @SUMMARY: Get all customers from Customer Table
     * PARAMETER: bookingid
     * RETURNS: List of customers
     */
    public static ObservableList<Customers> getCustomers() {
        ObservableList<Customers> data = FXCollections.observableArrayList();
        Connection conn = DBHelper.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from customers");
            while (rs.next()) {
                data.add(new Customers(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getInt(12)
                ));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * @SUMMARY: Get customer from Customer Table
     * PARAMETER: customerId to update
     */
    public static int updateCustomer(int customerId, Customers updatedCustomer) {
        int rowsUpdated = 0;
        try {
            Connection conn = DBHelper.getConnection();
            String updateQuery = "UPDATE CUSTOMERS SET CUSTFIRSTNAME=?, CUSTLASTNAME =?, "
                    + "CUSTADDRESS=?, CUSTCITY=?, CUSTPROV=?, CUSTPOSTAL=?, "
                    + "CUSTCOUNTRY=?,CUSTHOMEPHONE=?,CUSTBUSPHONE=?,CUSTEMAIL=?,AGENTID=? "
                    + "WHERE CUSTOMERID=?";
            PreparedStatement stmt = conn.prepareStatement(updateQuery);
            stmt.setString(1, updatedCustomer.getCustFirstName());
            stmt.setString(2, updatedCustomer.getCustLastName());
            stmt.setString(3, updatedCustomer.getCustAddress());
            stmt.setString(4, updatedCustomer.getCustCity());
            stmt.setString(5, updatedCustomer.getCustProv());
            stmt.setString(6, updatedCustomer.getCustPostal());
            stmt.setString(7, updatedCustomer.getCustCountry());
            stmt.setString(8, updatedCustomer.getCustHomePhone());
            stmt.setString(9, updatedCustomer.getCustBusPhone());
            stmt.setString(10, updatedCustomer.getCustEmail());
            stmt.setString(11, String.valueOf(updatedCustomer.getAgentId()));
            stmt.setInt(12, customerId);

            rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Update failed, Please try again", ButtonType.OK);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Customer " + customerId + " updated successfully", ButtonType.OK);
                alert.showAndWait();
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsUpdated;
    }


    /**
     * @SUMMARY: Add new Customer
     * RETURNS: number of customer added
     */
    public static int addNewCustomer(Customers newCustomer) {
        int rowsUpdated = 0;
        try {
            Connection conn = DBHelper.getConnection();
            String insertQuery = "INSERT INTO Customers "
                    + "(custFirstName, custLastName, CustAddress, "
                    + "CustCity, CustProv, CustPostal, CustCountry, "
                    + "CustHomePhone, CustBusPhone, CustEmail, AgentId) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(insertQuery);
            statement.setString(1, newCustomer.getCustFirstName());
            statement.setString(2, newCustomer.getCustLastName());
            statement.setString(3, newCustomer.getCustAddress());
            statement.setString(4, newCustomer.getCustCity());
            statement.setString(5, newCustomer.getCustProv());
            statement.setString(6, newCustomer.getCustPostal());
            statement.setString(7, newCustomer.getCustCountry());
            statement.setString(8, newCustomer.getCustHomePhone());
            statement.setString(9, newCustomer.getCustBusPhone());
            statement.setString(10, newCustomer.getCustEmail());
            statement.setInt(11, newCustomer.getAgentId());
            // confirm if new customer was added
            rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error adding new customer", ButtonType.OK);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "New customer successfully added", ButtonType.OK);
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsUpdated;
    }

    /**
     * @SUMMARY: Get all customer details from Customer Table
     * PARAMETER: customerId to delete
     */

    public static int deleteCustomer(int customerId) {
        int rowsUpdated = 0;
        try {
            Connection conn = DBHelper.getConnection();
            PreparedStatement statement = conn.prepareStatement("DELETE FROM customers WHERE customerId=?");
            statement.setInt(1, customerId);

            //executes the prepared statement
            rowsUpdated = statement.executeUpdate();
            //if no rows where updated then show that the delete was unsuccessful
            if (rowsUpdated == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Error deleting customer record", ButtonType.OK);
                alert.showAndWait();
            } else { //else say it was a success
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Customer " + customerId + " deleted", ButtonType.OK);
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsUpdated;
    }

    /**
     * @SUMMARY: Get all customerid
     * PARAMETER: customerId to delete
     */

    public static ObservableList<Integer> getAllCustomerId() {
        ObservableList<Integer> customerIdList = FXCollections.observableArrayList();
        try {
            // get connection
            Connection connection = DBHelper.getConnection();
            // create statement
            Statement statement = connection.createStatement();
            // query
            String query = "SELECT CUSTOMERID FROM CUSTOMERS";
            // execute query
            ResultSet rs = statement.executeQuery(query);
            // create object and add to list
            while (rs.next()) {
                customerIdList.add(rs.getInt(1)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerIdList;
    }


    /**
     * @SUMMARY: get last customer id
     * RETURNS: last customer id
     */


    public static int getIdForNewCustomer() {
        int newBookingId = 0;
        try {
            // get connection
            Connection connection = DBHelper.getConnection();
            // query to get last id
            String query = "SELECT MAX(CUSTOMERID) FROM CUSTOMERS";
            // create statement
            Statement statement = connection.createStatement();
            // execute
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                newBookingId = resultSet.getInt(1);
            }

            // close connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newBookingId + 1;
    }

}